package com.liem.ms.securityservice.service.query;

import com.liem.ms.commonservice.service.QueryService;
import com.liem.ms.securityservice.dto.resource.ResourceDto;
import com.liem.ms.securityservice.entity.ResourceEntity;
import java.util.List;

/**
 * The interface Resource query service.
 */
public interface ResourceQueryService
    extends QueryService<Long, ResourceEntity, ResourceDto<Long>> {

  /**
   * Gets all active resource.
   *
   * @return the all active resource
   */
  List<ResourceDto<Long>> getAllActiveResources();

}
