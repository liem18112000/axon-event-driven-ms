package com.liem.ms.commonservice.dto;

import com.liem.ms.commonservice.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * The interface Key value projection.
 */
@Projection(name = "keyValue", types = {BaseEntity.class})
public interface KeyValueProjection {

  /**
   * Gets key.
   *
   * @return the key
   */
  @Value("#{target.name}")
  Object getKey();

  /**
   * Gets value.
   *
   * @return the value
   */
  @Value("#{target.description}")
  Object getValue();
}
