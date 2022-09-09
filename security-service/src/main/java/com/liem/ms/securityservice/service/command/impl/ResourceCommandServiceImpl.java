package com.liem.ms.securityservice.service.command.impl;

import com.liem.ms.commonservice.service.impl.BaseCommandService;
import com.liem.ms.securityservice.dto.resource.ResourceDto;
import com.liem.ms.securityservice.entity.ResourceEntity;
import com.liem.ms.securityservice.mapper.ResourceMapper;
import com.liem.ms.securityservice.repository.ResourceRepository;
import com.liem.ms.securityservice.service.command.ResourceCommandService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * The type Resource command service.
 */
@Service
public class ResourceCommandServiceImpl
    extends BaseCommandService<
        Long, ResourceEntity, ResourceDto<Long>,
        ResourceMapper, ResourceRepository
        > implements ResourceCommandService {

  /**
   * Instantiates a new Resource command service.
   *
   * @param resourceRepository the resource repository
   * @param mapper             the mapper
   * @param publisher          the publisher
   */
  public ResourceCommandServiceImpl(
      ResourceRepository resourceRepository,
      ResourceMapper mapper,
      ApplicationEventPublisher publisher
  ) {
    super(resourceRepository, mapper, publisher);
  }
}
