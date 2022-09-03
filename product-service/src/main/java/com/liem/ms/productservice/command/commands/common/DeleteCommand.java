package com.liem.ms.productservice.command.commands.common;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * The type Delete command.
 *
 * @param <ID> the type parameter
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class DeleteCommand <ID extends Serializable> implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = -8443817803996334402L;

  /**
   * The Id.
   */
  @TargetAggregateIdentifier
  @NotNull
  protected ID id;
}
