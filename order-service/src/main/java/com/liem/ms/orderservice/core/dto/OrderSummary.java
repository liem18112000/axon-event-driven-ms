package com.liem.ms.orderservice.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.liem.ms.orderservice.core.model.OrderStatus;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Order summary.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class OrderSummary implements Serializable {

  /**
   * The Order id.
   */
  private String orderId;

  /**
   * The Reject reason.
   */
  private String message;

  /**
   * The Order Status
   */
  private OrderStatus orderStatus;
}
