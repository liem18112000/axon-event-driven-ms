package com.liem.ms.orderservice.saga.service;

import com.liem.ms.coreservice.events.PaymentProcessedEvent;

/**
 * The interface Cancel payment service.
 */
public interface CancelPaymentService {

  /**
   * Handle cancel payment.
   *
   * @param event        the event
   * @param cancelReason the cancel reason
   */
  void handleCancelPayment(PaymentProcessedEvent event, String cancelReason);

}
