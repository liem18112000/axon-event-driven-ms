package com.liem.ms.orderservice.command.service.impl;

import com.liem.ms.orderservice.command.mapper.OrderCommandMapper;
import com.liem.ms.orderservice.command.service.OrderCommandService;
import com.liem.ms.orderservice.core.dto.OrderDto;
import com.liem.ms.orderservice.core.exception.CommandServiceException;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Order command service.
 */
@Slf4j
@Service
@Transactional(rollbackFor = CommandServiceException.class)
@AllArgsConstructor
public class OrderCommandServiceImpl implements OrderCommandService<String> {

  /**
   * The Command mapper.
   */
  private final OrderCommandMapper commandMapper;

  /**
   * The Command gateway.
   */
  private final CommandGateway commandGateway;

  /**
   * Create order dto.
   *
   * @param dto the dto
   * @return the order dto
   */
  @Override
  public OrderDto<String> create(final @NotNull OrderDto<String> dto) {
    var command = this.commandMapper.toCreateCommand(dto);
    command.setOrderId(UUID.randomUUID().toString());
    try {
      log.info("Send create order command: {}", dto);
      final String orderId = this.commandGateway.sendAndWait(command);
      dto.setId(orderId);
      return dto;
    } catch (Exception exception) {
      log.error("Exception in order product: {} => {}", command, exception.getMessage());
      throw new CommandServiceException(
          String.format("Exception in create order: %s", exception.getMessage()), exception);
    }
  }

  /**
   * Approve order dto.
   *
   * @param dto the dto
   * @return the order dto
   */
  @Override
  public OrderDto<String> approve(OrderDto<String> dto) {
    return null;
  }

  /**
   * Reject order dto.
   *
   * @param dto the dto
   * @return the order dto
   */
  @Override
  public OrderDto<String> reject(OrderDto<String> dto) {
    return null;
  }

  /**
   * Delete order dto.
   *
   * @param dto the dto
   * @return the order dto
   */
  @Override
  public OrderDto<String> delete(OrderDto<String> dto) {
    return null;
  }
}
