package com.liem.ms.orderservice.command.mapper;

import com.liem.ms.coreservice.commands.CancelProductReserveCommand;
import com.liem.ms.coreservice.commands.ReserveProductCommand;
import com.liem.ms.coreservice.events.PaymentCanceledEvent;
import com.liem.ms.coreservice.events.ProductReservedEvent;
import com.liem.ms.orderservice.command.commands.CreateOrderCommand;
import com.liem.ms.orderservice.command.events.OrderCreatedEvent;
import com.liem.ms.orderservice.command.repository.OrderLookupRepository;
import com.liem.ms.orderservice.core.dto.OrderDto;
import com.liem.ms.orderservice.core.model.OrderStatus;
import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * The type Order command mapper.
 */
@Component
@AllArgsConstructor
public class OrderCommandMapper {

  /**
   * The Order lookup repository.
   */
  private final OrderLookupRepository orderLookupRepository;

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

  /**
   * To cancel product reserve command cancel product reserve command.
   *
   * @param event         the event
   * @param rejectMessage the reject message
   * @return the cancel product reserve command
   */
  public CancelProductReserveCommand toCancelProductReserveCommand(
      final @NotNull OrderCreatedEvent event, final @NotNull String rejectMessage) {
    return CancelProductReserveCommand
        .builder()
        .productId(event.getProductId())
        .orderId(event.getOrderId())
        .quantity(event.getQuantity())
        .userId(event.getUserId())
        .reason(rejectMessage)
        .build();
  }

  /**
   * To cancel product reserve command cancel product reserve command.
   *
   * @param event         the event
   * @param rejectMessage the reject message
   * @return the cancel product reserve command
   */
  public CancelProductReserveCommand toCancelProductReserveCommand(
      final @NotNull ProductReservedEvent event, final @NotNull String rejectMessage) {
    return CancelProductReserveCommand
        .builder()
        .productId(event.getId())
        .orderId(event.getOrderId())
        .quantity(event.getQuantity())
        .userId(event.getUserId())
        .reason(rejectMessage)
        .build();
  }

  /**
   * To cancel product reserve command cancel product reserve command.
   *
   * @param event the event
   * @return the cancel product reserve command
   */
  public CancelProductReserveCommand toCancelProductReserveCommand(
      final @NotNull PaymentCanceledEvent event) {
    final var lookupOrder = this.orderLookupRepository
        .findById(event.getOrderId())
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("Order lookup not found by id '%s'", event.getOrderId())));
    return CancelProductReserveCommand
        .builder()
        .productId(lookupOrder.getProductId())
        .orderId(event.getOrderId())
        .quantity(lookupOrder.getQuantity())
        .userId(lookupOrder.getUserId())
        .reason(event.getCancelReason())
        .build();
  }

}
