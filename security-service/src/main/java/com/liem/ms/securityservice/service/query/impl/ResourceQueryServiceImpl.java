package com.liem.ms.securityservice.service.query.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liem.ms.commonservice.entity.BaseEntity;
import com.liem.ms.commonservice.service.CacheService;
import com.liem.ms.commonservice.service.impl.CacheBasedQueryServiceImpl;
import com.liem.ms.securityservice.config.cache.CacheDurationConfiguration;
import com.liem.ms.securityservice.dto.resource.ResourceDto;
import com.liem.ms.securityservice.entity.ResourceEntity;
import com.liem.ms.securityservice.mapper.ResourceMapper;
import com.liem.ms.securityservice.repository.ResourceRepository;
import com.liem.ms.securityservice.service.query.ResourceQueryService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * The type Resource query service.
 */
@Service
public class ResourceQueryServiceImpl
    extends CacheBasedQueryServiceImpl<
            Long, ResourceEntity, ResourceDto<Long>,
        ResourceMapper, ResourceRepository
            > implements ResourceQueryService {

  /**
   * The Cache duration configuration.
   */
  protected final CacheDurationConfiguration cacheDurationConfig;

  /**
   * Instantiates a new Cache based query service.
   *
   * @param resourceRepository         the repo
   * @param mapper                     the mapper
   * @param cacheService               the cache service
   * @param objectMapper               the object mapper
   * @param cacheDurationConfiguration the cache duration configuration
   */
  public ResourceQueryServiceImpl(
      ResourceRepository resourceRepository,
      ResourceMapper mapper,
      CacheService<String, String> cacheService,
      ObjectMapper objectMapper,
      CacheDurationConfiguration cacheDurationConfiguration) {
    super(resourceRepository, mapper, cacheService, objectMapper);
    this.cacheDurationConfig = cacheDurationConfiguration;
  }

  /**
   * Gets all active resources.
   *
   * @return the all active resources
   */
  @Override
  public List<ResourceDto<Long>> getAllActiveResources() {
    return this.getAllByCached(
        "AllActiveResources", i -> this.repo.findAll().stream()
            .filter(BaseEntity::isActive).map(this.mapper::mapToDto)
            .collect(Collectors.toList()),
        new TypeReference<>() {}, cacheDurationConfig.getResourcesCacheDuration());
  }
}
