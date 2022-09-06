package com.liem.ms.orderservice.query.queries;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Find order query.
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class FindDetailsOrderQuery implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 3961506037599885395L;

  /**
   * The Order id.
   */
  private String orderId;

}
