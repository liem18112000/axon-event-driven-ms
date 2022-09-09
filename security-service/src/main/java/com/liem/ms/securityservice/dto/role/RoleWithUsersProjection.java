package com.liem.ms.securityservice.dto.role;

import com.liem.ms.commonservice.dto.BaseProjection;
import com.liem.ms.securityservice.entity.RoleEntity;
import java.util.Set;
import org.springframework.data.rest.core.config.Projection;

/**
 * The interface Role projection.
 */
@Projection(name = "withUsers", types = {RoleEntity.class})
public interface RoleWithUsersProjection extends RoleProjection {

  /**
   * Gets id.
   *
   * @return the id
   */
  Long getId();

  /**
   * Gets users.
   *
   * @return the users
   */
  Set<BaseProjection<Long>> getUsers();

}
