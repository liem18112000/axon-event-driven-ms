package com.liem.ms.orderservice.query.mapper;

import com.liem.ms.orderservice.core.dto.OrderDto;
import com.liem.ms.orderservice.core.dto.OrderSummary;
import com.liem.ms.orderservice.query.entity.OrderEntity;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

/**
 * The type Order dto mapper.
 */
@Component
public class OrderDtoMapper {

  /**
   * To dto order dto.
   *
   * @param entity the entity
   * @return the order dto
   */
  public OrderDto<String> toDto(final @NotNull OrderEntity entity) {
    return OrderDto.<String>builder()
        .id(entity.getId())
        .productId(entity.getProductId())
        .quantity(entity.getQuantity())
        .userId(entity.getUserId())
        .status(entity.getOrderStatus().name())
        .addressId(entity.getAddressId())
        .rejectReason(entity.getRejectReason())
        .build();
  }

  /**
   * To dto order summary.
   *
   * @param entity the entity
   * @return the order summary
   */
  public OrderSummary toSummary(final @NotNull OrderEntity entity) {
    return OrderSummary.builder()
        .orderId(entity.getId())
        .orderStatus(entity.getOrderStatus())
        .message(entity.getRejectReason())
        .build();
  }
}
