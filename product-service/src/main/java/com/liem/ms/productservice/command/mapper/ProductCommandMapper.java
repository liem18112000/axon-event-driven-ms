package com.liem.ms.productservice.command.mapper;

import com.liem.ms.productservice.command.commands.common.DeleteCommand;
import com.liem.ms.productservice.command.commands.product.CreateProductCommand;
import com.liem.ms.productservice.command.commands.product.UpdateProductCommand;
import com.liem.ms.productservice.core.dto.ProductDto;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

/**
 * The type Product command mapper.
 */
@Component
public class ProductCommandMapper {

  /**
   * To create product command.
   *
   * @param dto the dto
   * @return the create product command
   */
  public CreateProductCommand toCreateCommand(
      final @NotNull ProductDto<String> dto) {
    return CreateProductCommand.builder()
        .name(dto.getName())
        .price(dto.getPrice())
        .description(dto.getDescription())
        .quantity(dto.getQuantity())
        .build();
  }

  /**
   * To update product command.
   *
   * @param dto the dto
   * @return the update product command
   */
  public UpdateProductCommand toUpdateCommand(
      final @NotNull ProductDto<String> dto) {
    return UpdateProductCommand.builder()
        .id(dto.getId())
        .name(dto.getName())
        .price(dto.getPrice())
        .description(dto.getDescription())
        .build();
  }

  /**
   * To delete command.
   *
   * @param dto the dto
   * @return the delete command
   */
  public DeleteCommand<String> toDeleteCommand(
      final @NotNull ProductDto<String> dto) {
    return DeleteCommand.<String>builder()
        .id(dto.getId())
        .build();
  }

}
