package com.liem.ms.productservice.command.commands.common;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * The type Create command.
 *
 * @param <ID> the type parameter
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class CreateCommand<ID extends Serializable> implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 5835237285032047302L;

  /**
   * The Id.
   */
  @TargetAggregateIdentifier
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
