package com.liem.ms.coreservice.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * The type Cancel product reserve command.
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class CancelProductReserveCommand {

  /**
   * The Product id.
   */
  @TargetAggregateIdentifier
  private String productId;

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

  /**
   * The Reason.
   */
  private String reason;

}
