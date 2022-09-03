package com.liem.ms.productservice.core.dto;

import java.io.Serializable;
import java.time.Instant;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The type Base dto.
 *
 * @param <ID> the type parameter
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto<ID extends Serializable> implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1782592208752953678L;

  /**
   * The Access at.
   */
  final protected String accessAt = Instant.now().toString();

  /**
   * The Id.
   */
  protected ID id;

  /**
   * The Name.
   */
  @NotNull
  @NotBlank
  protected String name;

  /**
   * The Description.
   */
  protected String description;

  /**
   * The Updated at.
   */
  protected String updatedAt;

  /**
   * The Created at.
   */
  protected String createdAt;

}
