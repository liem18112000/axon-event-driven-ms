package com.liem.ms.coreservice.commands;

import com.liem.ms.coreservice.models.PaymentDetails;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * The type Process payment command.
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class ProcessPaymentCommand implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 4685507607488198217L;

  /**
   * The Payment id.
   */
  @TargetAggregateIdentifier
  private String paymentId;

  /**
   * The Order id.
   */
  private String orderId;

  /**
   * The Payment details.
   */
  private PaymentDetails paymentDetails;

}
