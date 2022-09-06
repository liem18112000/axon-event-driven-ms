package com.liem.ms.orderservice.command.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

/**
 * The type Order lookup entity.
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "orders_lookup")
public class OrderLookupEntity {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = -4572180894694838356L;

  /**
   * The Order id.
   */
  @Id
  @Column(unique = true)
  public String id;

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
    OrderLookupEntity that = (OrderLookupEntity) o;
    return id != null && Objects.equals(id, that.id);
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
