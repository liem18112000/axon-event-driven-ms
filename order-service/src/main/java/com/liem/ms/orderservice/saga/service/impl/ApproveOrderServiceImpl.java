package com.liem.ms.orderservice.saga.service.impl;

import static com.liem.ms.orderservice.saga.config.DeadlineManagerConfiguration.PAYMENT_PROCESS_DEADLINE;

import com.liem.ms.coreservice.events.PaymentProcessedEvent;
import com.liem.ms.orderservice.command.commands.ApproveOrderCommand;
import com.liem.ms.orderservice.saga.service.ApproveOrderService;
import com.liem.ms.orderservice.saga.service.CancelPaymentService;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.deadline.DeadlineManager;
import org.springframework.stereotype.Service;

/**
 * The type Approve order service.
 */
@Slf4j
@Service
@AllArgsConstructor
public class ApproveOrderServiceImpl implements ApproveOrderService {

  /**
   * The Command gateway.
   */
  private final CommandGateway commandGateway;

  /**
   * The Deadline manager.
   */
  private final DeadlineManager deadlineManager;

  /**
   * The Cancel payment service.
   */
  private final CancelPaymentService cancelPaymentService;

  /**
   * Handle approve order.
   *
   * @param event the event
   */
  @Override
  public void handleApproveOrder(final @NotNull PaymentProcessedEvent event) {
    final var command = ApproveOrderCommand
        .builder().orderId(event.getOrderId()).build();
    log.info("Send approve order command: {}", command);
    final var scheduleId = event.getScheduleId();
    this.deadlineManager.cancelSchedule(PAYMENT_PROCESS_DEADLINE, scheduleId);
    log.info("Cancel deadline for '{}' by id '{}'", PAYMENT_PROCESS_DEADLINE, scheduleId);
    commandGateway.send(command, ((commandMessage, commandResultMessage) -> {
      log.trace("Command message: {}", commandMessage);
      log.trace("Command result message: {}", commandResultMessage);
      if (commandResultMessage.isExceptional()) {
        log.error("Approve order '{}' failed", event.getOrderId());
        final var cancelPayment = String.format("Approve order failed: %s",
            commandResultMessage.exceptionResult().getMessage());
        this.cancelPaymentService.handleCancelPayment(event, cancelPayment);
      } else {
        log.info("Approve order '{}' completed", event.getOrderId());
      }
    }));
  }
}
