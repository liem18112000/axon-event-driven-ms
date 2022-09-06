package com.liem.ms.orderservice.saga.service.impl;

import static com.liem.ms.orderservice.saga.config.DeadlineManagerConfiguration.PAYMENT_PROCESS_DEADLINE;

import com.liem.ms.coreservice.commands.CancelPaymentCommand;
import com.liem.ms.coreservice.events.PaymentProcessedEvent;
import com.liem.ms.orderservice.saga.service.CancelPaymentService;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.deadline.DeadlineManager;
import org.springframework.stereotype.Service;

/**
 * The type Cancel payment service.
 */
@Slf4j
@Service
@AllArgsConstructor
public class CancelPaymentServiceImpl implements CancelPaymentService {

  /**
   * The Command gateway.
   */
  private final CommandGateway commandGateway;

  /**
   * The Deadline manager.
   */
  private final DeadlineManager deadlineManager;

  /**
   * Handle cancel payment.
   *
   * @param event        the event
   * @param cancelReason the cancel reason
   */
  @Override
  public void handleCancelPayment(
      final @NotNull PaymentProcessedEvent event, final @NotNull String cancelReason) {
    final var paymentId = event.getPaymentId();
    final var scheduleId = event.getScheduleId();
    final var command = CancelPaymentCommand
        .builder()
        .paymentId(paymentId)
        .cancelReason(cancelReason)
        .orderId(event.getOrderId())
        .build();
    this.deadlineManager.cancelSchedule(PAYMENT_PROCESS_DEADLINE, scheduleId);
    log.info("Cancel deadline for '{}' by id '{}'", PAYMENT_PROCESS_DEADLINE, scheduleId);
    commandGateway.send(command, (commandMessage, commandResultMessage) -> {
      if (commandResultMessage.isExceptional()) {
        log.error("Cancel payment '{}' failed", paymentId);
      } else {
        log.info("Cancel payment '{}' completed", paymentId);
      }
    });
  }
}
