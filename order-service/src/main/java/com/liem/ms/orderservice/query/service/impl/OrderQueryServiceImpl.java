package com.liem.ms.orderservice.query.service.impl;

import com.liem.ms.orderservice.core.dto.OrderDto;
import com.liem.ms.orderservice.query.queries.FindDetailsOrderQuery;
import com.liem.ms.orderservice.query.queries.FindOrderSummaryQuery;
import com.liem.ms.orderservice.query.service.OrderQueryService;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class OrderQueryServiceImpl implements OrderQueryService {

  private final QueryGateway gateway;

  /**
   * Query order by id order dto.
   *
   * @param orderId the order id
   * @return the order dto
   */
  @Override
  public OrderDto queryOrderById(final @NotNull String orderId) {
    final var query = FindDetailsOrderQuery.builder().orderId(orderId).build();
    return this.gateway.query(query, ResponseTypes.instanceOf(OrderDto.class)).join();
  }
}
