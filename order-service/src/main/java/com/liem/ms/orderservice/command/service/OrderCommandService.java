package com.liem.ms.orderservice.command.service;

import com.liem.ms.orderservice.core.dto.OrderDto;
import com.liem.ms.orderservice.core.dto.OrderSummary;
import java.io.Serializable;

/**
 * The interface Order command service.
 *
 * @param <ID> the type parameter
 */
public interface OrderCommandService<ID extends Serializable> {

  /**
   * Create order dto.
   *
   * @param dto the dto
   * @return the order dto
   */
  OrderSummary create(OrderDto<ID> dto);

  /**
   * Reject order dto.
   *
   * @param dto the dto
   * @return the order dto
   */
  OrderSummary reject(OrderDto<ID> dto);
}
