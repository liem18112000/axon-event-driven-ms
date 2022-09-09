package com.liem.ms.securityservice.service.query;

import com.liem.ms.commonservice.service.QueryService;
import com.liem.ms.securityservice.dto.user.UserDto;
import com.liem.ms.securityservice.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * The interface User query service.
 */
public interface UserQueryService
    extends QueryService<Long, UserEntity, UserDto<Long>>, UserDetailsService {

  /**
   * Gets active user by username.
   *
   * @param username the username
   * @return the active user by username
   */
  UserDto<Long> getActiveUserByUsername(String username);

  /**
   * Gets active user by username without exception.
   *
   * @param username the username
   * @return the active user by username without exception
   */
  UserDto<Long> getActiveUserByUsernameWithoutException(String username);

}
