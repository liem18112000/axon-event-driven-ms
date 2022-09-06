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
public class FindOrderSummaryQuery implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 4028218821736032865L;

  /**
   * The Order id.
   */
  private String orderId;

}
