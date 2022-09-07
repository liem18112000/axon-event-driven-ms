package com.liem.ms.coreservice.events;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Cancel payment command.
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class PaymentCanceledEvent implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1472289348355769161L;

  /**
   * The Payment id.
   */
  private String paymentId;

  /**
   * The Order id.
   */
  private String orderId;

  /**
   * The Schedule id.
   */
  protected String scheduleId;

  /**
   * The Cancel reason.
   */
  protected String cancelReason;

}
