package com.liem.ms.productservice.command.commands.product;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@SuperBuilder
@NoArgsConstructor
public class SupplyProductCommand implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 883208773811975188L;

  /**
   * The Product id.
   */
  @TargetAggregateIdentifier
  private String productId;

  /**
   * The Quantity.
   */
  private Integer quantity;

}
