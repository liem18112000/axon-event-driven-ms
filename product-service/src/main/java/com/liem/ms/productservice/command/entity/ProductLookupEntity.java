package com.liem.ms.productservice.command.entity;

import com.liem.ms.productservice.command.event.product.ProductUpdatedEvent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Product lookup entity.
 */
@Entity
@Table(name = "products_lookup")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ProductLookupEntity {

  /**
   * The Product id.
   */
  @Id
  @Column(name = "product_id", nullable = false)
  private String productId;

  /**
   * The Product name.
   */
  @Column(name = "product_name", unique = true)
  private String productName;

  /**
   * Update product name product lookup entity.
   *
   * @param event the event
   * @return the product lookup entity
   */
  public ProductLookupEntity updateProductName(final @NotNull ProductUpdatedEvent event) {
    this.setProductName(event.getName());
    return this;
  }
}
