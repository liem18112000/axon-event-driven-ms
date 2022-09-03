package com.liem.ms.productservice.command.commands.common;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * The type Update command.
 *
 * @param <ID> the type parameter
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class UpdateCommand<ID extends Serializable> implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = -4652106434134765666L;

  /**
   * The Id.
   */
  @TargetAggregateIdentifier
  @NotNull
  protected ID id;

  /**
   * The Name.
   */
  protected String name;

  /**
   * The Description.
   */
  protected String description;

}
