package com.liem.ms.orderservice.query.service;

import com.liem.ms.orderservice.core.dto.OrderDto;

/**
 * The interface Order query service.
 *
 * @param <ID> the type parameter
 */
public interface OrderQueryService {

  /**
   * Query order by id order dto.
   *
   * @param orderId the order id
   * @return the order dto
   */
  OrderDto queryOrderById(String orderId);
}
