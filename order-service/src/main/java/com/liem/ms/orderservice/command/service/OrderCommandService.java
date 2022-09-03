package com.liem.ms.orderservice.command.service;

import com.liem.ms.orderservice.core.dto.OrderDto;
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
  OrderDto<ID> create(OrderDto<ID> dto);

  /**
   * Approve order dto.
   *
   * @param dto the dto
   * @return the order dto
   */
  OrderDto<ID> approve(OrderDto<ID> dto);

  /**
   * Reject order dto.
   *
   * @param dto the dto
   * @return the order dto
   */
  OrderDto<ID> reject(OrderDto<ID> dto);

  /**
   * Delete order dto.
   *
   * @param dto the dto
   * @return the order dto
   */
  OrderDto<ID> delete(OrderDto<ID> dto);
}
