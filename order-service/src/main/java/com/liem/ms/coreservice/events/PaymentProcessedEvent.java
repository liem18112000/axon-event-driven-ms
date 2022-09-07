package com.liem.ms.coreservice.events;

import com.liem.ms.coreservice.models.PaymentDetails;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Payment processed event.
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class PaymentProcessedEvent implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 4685507607488198217L;

  /**
   * The Payment id.
   */
  private String paymentId;

  /**
   * The Order id.
   */
  private String orderId;

  /**
   * The Payment details.
   */
  private PaymentDetails paymentDetails;

  /**
   * The Schedule id.
   */
  protected String scheduleId;

}
