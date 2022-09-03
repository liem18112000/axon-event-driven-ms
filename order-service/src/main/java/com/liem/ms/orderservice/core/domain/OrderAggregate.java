package com.liem.ms.orderservice.core.domain;

import com.liem.ms.orderservice.command.commands.CreateOrderCommand;
import com.liem.ms.orderservice.command.events.OrderCreatedEvent;
import com.liem.ms.orderservice.core.model.OrderStatus;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

/**
 * The type Order aggregate.
 */
@Aggregate
@Data
@Slf4j
@NoArgsConstructor
public class OrderAggregate {

  /**
   * The Order id.
   */
  @AggregateIdentifier
  private String orderId;

  /**
   * The Product id.
   */
  private String productId;

  /**
   * The User id.
   */
  private String userId;

  /**
   * The Quantity.
   */
  private int quantity;

  /**
   * The Address id.
   */
  private String addressId;

  /**
   * The Order status.
   */
  private OrderStatus orderStatus;

  /**
   * Instantiates a new Order aggregate.
   *
   * @param createOrderCommand the create order command
   */
  @CommandHandler
  public OrderAggregate(final @NotNull CreateOrderCommand createOrderCommand) {
    OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
    BeanUtils.copyProperties(createOrderCommand, orderCreatedEvent);
    AggregateLifecycle.apply(orderCreatedEvent);
  }

  /**
   * On.
   *
   * @param orderCreatedEvent the order created event
   */
  @EventSourcingHandler
  public void on(final @NotNull OrderCreatedEvent orderCreatedEvent) {
    this.orderId = orderCreatedEvent.getOrderId();
    this.productId = orderCreatedEvent.getProductId();
    this.userId = orderCreatedEvent.getUserId();
    this.addressId = orderCreatedEvent.getAddressId();
    this.quantity = orderCreatedEvent.getQuantity();
    this.orderStatus = orderCreatedEvent.getOrderStatus();
  }


}
