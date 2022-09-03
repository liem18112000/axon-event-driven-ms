package com.liem.ms.productservice.command.service.impl;

import com.liem.ms.productservice.command.mapper.ProductCommandMapper;
import com.liem.ms.productservice.command.service.ProductCommandService;
import com.liem.ms.productservice.core.dto.ProductDto;
import com.liem.ms.productservice.core.exception.CommandServiceException;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Product command service.
 */
@Slf4j
@Service
@Transactional(rollbackFor = CommandServiceException.class)
@AllArgsConstructor
public class ProductCommandServiceImpl implements ProductCommandService<String> {

  /**
   * The Command mapper.
   */
  private final ProductCommandMapper commandMapper;

  /**
   * The Command gateway.
   */
  private final CommandGateway commandGateway;

  /**
   * Create product dto.
   *
   * @param dto the dto
   * @return the product dto
   */
  @Override
  public ProductDto<String> create(final @NotNull ProductDto<String> dto) {
    var command = this.commandMapper.toCreateCommand(dto);
    command.setId(UUID.randomUUID().toString());
    try {
      log.info("Send create product command: {}", dto);
      final String productId = this.commandGateway.sendAndWait(command);
      dto.setId(productId);
      return dto;
    } catch (Exception exception) {
      log.error("Exception in create product: {} => {}", command, exception.getMessage());
      throw new CommandServiceException(
          String.format("Exception in create product: %s", exception.getMessage()), exception);
    }
  }

  /**
   * Update product dto.
   *
   * @param dto the dto
   * @return the product dto
   */
  @Override
  public ProductDto<String> update(ProductDto<String> dto) {
    final var command = this.commandMapper.toUpdateCommand(dto);
    try {
      log.info("Send update product command: {}", dto);
      final String productId = this.commandGateway.sendAndWait(command);
      dto.setId(productId);
      return dto;
    } catch (Exception exception) {
      log.error("Exception in update product: {} => {}", command, exception.getMessage());
      throw new CommandServiceException(
          String.format("Exception in update product: %s", exception.getMessage()), exception);
    }
  }

  /**
   * Delete product dto.
   *
   * @param dto the dto
   * @return the product dto
   */
  @Override
  public ProductDto<String> delete(ProductDto<String> dto) {
    final var command = this.commandMapper.toDeleteCommand(dto);
    try {
      log.info("Send delete product command: {}", dto);
      final String productId = this.commandGateway.sendAndWait(command);
      dto.setId(productId);
      return dto;
    } catch (Exception exception) {
      log.error("Exception in delete product: {}", command, exception);
      throw new CommandServiceException("Exception in delete product", exception);
    }
  }
}
