package com.liem.ms.productservice.core.dto;

import java.io.Serializable;
import javax.validation.constraints.Min;
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
   */
  @Builder
  public ProductDto(ID id, String name, String description, String updatedAt,
      String createdAt, Float price) {
    super(id, name, description, updatedAt, createdAt);
    this.price = price;
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

}
