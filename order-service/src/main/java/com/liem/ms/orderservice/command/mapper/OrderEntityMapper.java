package com.liem.ms.orderservice.command.mapper;

import com.liem.ms.orderservice.command.events.OrderCreatedEvent;
import com.liem.ms.orderservice.query.entity.OrderEntity;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

/**
 * The type Order entity mapper.
 */
@Component
public class OrderEntityMapper {

  /**
   * To entity order entity.
   *
   * @param event the event
   * @return the order entity
   */
  public OrderEntity toEntity(final @NotNull OrderCreatedEvent event) {
    var entity = new OrderEntity();
    entity.setId(event.getOrderId());
    entity.setOrderStatus(event.getOrderStatus());
    entity.setAddressId(event.getAddressId());
    entity.setQuantity(event.getQuantity());
    entity.setProductId(event.getProductId());
    entity.setUserId(event.getUserId());
    return entity;
  }
}
