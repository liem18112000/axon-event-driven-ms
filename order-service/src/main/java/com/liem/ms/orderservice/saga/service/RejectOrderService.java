package com.liem.ms.orderservice.saga.service;

import com.liem.ms.coreservice.events.ProductCancelReserveEvent;

/**
 * The interface Reject order service.
 */
public interface RejectOrderService {

  /**
   * Handle reject order.
   *
   * @param event the event
   */
  void handleRejectOrder(ProductCancelReserveEvent event);
}
