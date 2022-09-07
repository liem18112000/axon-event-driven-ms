package com.liem.ms.coreservice.queries;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Fetch user payment details query.
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class FetchUserPaymentDetailsQuery implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 2166855334346189781L;

  /**
   * The User id.
   */
  private String userId;

}
