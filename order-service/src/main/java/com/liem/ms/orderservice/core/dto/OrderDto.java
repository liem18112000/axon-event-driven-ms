package com.liem.ms.orderservice.core.dto;

import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Order dto.
 *
 * @param <ID> the type parameter
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto<ID extends Serializable> {

  /**
   * The Id.
   */
  private ID id;

  /**
   * The User id.
   */
  @NotBlank(message = "Order userId is a required field")
  private String userId;

  /**
   * The Product id.
   */
  @NotBlank(message = "Order productId is a required field")
  private String productId;

  /**
   * The Quantity.
   */
  @Min(value = 1, message = "Quantity cannot be lower than 1")
  @Max(value = 500, message = "Quantity cannot be larger than 5")
  private int quantity;

  /**
   * The Address id.
   */
  @NotBlank(message = "Order addressId is a required field")
  private String addressId;

  /**
   * The Reject reason.
   */
  private String rejectReason;

  /**
   * The Status.
   */
  private String status;

}
