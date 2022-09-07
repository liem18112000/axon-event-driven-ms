package com.liem.ms.coreservice.commands;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * The type Cancel payment command.
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class CancelPaymentCommand implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = -3518000331802781543L;

  /**
   * The Payment id.
   */
  @TargetAggregateIdentifier
  private String paymentId;

  /**
   * The Schedule id.
   */
  protected String scheduleId;

  /**
   * The Order id.
   */
  protected String orderId;

  /**
   * The Cancel reason.
   */
  protected String cancelReason;

}
