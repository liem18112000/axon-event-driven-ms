package com.liem.ms.orderservice.query.handler.impl;

import com.liem.ms.orderservice.command.events.OrderCreatedEvent;
import com.liem.ms.orderservice.query.handler.OrderEventsHandler;
import com.liem.ms.orderservice.command.mapper.OrderEntityMapper;
import com.liem.ms.orderservice.query.repository.OrdersRepository;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Order events handler.
 */
@Slf4j
@Component
@Transactional
@AllArgsConstructor
@ProcessingGroup("order-group")
public class OrderEventsHandlerImpl implements OrderEventsHandler {

  /**
   * The Orders repository.
   */
  private final OrdersRepository ordersRepository;

  /**
   * The Mapper.
   */
  private final OrderEntityMapper mapper;

  /**
   * On order created.
   *
   * @param event the event
   */
  @EventHandler
  public void onCreated(final @NotNull OrderCreatedEvent event) {
    log.trace("Order handle: {}", event);
    final var entity = this.mapper.toEntity(event);
    log.info("Create entity: {}", entity);
    this.ordersRepository.save(entity);
  }

  /**
   * Handle exception.
   *
   * @param exception the exception
   * @throws Exception the exception
   */
  @ExceptionHandler
  protected void handleException(
      final @NotNull Exception exception) throws Exception {
    log.error(exception.getMessage());
    throw exception;
  }

}
