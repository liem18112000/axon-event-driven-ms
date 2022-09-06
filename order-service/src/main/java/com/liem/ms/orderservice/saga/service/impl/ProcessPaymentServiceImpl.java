package com.liem.ms.orderservice.saga.service.impl;

import static com.liem.ms.orderservice.saga.config.DeadlineManagerConfiguration.PAYMENT_PROCESS_DEADLINE;

import com.liem.ms.coreservice.commands.CancelProductReserveCommand;
import com.liem.ms.coreservice.commands.ProcessPaymentCommand;
import com.liem.ms.coreservice.events.ProductReservedEvent;
import com.liem.ms.coreservice.models.User;
import com.liem.ms.coreservice.queries.FetchUserPaymentDetailsQuery;
import com.liem.ms.orderservice.saga.service.ProcessPaymentService;
import java.time.Duration;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.deadline.DeadlineManager;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * The type Process payment service.
 */
@Slf4j
@Service
@AllArgsConstructor
public class ProcessPaymentServiceImpl implements ProcessPaymentService {

  /**
   * The Command gateway.
   */
  private final CommandGateway commandGateway;

  /**
   * The Query gateway.
   */
  private final QueryGateway queryGateway;

  /**
   * The Deadline manager.
   */
  private final DeadlineManager deadlineManager;

  /**
   * Handle process payment.
   *
   * @param event the event
   */
  @Override
  public void handleProcessPayment(final @NotNull ProductReservedEvent event) {
    final var orderId = event.getOrderId();
    final var userId = event.getUserId();
    try {
      final var userPaymentDetails = this.fetchUserPaymentDetails(userId);
      final var paymentDetails = userPaymentDetails.getPaymentDetails();
      final var scheduleId = this.deadlineManager.schedule(
          Duration.ofSeconds(5), PAYMENT_PROCESS_DEADLINE, event);
      log.info("Schedule deadline for '{}' by id '{}'", PAYMENT_PROCESS_DEADLINE, scheduleId);
      final var processPaymentCommand = ProcessPaymentCommand
          .builder()
          .scheduleId(scheduleId)
          .paymentId(UUID.randomUUID().toString())
          .orderId(orderId)
          .paymentDetails(paymentDetails)
          .build();
      log.info("Send payment process command: {}", processPaymentCommand);
      final String paymentId = commandGateway.sendAndWait(processPaymentCommand);
      if (!StringUtils.hasText(paymentId)) {
        throw new IllegalArgumentException("Payment id is null or empty");
      }
      log.info("Payment process '{}' completed", paymentId);
    } catch (Exception exception) {
      log.error("Payment process failed: {}", exception.getMessage());
      this.handleFailedPaymentProcess(event, String.format(
          "Payment process failed: %s", exception.getMessage()));
    }
  }

  /**
   * Handle failed payment process.
   *
   * @param event   the event
   * @param message the message
   */
  protected void handleFailedPaymentProcess(
      final @NotNull ProductReservedEvent event, String message) {
    final var command = CancelProductReserveCommand
        .builder()
        .productId(event.getId())
        .orderId(event.getOrderId())
        .quantity(event.getQuantity())
        .userId(event.getUserId())
        .reason(message)
        .build();
    commandGateway.send(command, (commandMessage, commandResultMessage) -> {
      log.trace("Command message: {}", commandMessage);
      log.trace("Command result message: {}", commandResultMessage);
      if (commandResultMessage.isExceptional()) {
        log.error("Cancel product reservation failed");
      } else {
        log.info("Reserved product reservation completed");
      }
    });
  }

  /**
   * Fetch user payment details user.
   *
   * @param userId the user id
   * @return the user
   */
  protected User fetchUserPaymentDetails(final String userId) {
    final var paymentDetailsQuery =
        FetchUserPaymentDetailsQuery.builder().userId(userId).build();
    log.info("Send user payment details query: {}", paymentDetailsQuery);
    final var userPaymentDetails = queryGateway.query(paymentDetailsQuery,
        ResponseTypes.instanceOf(User.class)).join();
    log.info("User payment details query: {}", userPaymentDetails);
    if (userPaymentDetails == null) {
      throw new IllegalArgumentException("User payment details query is null");
    }
    return userPaymentDetails;
  }
}
