/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liem.ms.orderservice.command.commands;

import com.liem.ms.orderservice.core.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * The type Create order command.
 */
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderCommand {

  /**
   * The Order id.
   */
  @TargetAggregateIdentifier
  private String orderId;

  /**
   * The User id.
   */
  private String userId;

  /**
   * The Product id.
   */
  private String productId;

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
