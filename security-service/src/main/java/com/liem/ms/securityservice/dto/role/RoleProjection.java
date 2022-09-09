package com.liem.ms.securityservice.dto.role;

import com.liem.ms.commonservice.dto.BaseProjection;
import com.liem.ms.securityservice.entity.RoleEntity;
import org.springframework.data.rest.core.config.Projection;

/**
 * The interface Role projection.
 */
@Projection(types = {RoleEntity.class})
public interface RoleProjection extends BaseProjection<Long> {

  /**
   * Gets id.
   *
   * @return the id
   */
  Long getId();

  /**
   * Gets active.
   *
   * @return the active
   */
  boolean getActive();

}
