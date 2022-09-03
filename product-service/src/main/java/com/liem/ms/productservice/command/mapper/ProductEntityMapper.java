package com.liem.ms.productservice.command.mapper;

import com.liem.ms.productservice.command.event.product.ProductCreatedEvent;
import com.liem.ms.productservice.command.event.product.ProductUpdatedEvent;
import com.liem.ms.productservice.query.entity.ProductEntity;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

/**
 * The type Product entity mapper.
 */
@Component
public class ProductEntityMapper {

  /**
   * To entity product entity.
   *
   * @param event the event
   * @return the product entity
   */
  public ProductEntity toEntity(final @NotNull ProductCreatedEvent event) {
    var entity = new ProductEntity();
    entity.setId(event.getId());
    entity.setName(event.getName());
    entity.setDescription(event.getDescription());
    entity.setPrice(event.getPrice());
    return entity;
  }

  /**
   * To entity product entity.
   *
   * @param event the event
   * @return the product entity
   */
  public ProductEntity toEntity(final @NotNull ProductUpdatedEvent event) {
    var entity = new ProductEntity();
    entity.setName(event.getName());
    entity.setDescription(event.getDescription());
    entity.setPrice(event.getPrice());
    return entity;
  }

}
