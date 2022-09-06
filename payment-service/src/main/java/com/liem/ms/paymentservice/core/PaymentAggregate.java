package com.liem.ms.paymentservice.core;

import com.liem.ms.coreservice.commands.CancelPaymentCommand;
import com.liem.ms.coreservice.commands.ProcessPaymentCommand;
import com.liem.ms.coreservice.events.PaymentCanceledEvent;
import com.liem.ms.coreservice.events.PaymentProcessedEvent;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

/**
 * The type Payment aggregate.
 */
@Aggregate
@Data
@Slf4j
@NoArgsConstructor
public class PaymentAggregate {

  /**
   * The Payment id.
   */
  @AggregateIdentifier
  private String paymentId;

  /**
   * The Order id.
   */
  private String orderId;

  /**
   * The Status.
   */
  private PaymentStatus status;

  /**
   * The Cancel reason.
   */
  private String cancelReason;

  /**
   * Instantiates a new Payment aggregate.
   *
   * @param command the command
   */
  @CommandHandler
  public PaymentAggregate(final @NotNull ProcessPaymentCommand command) {

    log.info("Process payment command handle: {}", command);

    if (command.getPaymentDetails() == null) {
      throw new IllegalArgumentException("Missing payment details");
    }

    if (command.getOrderId() == null) {
      throw new IllegalArgumentException("Missing orderId");
    }

    if (command.getPaymentId() == null) {
      throw new IllegalArgumentException("Missing paymentId");
    }

    final var event = PaymentProcessedEvent
        .builder()
        .paymentId(command.getPaymentId())
        .scheduleId(command.getScheduleId())
        .orderId(command.getOrderId())
        .build();

    AggregateLifecycle.apply(event);
  }

  /**
   * On.
   *
   * @param paymentProcessedEvent the payment processed event
   */
  @EventSourcingHandler
  public void onPaymentProcessed(
      final @NotNull PaymentProcessedEvent paymentProcessedEvent) {
    log.info("Payment processed event sourcing handle: {}", paymentProcessedEvent);
    this.paymentId = paymentProcessedEvent.getPaymentId();
    this.orderId = paymentProcessedEvent.getOrderId();
    this.status = PaymentStatus.PROCESSED;
  }

  @CommandHandler
  public void cancelPayment(final @NotNull CancelPaymentCommand command) {

    log.info("Cancel payment command handle: {}", command);

    final var event = PaymentCanceledEvent
        .builder()
        .paymentId(command.getPaymentId())
        .scheduleId(command.getScheduleId())
        .cancelReason(command.getCancelReason())
        .orderId(command.getOrderId())
        .build();

    AggregateLifecycle.apply(event);
  }

  @EventSourcingHandler
  public void onPaymentCanceled(final @NotNull PaymentCanceledEvent event) {
    log.info("Payment canceled event sourcing handle: {}", event);
    this.paymentId = event.getPaymentId();
    this.status = PaymentStatus.CANCELED;
  }
}
