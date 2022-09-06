package com.liem.ms.orderservice.core.handler;

import com.liem.ms.orderservice.command.events.OrderApprovedEvent;
import com.liem.ms.orderservice.command.events.OrderCreatedEvent;
import com.liem.ms.orderservice.command.events.OrderRejectedEvent;

/**
 * The interface Order events handler.
 */
public interface OrderEventsHandler {

  /**
   * On order created.
   *
   * @param event the event
   */
  void onCreated(OrderCreatedEvent event);

  /**
   * On approved.
   *
   * @param event the event
   */
  void onApproved(OrderApprovedEvent event);

  /**
   * On rejected.
   *
   * @param event the event
   */
  void onRejected(OrderRejectedEvent event);

}
