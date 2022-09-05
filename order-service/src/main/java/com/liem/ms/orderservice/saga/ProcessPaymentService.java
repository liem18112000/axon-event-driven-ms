package com.liem.ms.orderservice.saga;

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

  /**
   * Handle failed payment process.
   *
   * @param event the event
   */
  void handleFailedPaymentProcess(ProductReservedEvent event);
}
