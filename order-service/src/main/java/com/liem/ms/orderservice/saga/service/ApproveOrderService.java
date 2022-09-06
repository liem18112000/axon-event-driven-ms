package com.liem.ms.orderservice.saga.service;

import com.liem.ms.coreservice.events.PaymentProcessedEvent;

/**
 * The interface Approve order service.
 */
public interface ApproveOrderService {

  /**
   * Handle approve order.
   *
   * @param event the event
   */
  void handleApproveOrder(PaymentProcessedEvent event);
}
