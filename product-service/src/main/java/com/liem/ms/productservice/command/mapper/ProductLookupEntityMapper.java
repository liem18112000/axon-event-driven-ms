package com.liem.ms.productservice.command.mapper;

import com.liem.ms.productservice.command.entity.ProductLookupEntity;
import com.liem.ms.productservice.command.event.product.ProductCreatedEvent;
import com.liem.ms.productservice.command.event.product.ProductUpdatedEvent;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

/**
 * The type Product lookup entity mapper.
 */
@Component
public class ProductLookupEntityMapper {

  /**
   * To entity product lookup entity.
   *
   * @param event the event
   * @return the product lookup entity
   */
  public ProductLookupEntity toEntity(final @NotNull ProductCreatedEvent event) {
    var entity = new ProductLookupEntity();
    entity.setProductId(event.getId());
    entity.setProductName(event.getName());
    return entity;
  }

}
