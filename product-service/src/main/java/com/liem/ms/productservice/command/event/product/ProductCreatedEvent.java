package com.liem.ms.productservice.command.event.product;

import com.liem.ms.productservice.command.event.common.CreatedEvent;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Product created event.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class ProductCreatedEvent extends CreatedEvent<String> {

  /**
   * The Price.
   */
  @NotNull
  private Float price;

}
