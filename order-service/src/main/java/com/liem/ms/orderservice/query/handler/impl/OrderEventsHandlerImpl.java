package com.liem.ms.orderservice.query.handler.impl;

import com.liem.ms.orderservice.command.events.OrderApprovedEvent;
import com.liem.ms.orderservice.command.events.OrderCreatedEvent;
import com.liem.ms.orderservice.command.events.OrderRejectedEvent;
import com.liem.ms.orderservice.core.handler.OrderEventsHandler;
import com.liem.ms.orderservice.query.entity.OrderEntity;
import com.liem.ms.orderservice.query.mapper.OrderEntityMapper;
import com.liem.ms.orderservice.query.repository.OrdersRepository;
import javax.persistence.EntityNotFoundException;
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
    log.info("Create order: {}", entity);
    this.ordersRepository.save(entity);
  }

  /**
   * On approved.
   *
   * @param event the event
   */
  @EventHandler
  public void onApproved(final @NotNull OrderApprovedEvent event) {
    log.trace("Order handle: {}", event);
    final var entity = this.getOrderById(event.getOrderId())
            .updateStatus(event);
    log.info("Approve order: {}", entity);
    this.ordersRepository.save(entity);
  }

  /**
   * On rejected.
   *
   * @param event the event
   */
  @EventHandler
  public void onRejected(OrderRejectedEvent event) {
    log.trace("Order handle: {}", event);
    final var entity = this.getOrderById(event.getOrderId())
        .updateStatus(event);
    log.info("Reject order: {}", entity);
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
    if (exception instanceof EntityNotFoundException) {
      throw exception;
    }
  }

  /**
   * Gets order by id.
   *
   * @param orderId the order id
   * @return the order by id
   */
  protected OrderEntity getOrderById(final String orderId) {
    return this.ordersRepository.findById(orderId)
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("Order not found by id: %s", orderId)));
  }

}
