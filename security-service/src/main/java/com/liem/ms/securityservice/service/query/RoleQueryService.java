package com.liem.ms.securityservice.service.query;

import com.liem.ms.commonservice.service.QueryService;
import com.liem.ms.securityservice.dto.role.RoleDto;
import com.liem.ms.securityservice.entity.RoleEntity;

/**
 * The interface Role query service.
 */
public interface RoleQueryService
    extends QueryService<Long, RoleEntity, RoleDto<Long>> {

}
