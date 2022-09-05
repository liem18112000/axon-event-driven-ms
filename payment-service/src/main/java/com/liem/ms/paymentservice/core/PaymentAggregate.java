package com.liem.ms.paymentservice.core;

import com.liem.ms.coreservice.commands.ProcessPaymentCommand;
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
   * Instantiates a new Payment aggregate.
   *
   * @param command the command
   */
  @CommandHandler
  public PaymentAggregate(final @NotNull ProcessPaymentCommand command) {

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
  protected void onPaymentProcessed(
      final @NotNull PaymentProcessedEvent paymentProcessedEvent) {
    this.paymentId = paymentProcessedEvent.getPaymentId();
    this.orderId = paymentProcessedEvent.getOrderId();
  }
}
