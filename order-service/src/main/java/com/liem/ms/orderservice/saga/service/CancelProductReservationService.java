package com.liem.ms.orderservice.saga.service;

import com.liem.ms.coreservice.events.PaymentCanceledEvent;
import com.liem.ms.coreservice.events.ProductReservedEvent;
import com.liem.ms.orderservice.command.events.OrderCreatedEvent;

/**
 * The interface Cancel product reservation service.
 */
public interface CancelProductReservationService {

  /**
   * Handle cancel product reservation.
   *
   * @param event         the event
   * @param rejectMessage the reject message
   */
  void handleCancelProductReservation(OrderCreatedEvent event, String rejectMessage);

  /**
   * Handle cancel product reservation.
   *
   * @param event         the event
   * @param rejectMessage the reject message
   */
  void handleCancelProductReservation(ProductReservedEvent event, String rejectMessage);

  /**
   * Handle cancel product reservation.
   *
   * @param event the event
   */
  void handleCancelProductReservation(PaymentCanceledEvent event);
}
