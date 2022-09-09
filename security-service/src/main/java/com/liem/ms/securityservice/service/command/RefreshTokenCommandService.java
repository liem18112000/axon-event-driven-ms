package com.liem.ms.securityservice.service.command;

import com.liem.ms.commonservice.service.CommandService;
import com.liem.ms.securityservice.dto.token.RefreshTokenDto;

/**
 * The interface Refresh token command service.
 */
public interface RefreshTokenCommandService
    extends CommandService<Long, RefreshTokenDto<Long>> {

}
