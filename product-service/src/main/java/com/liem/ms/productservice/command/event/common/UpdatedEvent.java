package com.liem.ms.productservice.command.event.common;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Updated event.
 *
 * @param <ID> the type parameter
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class UpdatedEvent<ID extends Serializable> implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 8116104210758965854L;

  /**
   * The Id.
   */
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
