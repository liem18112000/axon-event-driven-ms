package com.liem.ms.securityservice.service.command;

import com.liem.ms.commonservice.service.CommandService;
import com.liem.ms.securityservice.dto.resource.ResourceDto;

/**
 * The interface Resource command service.
 */
public interface ResourceCommandService
    extends CommandService<Long, ResourceDto<Long>> {

}
