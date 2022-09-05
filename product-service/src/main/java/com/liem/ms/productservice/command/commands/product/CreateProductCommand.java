package com.liem.ms.productservice.command.commands.product;

import com.liem.ms.productservice.command.commands.common.CreateCommand;
import javax.validation.constraints.NotNull;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Create product command.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class CreateProductCommand extends CreateCommand<String> {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = -2144948809545759168L;

  /**
   * The Price.
   */
  private Float price;

  /**
   * The Quantity.
   */
  @NotNull
  @Default
  private Integer quantity = 1;

}
