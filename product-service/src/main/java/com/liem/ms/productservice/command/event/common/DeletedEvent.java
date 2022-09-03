package com.liem.ms.productservice.command.event.common;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * The type Deleted event.
 *
 * @param <ID> the type parameter
 */
@Data
public class DeletedEvent<ID extends Serializable> implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 7041134948053741220L;

  /**
   * The Id.
   */
  @NotNull
  protected ID id;
}
