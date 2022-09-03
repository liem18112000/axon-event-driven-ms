package com.liem.ms.productservice.query.entity;

import com.liem.ms.productservice.command.event.product.ProductUpdatedEvent;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class ProductEntity extends BaseEntity<String> {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = -6148557352562843454L;

  /**
   * The Price.
   */
  @Column(name = "price")
  private Float price;

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
