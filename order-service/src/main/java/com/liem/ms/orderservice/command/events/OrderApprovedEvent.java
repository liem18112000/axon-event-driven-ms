package com.liem.ms.orderservice.command.events;

import com.liem.ms.orderservice.core.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Order approved event.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderApprovedEvent {

  /**
   * The Order id.
   */
  private String orderId;

  /**
   * The Order status.
   */
  private final OrderStatus orderStatus = OrderStatus.APPROVED;

}
