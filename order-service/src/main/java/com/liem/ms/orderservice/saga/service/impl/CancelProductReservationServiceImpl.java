package com.liem.ms.orderservice.saga.service.impl;

import static com.liem.ms.orderservice.saga.config.DeadlineManagerConfiguration.PAYMENT_PROCESS_DEADLINE;

import com.liem.ms.coreservice.events.PaymentCanceledEvent;
import com.liem.ms.coreservice.events.ProductReservedEvent;
import com.liem.ms.orderservice.command.events.OrderCreatedEvent;
import com.liem.ms.orderservice.command.mapper.OrderCommandMapper;
import com.liem.ms.orderservice.saga.service.CancelProductReservationService;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.deadline.DeadlineManager;
import org.springframework.stereotype.Service;

/**
 * The type Cancel product reservation service.
 */
@Slf4j
@Service
@AllArgsConstructor
public class CancelProductReservationServiceImpl implements CancelProductReservationService {

  /**
   * The Command gateway.
   */
  private final CommandGateway commandGateway;

  /**
   * The Command mapper.
   */
  private final OrderCommandMapper commandMapper;

  /**
   * The Deadline manager.
   */
  private final DeadlineManager deadlineManager;

  /**
   * Handle cancel product reservation.
   *
   * @param event         the event
   * @param rejectMessage the reject message
   */
  @Override
  public void handleCancelProductReservation(
      final @NotNull OrderCreatedEvent event,
      final @NotNull String rejectMessage) {
    final var productId = event.getProductId();
    final var command = this.commandMapper
        .toCancelProductReserveCommand(event, rejectMessage);
    this.deadlineManager.cancelAll(PAYMENT_PROCESS_DEADLINE);
    commandGateway.send(command, (commandMessage, commandResultMessage) -> {
      if (commandResultMessage.isExceptional()) {
        log.error("Cancel product '{}' reservation failed", productId);
      } else {
        log.info("Cancel product '{}' reservation completed", productId);
      }
    });
  }

  /**
   * Handle cancel product reservation.
   *
   * @param event         the event
   * @param rejectMessage the reject message
   */
  @Override
  public void handleCancelProductReservation(
      final @NotNull ProductReservedEvent event, final @NotNull String rejectMessage) {
    final var productId = event.getId();
    final var command = this.commandMapper
        .toCancelProductReserveCommand(event, rejectMessage);
    commandGateway.send(command, (commandMessage, commandResultMessage) -> {
      if (commandResultMessage.isExceptional()) {
        log.error("Cancel product '{}' reservation failed", productId);
      } else {
        log.info("Cancel product '{}' reservation completed", productId);
      }
    });
  }

  /**
   * Handle cancel product reservation.
   *
   * @param event the event
   */
  @Override
  public void handleCancelProductReservation(PaymentCanceledEvent event) {
    final var command = this.commandMapper
        .toCancelProductReserveCommand(event);
    commandGateway.send(command, (commandMessage, commandResultMessage) -> {
      if (commandResultMessage.isExceptional()) {
        log.error("Cancel payment '{}' for product '{}' failed",
            event.getPaymentId(), command.getProductId());
      } else {
        log.info("Cancel payment '{}' for product '{}' completed",
            event.getPaymentId(), command.getProductId());
      }
    });
  }
}
