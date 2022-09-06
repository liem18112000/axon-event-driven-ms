package com.liem.ms.orderservice.command.service.impl;

import com.liem.ms.orderservice.command.mapper.OrderCommandMapper;
import com.liem.ms.orderservice.command.service.OrderCommandService;
import com.liem.ms.orderservice.core.dto.OrderDto;
import com.liem.ms.orderservice.core.dto.OrderSummary;
import com.liem.ms.orderservice.core.exception.CommandServiceException;
import com.liem.ms.orderservice.query.queries.FindOrderSummaryQuery;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Order command service.
 */
@Slf4j
@Service
@Transactional(rollbackFor = CommandServiceException.class)
@AllArgsConstructor
public class OrderCommandServiceImpl implements OrderCommandService<String> {

  /**
   * The Command mapper.
   */
  private final OrderCommandMapper commandMapper;

  /**
   * The Command gateway.
   */
  private final CommandGateway commandGateway;

  /**
   * The Query gateway.
   */
  private final QueryGateway queryGateway;

  /**
   * Create order dto.
   *
   * @param dto the dto
   * @return the order dto
   */
  @Override
  public OrderSummary create(final @NotNull OrderDto<String> dto) {
    var command = this.commandMapper.toCreateCommand(dto);
    final var assignedOrderId = UUID.randomUUID().toString();
    command.setOrderId(assignedOrderId);
    final var query = FindOrderSummaryQuery.builder().orderId(assignedOrderId).build();
    try (var queryResult =
        this.queryGateway.subscriptionQuery(query,
        ResponseTypes.instanceOf(OrderSummary.class),
        ResponseTypes.instanceOf(OrderSummary.class))) {
      log.info("Send create order command: {}", dto);
      this.commandGateway.sendAndWait(command);
      return queryResult.updates().blockFirst();
    } catch (Exception exception) {
      log.error("Exception in order product: {} => {}", command, exception.getMessage());
      throw new CommandServiceException(
          String.format("Exception in create order: %s", exception.getMessage()), exception);
    } finally {
      log.info("Query subscription close");
    }
  }

  /**
   * Reject order dto.
   *
   * @param dto the dto
   * @return the order dto
   */
  @Override
  public OrderSummary reject(OrderDto<String> dto) {
    return null;
  }
}
