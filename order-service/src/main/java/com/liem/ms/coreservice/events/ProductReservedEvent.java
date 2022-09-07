package com.liem.ms.coreservice.events;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Product reserved event.
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class ProductReservedEvent implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = -7909698881600928396L;

  /**
   * The Id.
   */
  private String id;

  /**
   * The Quantity.
   */
  private Integer quantity;

  /**
   * The Order id.
   */
  private String orderId;

  /**
   * The User id.
   */
  private String userId;

}
