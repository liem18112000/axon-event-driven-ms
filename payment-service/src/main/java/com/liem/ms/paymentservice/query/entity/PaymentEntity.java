package com.liem.ms.paymentservice.query.entity;

import java.io.Serializable;
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
 * The type Payment entity.
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "payments")
public class PaymentEntity implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = -8495495152790348108L;
  /**
   * The Order id.
   */
  @Column(name = "order_id")
  public String orderId;
  @Id
  @Column(name = "id")
  private String id;

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
    PaymentEntity that = (PaymentEntity) o;
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
