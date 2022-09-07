package com.liem.ms.coreservice.events;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * The type Product cancel reserve event.
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class ProductCancelReserveEvent {

  /**
   * The Product id.
   */
  private String productId;

  /**
   * The Quantity
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

  /**
   * The Reason.
   */
  private String reason;

}
