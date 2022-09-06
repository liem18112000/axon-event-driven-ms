package com.liem.ms.orderservice.saga.saga.impl;

import com.liem.ms.coreservice.events.PaymentCanceledEvent;
import com.liem.ms.coreservice.events.PaymentProcessedEvent;
import com.liem.ms.coreservice.events.ProductCancelReserveEvent;
import com.liem.ms.coreservice.events.ProductReservedEvent;
import com.liem.ms.orderservice.command.events.OrderApprovedEvent;
import com.liem.ms.orderservice.command.events.OrderCreatedEvent;
import com.liem.ms.orderservice.command.events.OrderRejectedEvent;
import com.liem.ms.orderservice.core.dto.OrderSummary;
import com.liem.ms.orderservice.query.queries.FindOrderSummaryQuery;
import com.liem.ms.orderservice.saga.saga.OrderSaga;
import com.liem.ms.orderservice.saga.service.ApproveOrderService;
import com.liem.ms.orderservice.saga.service.CancelProductReservationService;
import com.liem.ms.orderservice.saga.service.ProcessPaymentService;
import com.liem.ms.orderservice.saga.service.RejectOrderService;
import com.liem.ms.orderservice.saga.service.ReserveProductService;
import javax.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The type Order saga.
 */
@Slf4j
@Saga
@NoArgsConstructor
public class OrderSagaImpl implements OrderSaga {

  /**
   * The constant ORDER_ID.
   */
  public static final String ORDER_ID = "orderId";

  /**
   * The Reserve product service.
   */
  @Autowired
  private transient ReserveProductService reserveProductService;

  /**
   * The Process payment service.
   */
  @Autowired
  private transient ProcessPaymentService processPaymentService;

  /**
   * The Approve order service.
   */
  @Autowired
  private transient ApproveOrderService approveOrderService;

  /**
   * The Reject order service.
   */
  @Autowired
  private transient RejectOrderService rejectOrderService;

  /**
   * The Cancel product reservation service.
   */
  @Autowired
  private transient CancelProductReservationService cancelProductReservationService;

  /**
   * The Query update emitter.
   */
  @Autowired
  private transient QueryUpdateEmitter queryUpdateEmitter;

  /**
   * On created order.
   *
   * @param event the event
   */
  @Override
  @StartSaga
  @SagaEventHandler(associationProperty = ORDER_ID)
  public void onCreatedOrder(final @NotNull OrderCreatedEvent event) {
    log.info("Start transaction : handle after created order: {}", event);
    this.reserveProductService.handleReserveProduct(event);
  }

  /**
   * On reserved product.
   *
   * @param event the event
   */
  @Override
  @SagaEventHandler(associationProperty = ORDER_ID)
  public void onReservedProduct(final @NotNull ProductReservedEvent event) {
    log.info("Handle after reserve product: {}", event);
    this.processPaymentService.handleProcessPayment(event);
  }

  /**
   * On processed payment.
   *
   * @param event the event
   */
  @Override
  @SagaEventHandler(associationProperty = ORDER_ID)
  public void onProcessedPayment(final @NotNull PaymentProcessedEvent event) {
    log.info("Handle after payment process: {}", event);
    this.approveOrderService.handleApproveOrder(event);
  }

  /**
   * On approved order.
   *
   * @param event the event
   */
  @Override
  @EndSaga
  @SagaEventHandler(associationProperty = ORDER_ID)
  public void onApprovedOrder(final @NotNull OrderApprovedEvent event) {
    log.info("Complete transaction : handle after approved order '{}'", event);
    final var orderSummary = OrderSummary.builder()
        .orderId(event.getOrderId())
        .orderStatus(event.getOrderStatus())
        .build();
    queryUpdateEmitter.emit(FindOrderSummaryQuery.class, query -> true, orderSummary);
  }

  /**
   * On cancel payment.
   *
   * @param event the event
   */
  @Override
  @SagaEventHandler(associationProperty = ORDER_ID)
  public void onCancelPayment(PaymentCanceledEvent event) {
    log.info("Handle after payment canceled: {}", event);
    this.cancelProductReservationService.handleCancelProductReservation(event);
  }


  /**
   * On canceled product reservation.
   *
   * @param event the event
   */
  @Override
  @SagaEventHandler(associationProperty = ORDER_ID)
  public void onCanceledProductReservation(final @NotNull ProductCancelReserveEvent event) {
    log.info("Handle after cancel product reservation process: {}", event);
    this.rejectOrderService.handleRejectOrder(event);
  }

  /**
   * On rejected order.
   *
   * @param event the event
   */
  @Override
  @EndSaga
  @SagaEventHandler(associationProperty = ORDER_ID)
  public void onRejectedOrder(OrderRejectedEvent event) {
    log.info("Complete transaction : handle after reject order: {}", event);
    final var orderSummary = OrderSummary.builder()
        .orderId(event.getOrderId())
        .orderStatus(event.getOrderStatus())
        .message(event.getReason())
        .build();
    queryUpdateEmitter.emit(FindOrderSummaryQuery.class, query -> true, orderSummary);
  }
}
