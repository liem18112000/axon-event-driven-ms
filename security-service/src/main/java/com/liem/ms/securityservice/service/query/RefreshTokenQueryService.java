package com.liem.ms.securityservice.service.query;

import com.liem.ms.commonservice.service.QueryService;
import com.liem.ms.securityservice.dto.token.RefreshTokenDto;
import com.liem.ms.securityservice.entity.RefreshTokenEntity;

/**
 * The interface Refresh token query service.
 */
public interface RefreshTokenQueryService extends
    QueryService<Long, RefreshTokenEntity, RefreshTokenDto<Long>> {

  /**
   * Gets active token by username.
   *
   * @param username Username
   * @return the active token by user
   */
  String getActiveTokenByUsername(String username);

  /**
   * Gets active token by token.
   *
   * @param token the token
   * @return the active token by token
   */
  RefreshTokenDto<Long> getActiveTokenByToken(String token);
}
