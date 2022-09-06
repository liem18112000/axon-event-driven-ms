package com.liem.ms.productservice.query.mapper;

import com.liem.ms.productservice.core.dto.ProductDto;
import com.liem.ms.productservice.query.entity.ProductEntity;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

/**
 * The type Product dto mapper.
 */
@Component
public class ProductDtoMapper {

  /**
   * To dto product dto.
   *
   * @param entity the entity
   * @return the product dto
   */
  public ProductDto<String> toDto(final @NotNull ProductEntity entity) {
    return ProductDto.<String>builder()
        .id(entity.getId())
        .name(entity.getName())
        .description(entity.getDescription())
        .price(entity.getPrice())
        .quantity(entity.getQuantity())
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())
        .build();
  }
}
