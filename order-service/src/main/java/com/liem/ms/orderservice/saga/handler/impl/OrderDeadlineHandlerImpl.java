package com.liem.ms.orderservice.saga.handler.impl;

import static com.liem.ms.orderservice.saga.config.DeadlineManagerConfiguration.PAYMENT_PROCESS_DEADLINE;

import com.liem.ms.coreservice.events.ProductReservedEvent;
import com.liem.ms.orderservice.saga.handler.OrderDeadlineHandler;
import com.liem.ms.orderservice.saga.service.CancelProductReservationService;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.deadline.annotation.DeadlineHandler;
import org.springframework.stereotype.Component;

/**
 * The type Order deadline handler.
 */
@Slf4j
@AllArgsConstructor
@Component
public class OrderDeadlineHandlerImpl implements OrderDeadlineHandler {

  /**
   * The Cancel product reservation service.
   */
  private final CancelProductReservationService cancelProductReservationService;

  /**
   * Handle payment process deadline.
   *
   * @param event the event
   */
  @Override
  @DeadlineHandler(deadlineName = PAYMENT_PROCESS_DEADLINE)
  public void handlePaymentProcessDeadline(final @NotNull ProductReservedEvent event) {
    log.info("Deadline handle '{}': {} => Payment timeout",
        PAYMENT_PROCESS_DEADLINE, event);
    this.cancelProductReservationService.handleCancelProductReservation(
        event, "Payment timeout");
  }
}
