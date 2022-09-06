package com.liem.ms.productservice.command.event.product;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Product supplied event.
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class ProductSuppliedEvent implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = -6673309761811201479L;

  /**
   * The Id.
   */
  private String id;

  /**
   * The Quantity.
   */
  private Integer quantity;

}
