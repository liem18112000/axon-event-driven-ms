package com.liem.ms.productservice.command.event.product;

import com.liem.ms.productservice.command.event.common.UpdatedEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Product updated event.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class ProductUpdatedEvent extends UpdatedEvent<String> {

  /**
   * The Price.
   */
  private Float price;
}
