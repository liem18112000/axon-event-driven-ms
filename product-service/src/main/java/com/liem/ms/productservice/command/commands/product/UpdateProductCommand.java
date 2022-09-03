package com.liem.ms.productservice.command.commands.product;

import com.liem.ms.productservice.command.commands.common.UpdateCommand;
import javax.validation.constraints.Min;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Update product command.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class UpdateProductCommand extends UpdateCommand<String> {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = -4697178832215123976L;

  /**
   * The Price.
   */
  @Min(value = 0, message = "Price must be positive")
  private Float price;

}
