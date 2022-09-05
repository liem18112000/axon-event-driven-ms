package com.liem.ms.productservice.core.dto;

import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The type Product dto.
 *
 * @param <ID> the type parameter
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto<ID extends Serializable> extends BaseDto<ID> {

  /**
   * Instantiates a new Product dto.
   *
   * @param id          the id
   * @param name        the name
   * @param description the description
   * @param updatedAt   the updated at
   * @param createdAt   the created at
   * @param price       the price
   * @param quantity    the quantity
   */
  @Builder
  public ProductDto(ID id, String name, String description, String updatedAt,
      String createdAt, Float price, Integer quantity) {
    super(id, name, description, updatedAt, createdAt);
    this.price = price;
    this.quantity = quantity;
  }

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1782592208752953678L;

  /**
   * The Price.
   */
  @Min(value = 0, message = "Price must be positive")
  private Float price;

  /**
   * The Quantity.
   */
  @NotNull(message = "Quantity must not be null")
  @Min(value = 1, message = "Quantity must be larger than zero")
  private Integer quantity;

}
