package com.liem.ms.securityservice.service.command;

import com.liem.ms.commonservice.service.CommandService;
import com.liem.ms.securityservice.dto.role.RoleDto;

/**
 * The interface Role command service.
 */
public interface RoleCommandService
    extends CommandService<Long, RoleDto<Long>> {

}
