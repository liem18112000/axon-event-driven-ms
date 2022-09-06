package com.liem.ms.orderservice.saga.saga;

import com.liem.ms.coreservice.events.PaymentCanceledEvent;
import com.liem.ms.coreservice.events.PaymentProcessedEvent;
import com.liem.ms.coreservice.events.ProductCancelReserveEvent;
import com.liem.ms.coreservice.events.ProductReservedEvent;
import com.liem.ms.orderservice.command.events.OrderApprovedEvent;
import com.liem.ms.orderservice.command.events.OrderCreatedEvent;
import com.liem.ms.orderservice.command.events.OrderRejectedEvent;

/**
 * The interface Order saga.
 */
public interface OrderSaga {

  /**
   * On created order.
   *
   * @param event the event
   */
  void onCreatedOrder(OrderCreatedEvent event);

  /**
   * On reserved product.
   *
   * @param event the event
   */
  void onReservedProduct(ProductReservedEvent event);

  /**
   * On processed payment.
   *
   * @param event the event
   */
  void onProcessedPayment(PaymentProcessedEvent event);

  /**
   * On approved order.
   *
   * @param event the event
   */
  void onApprovedOrder(OrderApprovedEvent event);

  /**
   * On cancel payment.
   *
   * @param event the event
   */
  void onCancelPayment(PaymentCanceledEvent event);

  /**
   * On canceled product reservation.
   *
   * @param event the event
   */
  void onCanceledProductReservation(ProductCancelReserveEvent event);

  /**
   * On rejected order.
   *
   * @param event the event
   */
  void onRejectedOrder(OrderRejectedEvent event);
}
