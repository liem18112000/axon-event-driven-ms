package com.liem.ms.orderservice.saga.impl;

import com.liem.ms.orderservice.command.events.OrderCreatedEvent;
import com.liem.ms.orderservice.command.mapper.OrderCommandMapper;
import com.liem.ms.orderservice.saga.ReserveProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

/**
 * The type Reserve product service.
 */
@Slf4j
@Service
@AllArgsConstructor
public class ReserveProductServiceImpl implements ReserveProductService {

  /**
   * The Command gateway.
   */
  private final CommandGateway commandGateway;

  /**
   * The Mapper.
   */
  private final OrderCommandMapper mapper;

  /**
   * Handle reserve product.
   *
   * @param event the event
   */
  @Override
  public void handleReserveProduct(OrderCreatedEvent event) {
    final var command = this.mapper.toReserveProductCommand(event);
    commandGateway.send(command, (commandMessage, commandResultMessage) -> {
      log.trace("Command message: {}", commandMessage);
      log.trace("Command result message: {}", commandResultMessage);
      if (commandResultMessage.isExceptional()) {
        log.error("Reserved product failed");
        this.handleFailedProductReserve(event);
      } else {
        log.info("Reserved product completed");
      }
    });
  }

  /**
   * Handle failed product reserve.
   *
   * @param event the event
   */
  @Override
  public void handleFailedProductReserve(OrderCreatedEvent event) {

  }
}
