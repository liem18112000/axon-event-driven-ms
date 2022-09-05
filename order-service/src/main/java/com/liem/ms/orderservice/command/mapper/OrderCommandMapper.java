package com.liem.ms.orderservice.command.mapper;

import com.liem.ms.coreservice.commands.ReserveProductCommand;
import com.liem.ms.orderservice.command.commands.CreateOrderCommand;
import com.liem.ms.orderservice.command.events.OrderCreatedEvent;
import com.liem.ms.orderservice.core.dto.OrderDto;
import com.liem.ms.orderservice.core.model.OrderStatus;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

/**
 * The type Order command mapper.
 */
@Component
public class OrderCommandMapper {

  /**
   * To create command create order command.
   *
   * @param dto the dto
   * @return the create order command
   */
  public CreateOrderCommand toCreateCommand(final @NotNull OrderDto<String> dto) {
    return CreateOrderCommand.builder()
        .addressId(dto.getAddressId())
        .productId(dto.getProductId())
        .userId(dto.getUserId())
        .quantity(dto.getQuantity())
        .orderStatus(OrderStatus.CREATED)
        .build();
  }

  /**
   * To reserve product command reserve product command.
   *
   * @param event the event
   * @return the reserve product command
   */
  public ReserveProductCommand toReserveProductCommand(final @NotNull OrderCreatedEvent event) {
    return ReserveProductCommand.builder()
        .orderId(event.getOrderId())
        .productId(event.getProductId())
        .quantity(event.getQuantity())
        .userId(event.getUserId())
        .build();
  }

}
