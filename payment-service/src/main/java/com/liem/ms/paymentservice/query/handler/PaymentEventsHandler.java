package com.liem.ms.paymentservice.query.handler;

import com.liem.ms.coreservice.events.PaymentProcessedEvent;
import com.liem.ms.paymentservice.query.entity.PaymentEntity;
import com.liem.ms.paymentservice.query.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
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
    PaymentEntity paymentEntity = new PaymentEntity();
    BeanUtils.copyProperties(event, paymentEntity);
    paymentRepository.save(paymentEntity);
  }
}
