package com.liem.ms.coreservice.commands;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * The type Reserve product command.
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class ReserveProductCommand implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 2166855334346189781L;

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

}
