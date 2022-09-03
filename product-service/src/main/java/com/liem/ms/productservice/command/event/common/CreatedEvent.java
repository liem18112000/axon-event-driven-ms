package com.liem.ms.productservice.command.event.common;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Created event.
 *
 * @param <ID> the type parameter
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class CreatedEvent <ID extends Serializable> implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 3794766356615665292L;

  /**
   * The Id.
   */
  @NotNull
  protected ID id;

  /**
   * The Name.
   */
  @NotNull
  @NotBlank
  protected String name;

  /**
   * The Description.
   */
  protected String description;

}
