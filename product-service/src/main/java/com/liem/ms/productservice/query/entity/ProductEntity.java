package com.liem.ms.productservice.query.entity;

import com.liem.ms.coreservice.events.ProductCancelReserveEvent;
import com.liem.ms.coreservice.events.ProductReservedEvent;
import com.liem.ms.productservice.command.event.product.ProductSuppliedEvent;
import com.liem.ms.productservice.command.event.product.ProductUpdatedEvent;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;

/**
 * The type Product entity.
 */
@Table(name = "products")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Slf4j
public class ProductEntity implements Serializable  {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = -6148557352562843454L;

  /**
   * The Id.
   */
  @Id
  @Column(name = "id", nullable = false)
  private String id;

  /**
   * The Name.
   */
  @Column(name = "name")
  private String name;

  /**
   * The Description.
   */
  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  /**
   * The Updated at.
   */
  @Column(name = "updated_at")
  private String updatedAt = Instant.now().toString();

  /**
   * The Created at.
   */
  @Column(name = "created_at")
  private String createdAt = Instant.now().toString();

  /**
   * The Version.
   */
  @Column(name = "version")
  private int version = 1;

  /**
   * The Is active.
   */
  @Column(name = "is_active")
  private boolean isActive = true;

  /**
   * The Price.
   */
  @Column(name = "price")
  private Float price;

  /**
   * The Quantity.
   */
  @Column(name = "quantity")
  private Integer quantity;

  /**
   * Update product entity.
   *
   * @param event the event
   * @return the product entity
   */
  public ProductEntity update(ProductUpdatedEvent event) {
    this.setName(event.getName());
    this.setDescription(event.getDescription());
    this.setPrice(event.getPrice());
    this.setUpdatedAt(LocalDateTime.now().toString());
    this.setVersion(this.getVersion() + 1);
    return this;
  }

  /**
   * Supply product entity.
   *
   * @param event the event
   * @return the product entity
   */
  public ProductEntity supply(ProductSuppliedEvent event) {
    this.setQuantity(this.getQuantity() + event.getQuantity());
    this.setUpdatedAt(LocalDateTime.now().toString());
    this.setVersion(this.getVersion() + 1);
    return this;
  }

  /**
   * Cancel reservation product entity.
   *
   * @param event the event
   * @return the product entity
   */
  public ProductEntity cancelReservation(ProductCancelReserveEvent event) {
    this.setQuantity(this.getQuantity() + event.getQuantity());
    this.setUpdatedAt(LocalDateTime.now().toString());
    this.setVersion(this.getVersion() + 1);
    return this;
  }

  /**
   * Reserve product entity.
   *
   * @param event the event
   * @return the product entity
   */
  public ProductEntity reserve(ProductReservedEvent event) {
    this.setQuantity(this.getQuantity() - event.getQuantity());
    this.setUpdatedAt(LocalDateTime.now().toString());
    this.setVersion(this.getVersion() + 1);
    return this;
  }

  /**
   * Equals boolean.
   *
   * @param o the o
   * @return the boolean
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    ProductEntity that = (ProductEntity) o;
    return getId() != null && Objects.equals(getId(), that.getId());
  }

  /**
   * Hash code int.
   *
   * @return the int
   */
  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
