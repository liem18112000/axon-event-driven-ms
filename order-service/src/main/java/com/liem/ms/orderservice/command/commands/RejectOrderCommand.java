package com.liem.ms.orderservice.command.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * The type Reject order command.
 */
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RejectOrderCommand {

  /**
   * The Order id.
   */
  @TargetAggregateIdentifier
  private String orderId;

  /**
   * The Reason.
   */
  private String reason;

}
