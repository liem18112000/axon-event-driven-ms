package com.liem.ms.orderservice.saga.service.impl;

import com.liem.ms.coreservice.events.ProductCancelReserveEvent;
import com.liem.ms.orderservice.command.commands.RejectOrderCommand;
import com.liem.ms.orderservice.saga.service.RejectOrderService;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

/**
 * The type Reject order service.
 */
@Slf4j
@Service
@AllArgsConstructor
public class RejectOrderServiceImpl implements RejectOrderService {

  /**
   * The Command gateway.
   */
  private final CommandGateway commandGateway;

  /**
   * Handle reject order.
   *
   * @param event the event
   */
  @Override
  public void handleRejectOrder(final @NotNull ProductCancelReserveEvent event) {
    final var command = RejectOrderCommand.builder()
        .orderId(event.getOrderId()).reason(event.getReason()).build();
    this.commandGateway.send(command, ((commandMessage, commandResultMessage) -> {
      log.trace("Command message: {}", commandMessage);
      log.trace("Command result message: {}", commandResultMessage);
      if (commandResultMessage.isExceptional()) {
        log.error("Reject order failed");
      } else {
        log.info("Reject order completed");
      }
    }));
  }
}
