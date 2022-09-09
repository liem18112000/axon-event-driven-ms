package com.liem.ms.securityservice.repository;

import com.liem.ms.commonservice.repository.BaseRepository;
import com.liem.ms.securityservice.dto.resource.ResourceProjection;
import com.liem.ms.securityservice.entity.ResourceEntity;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * The interface Resource repository.
 */
@Repository
@RepositoryRestResource(
    path = "resources",
    collectionResourceRel = "resources",
    itemResourceRel = "resource",
    excerptProjection = ResourceProjection.class)
public interface ResourceRepository
    extends BaseRepository<ResourceEntity, Long> {
}
