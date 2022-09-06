package com.liem.ms.orderservice.saga.handler;

import com.liem.ms.coreservice.events.ProductReservedEvent;

/**
 * The interface Order deadline handler.
 */
public interface OrderDeadlineHandler {

  /**
   * Handle payment process deadline.
   *
   * @param event the event
   */
  public void handlePaymentProcessDeadline(ProductReservedEvent event);
}
