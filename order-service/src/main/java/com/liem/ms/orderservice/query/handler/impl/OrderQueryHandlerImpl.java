package com.liem.ms.orderservice.query.handler.impl;

import com.liem.ms.orderservice.core.dto.OrderDto;
import com.liem.ms.orderservice.core.dto.OrderSummary;
import com.liem.ms.orderservice.core.handler.OrderQueryHandler;
import com.liem.ms.orderservice.query.mapper.OrderDtoMapper;
import com.liem.ms.orderservice.query.queries.FindDetailsOrderQuery;
import com.liem.ms.orderservice.query.queries.FindOrderSummaryQuery;
import com.liem.ms.orderservice.query.repository.OrdersRepository;
import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Order query handler.
 */
@Slf4j
@Component
@AllArgsConstructor
@Transactional(readOnly = true)
public class OrderQueryHandlerImpl implements OrderQueryHandler {

  /**
   * The Repository.
   */
  private final OrdersRepository repository;

  /**
   * The Mapper.
   */
  private final OrderDtoMapper mapper;

  /**
   * Handle find order query order dto.
   *
   * @param query the query
   * @return the order dto
   */
  @Override
  @QueryHandler
  public OrderSummary handleFindOrderSummaryQuery(final @NotNull FindOrderSummaryQuery query) {
    final var orderId = query.getOrderId();
    return this.repository.findById(orderId)
        .map(this.mapper::toSummary)
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("Order not found by id '%s'", orderId)));
  }

  /**
   * Handle find order details query order dto.
   *
   * @param query the query
   * @return the order dto
   */
  @Override
  @QueryHandler
  public OrderDto handleFindOrderDetailsQuery(final @NotNull FindDetailsOrderQuery query) {
    final var orderId = query.getOrderId();
    return this.repository.findById(orderId)
        .map(this.mapper::toDto)
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("Order not found by id '%s'", orderId)));
  }
}
