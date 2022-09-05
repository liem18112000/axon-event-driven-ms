package com.liem.ms.orderservice.saga.impl;

import com.liem.ms.coreservice.commands.ProcessPaymentCommand;
import com.liem.ms.coreservice.events.ProductReservedEvent;
import com.liem.ms.coreservice.models.User;
import com.liem.ms.coreservice.queries.FetchUserPaymentDetailsQuery;
import com.liem.ms.orderservice.saga.ProcessPaymentService;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
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
      final var processPaymentCommand = ProcessPaymentCommand
          .builder()
          .paymentId(UUID.randomUUID().toString())
          .orderId(orderId)
          .paymentDetails(paymentDetails)
          .build();
      log.info("Send payment process command: {}", processPaymentCommand);
      final String paymentId = commandGateway.sendAndWait(
          processPaymentCommand, 5, TimeUnit.SECONDS);
      if (!StringUtils.hasText(paymentId)) {
        throw new IllegalArgumentException("Payment id is null or empty");
      }
      log.info("Payment process completed: {}", paymentId);
    } catch (Exception exception) {
      log.error("Payment process failed: {}", exception.getMessage());
      this.handleFailedPaymentProcess(event);
    }
  }

  /**
   * Handle failed payment process.
   *
   * @param event the event
   */
  @Override
  public void handleFailedPaymentProcess(ProductReservedEvent event) {

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
