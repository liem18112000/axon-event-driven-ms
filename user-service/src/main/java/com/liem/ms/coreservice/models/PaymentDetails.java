package com.liem.ms.coreservice.models;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Payment details.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetails implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 4867278863061654140L;

  /**
   * The Name.
   */
  private String name;

  /**
   * The Card number.
   */
  private String cardNumber;

  /**
   * The Valid until month.
   */
  private int validUntilMonth;

  /**
   * The Valid until year.
   */
  private int validUntilYear;

  /**
   * The Cvv.
   */
  private String cvv;
}
