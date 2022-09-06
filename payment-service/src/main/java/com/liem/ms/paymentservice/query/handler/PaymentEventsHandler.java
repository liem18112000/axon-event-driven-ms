package com.liem.ms.paymentservice.query.handler;

import com.liem.ms.coreservice.events.PaymentCanceledEvent;
import com.liem.ms.coreservice.events.PaymentProcessedEvent;
import com.liem.ms.paymentservice.core.PaymentStatus;
import com.liem.ms.paymentservice.query.entity.PaymentEntity;
import com.liem.ms.paymentservice.query.repository.PaymentRepository;
import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.stereotype.Component;

/**
 * The type Payment events handler.
 */
@Slf4j
@Component
@AllArgsConstructor
public class PaymentEventsHandler {

  /**
   * The Payment repository.
   */
  private final PaymentRepository paymentRepository;

  /**
   * On payment processed.
   *
   * @param event the event
   */
  @EventHandler
  public void onPaymentProcessed(PaymentProcessedEvent event) {
    log.info("PaymentProcessedEvent is called for orderId: {}", event.getOrderId());
    var paymentEntity = new PaymentEntity();
    paymentEntity.setId(event.getPaymentId());
    paymentEntity.setOrderId(event.getOrderId());
    paymentEntity.setStatus(PaymentStatus.PROCESSED);
    paymentRepository.save(paymentEntity);
  }

  /**
   * On payment canceled.
   *
   * @param event the event
   */
  @EventHandler
  public void onPaymentCanceled(PaymentCanceledEvent event) {
    log.info("PaymentCanceledEvent is called: {}", event);
    var paymentEntity = this.getPaymentById(event.getPaymentId());
    paymentEntity.setStatus(PaymentStatus.CANCELED);
    paymentEntity.setOrderId(event.getOrderId());
    paymentEntity.setCancelReason(event.getCancelReason());
    paymentRepository.save(paymentEntity);
  }

  /**
   * Handle exception.
   *
   * @param exception the exception
   * @throws Exception the exception
   */
  @ExceptionHandler
  protected void handleException(Exception exception) throws Exception {
    log.error(exception.getMessage());
    if (exception instanceof EntityNotFoundException) {
      throw exception;
    }
  }

  /**
   * Gets payment by id.
   *
   * @param paymentId the payment id
   * @return the payment by id
   */
  protected PaymentEntity getPaymentById(final @NotNull String paymentId) {
    return this.paymentRepository.findById(paymentId)
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("Payment not found by id '%s'", paymentId)));
  }
}
