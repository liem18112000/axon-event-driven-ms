package com.liem.ms.orderservice.command.events;

import com.liem.ms.orderservice.core.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Order rejected event.
 */
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRejectedEvent {

  /**
   * The Order id.
   */
  private String orderId;

  /**
   * The Order status.
   */
  private final OrderStatus orderStatus = OrderStatus.REJECTED;

  /**
   * The Reason.
   */
  private String reason;

}
