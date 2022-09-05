package com.liem.ms.orderservice.saga;

import com.liem.ms.orderservice.command.events.OrderCreatedEvent;

/**
 * The interface Reserve product service.
 */
public interface ReserveProductService {

  /**
   * Handle reserve product.
   *
   * @param event the event
   */
  void handleReserveProduct(OrderCreatedEvent event);

  /**
   * Handle failed product reserve.
   *
   * @param event the event
   */
  void handleFailedProductReserve(OrderCreatedEvent event);
}
