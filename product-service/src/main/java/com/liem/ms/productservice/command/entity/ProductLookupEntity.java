package com.liem.ms.productservice.command.entity;

import com.liem.ms.coreservice.events.ProductCancelReserveEvent;
import com.liem.ms.coreservice.events.ProductReservedEvent;
import com.liem.ms.productservice.command.event.product.ProductSuppliedEvent;
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
   * The Product quantity.
   */
  @Column(name = "product_quantity")
  private Integer productQuantity;

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

  /**
   * Supply product entity.
   *
   * @param event the event
   * @return the product entity
   */
  public ProductLookupEntity supply(ProductSuppliedEvent event) {
    this.setProductQuantity(this.getProductQuantity() + event.getQuantity());
    return this;
  }

  /**
   * Cancel reservation product entity.
   *
   * @param event the event
   * @return the product entity
   */
  public ProductLookupEntity cancelReservation(ProductCancelReserveEvent event) {
    this.setProductQuantity(this.getProductQuantity() + event.getQuantity());
    return this;
  }

  /**
   * Reserve product entity.
   *
   * @param event the event
   * @return the product entity
   */
  public ProductLookupEntity reserve(ProductReservedEvent event) {
    this.setProductQuantity(this.getProductQuantity() - event.getQuantity());
    return this;
  }
}
