package com.liem.ms.orderservice.command.events;

import com.liem.ms.orderservice.core.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Order created event.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {

  /**
   * The Order id.
   */
  private String orderId;

  /**
   * The Product id.
   */
  private String productId;

  /**
   * The User id.
   */
  private String userId;

  /**
   * The Quantity.
   */
  private int quantity;

  /**
   * The Address id.
   */
  private String addressId;

  /**
   * The Order status.
   */
  private OrderStatus orderStatus;
}
