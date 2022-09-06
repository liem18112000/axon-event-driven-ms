package com.liem.ms.orderservice.saga.service;

import com.liem.ms.coreservice.events.ProductReservedEvent;

/**
 * The interface Process payment service.
 */
public interface ProcessPaymentService {

  /**
   * Handle process payment.
   *
   * @param event the event
   */
  void handleProcessPayment(ProductReservedEvent event);
}
