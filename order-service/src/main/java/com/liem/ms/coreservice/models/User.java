package com.liem.ms.coreservice.models;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type User.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 4867278863061654140L;

  /**
   * The First name.
   */
  private String firstName;

  /**
   * The Last name.
   */
  private String lastName;

  /**
   * The User id.
   */
  private String userId;

  /**
   * The Payment details.
   */
  private PaymentDetails paymentDetails;

}
