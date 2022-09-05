package com.liem.ms.orderservice.saga;

import com.liem.ms.coreservice.events.ProductReservedEvent;
import com.liem.ms.orderservice.command.events.OrderCreatedEvent;

/**
 * The interface Order saga.
 */
public interface OrderSaga {

  /**
   * Handle created order.
   *
   * @param event the event
   */
  void onCreatedOrder(OrderCreatedEvent event);

  /**
   * Handle reserve product.
   *
   * @param event the event
   */
  void onReservedProduct(ProductReservedEvent event);
}
