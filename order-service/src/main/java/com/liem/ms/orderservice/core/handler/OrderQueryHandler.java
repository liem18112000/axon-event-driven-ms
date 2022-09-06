package com.liem.ms.orderservice.core.handler;

import com.liem.ms.orderservice.core.dto.OrderDto;
import com.liem.ms.orderservice.core.dto.OrderSummary;
import com.liem.ms.orderservice.query.queries.FindDetailsOrderQuery;
import com.liem.ms.orderservice.query.queries.FindOrderSummaryQuery;

/**
 * The interface Order query handler.
 */
public interface OrderQueryHandler {

  /**
   * Handle find order query order dto.
   *
   * @param query the query
   * @return the order dto
   */
  OrderSummary handleFindOrderSummaryQuery(FindOrderSummaryQuery query);

  /**
   * Handle find order details query order dto.
   *
   * @param query the query
   * @return the order dto
   */
  OrderDto handleFindOrderDetailsQuery(FindDetailsOrderQuery query);

}
