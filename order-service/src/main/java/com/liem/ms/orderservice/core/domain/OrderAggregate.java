package com.liem.ms.orderservice.core.domain;

import static com.liem.ms.orderservice.core.config.SnapShotConfiguration.DEFAULT_SNAPSHOT_TRIGGER;

import com.liem.ms.orderservice.command.commands.ApproveOrderCommand;
import com.liem.ms.orderservice.command.commands.CreateOrderCommand;
import com.liem.ms.orderservice.command.commands.RejectOrderCommand;
import com.liem.ms.orderservice.command.events.OrderApprovedEvent;
import com.liem.ms.orderservice.command.events.OrderCreatedEvent;
import com.liem.ms.orderservice.command.events.OrderRejectedEvent;
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
@Aggregate(snapshotTriggerDefinition = DEFAULT_SNAPSHOT_TRIGGER)
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
   * The Reject reason.
   */
  private String rejectReason;

  /**
   * Instantiates a new Order aggregate.
   *
   * @param command the create order command
   */
  @CommandHandler
  public OrderAggregate(final @NotNull CreateOrderCommand command) {
    log.trace("Create order command handle: {}", command);
    var event = new OrderCreatedEvent();
    BeanUtils.copyProperties(command, event);
    AggregateLifecycle.apply(event);
  }

  /**
   * On.
   *
   * @param event the order created event
   */
  @EventSourcingHandler
  public void on(final @NotNull OrderCreatedEvent event) {
    log.trace("Order created event sourcing handle: {}", event);
    this.orderId = event.getOrderId();
    this.productId = event.getProductId();
    this.userId = event.getUserId();
    this.addressId = event.getAddressId();
    this.quantity = event.getQuantity();
    this.orderStatus = event.getOrderStatus();
  }

  /**
   * Approve.
   *
   * @param command the command
   */
  @CommandHandler
  public void approve(final @NotNull ApproveOrderCommand command) {
    log.trace("Approve order command handle: {}", command);
    var event = new OrderApprovedEvent();
    event.setOrderId(command.getOrderId());
    AggregateLifecycle.apply(event);
  }

  /**
   * On.
   *
   * @param event the event
   */
  @EventSourcingHandler
  public void on(final @NotNull OrderApprovedEvent event) {
    log.trace("Order approve event sourcing handle: {}", event);
    this.setOrderId(event.getOrderId());
    this.setOrderStatus(event.getOrderStatus());
  }

  /**
   * Reject.
   *
   * @param command the command
   */
  @CommandHandler
  public void reject(final @NotNull RejectOrderCommand command) {
    log.trace("Reject order command handle: {}", command);
    var event = new OrderRejectedEvent();
    event.setOrderId(command.getOrderId());
    event.setReason(command.getReason());
    AggregateLifecycle.apply(event);
  }

  /**
   * On.
   *
   * @param event the event
   */
  @EventSourcingHandler
  public void on(final @NotNull OrderRejectedEvent event) {
    log.trace("Order reject event sourcing handle: {}", event);
    this.setOrderId(event.getOrderId());
    this.setOrderStatus(event.getOrderStatus());
    this.setRejectReason(event.getReason());
  }

}
