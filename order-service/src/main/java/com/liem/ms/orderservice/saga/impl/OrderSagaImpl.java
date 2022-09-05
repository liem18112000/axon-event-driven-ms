package com.liem.ms.orderservice.saga.impl;

import com.liem.ms.coreservice.events.ProductReservedEvent;
import com.liem.ms.orderservice.command.events.OrderCreatedEvent;
import com.liem.ms.orderservice.saga.OrderSaga;
import com.liem.ms.orderservice.saga.ProcessPaymentService;
import com.liem.ms.orderservice.saga.ReserveProductService;
import javax.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
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
   * Handle created order.
   *
   * @param event the event
   */
  @Override
  @StartSaga
  @SagaEventHandler(associationProperty = ORDER_ID)
  public void onCreatedOrder(final @NotNull OrderCreatedEvent event) {
    log.info("Handle after created order: {}", event);
    this.reserveProductService.handleFailedProductReserve(event);
  }

  /**
   * Handle reserve product.
   *
   * @param event the event
   */
  @Override
  @SagaEventHandler(associationProperty = "orderId")
  public void onReservedProduct(final @NotNull ProductReservedEvent event) {
    log.info("Handle after reserve product: {}", event);
    this.processPaymentService.handleProcessPayment(event);
  }
}
