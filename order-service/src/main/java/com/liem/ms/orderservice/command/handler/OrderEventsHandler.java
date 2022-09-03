package com.liem.ms.orderservice.command.handler;

import com.liem.ms.orderservice.command.events.OrderCreatedEvent;

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

}
