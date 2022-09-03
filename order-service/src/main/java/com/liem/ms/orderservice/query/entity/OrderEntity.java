/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liem.ms.orderservice.query.entity;

import com.liem.ms.orderservice.core.model.OrderStatus;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

/**
 * The type Order entity.
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = -4491461356277288249L;

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
   * The Address id.
   */
  private String addressId;

  /**
   * The Order status.
   */
  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

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
    OrderEntity that = (OrderEntity) o;
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
