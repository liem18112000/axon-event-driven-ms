package com.liem.ms.securityservice.repository;

import com.liem.ms.commonservice.repository.BaseRepository;
import com.liem.ms.securityservice.dto.role.RoleProjection;
import com.liem.ms.securityservice.entity.RoleEntity;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * The interface Role repository.
 */
@Repository
@RepositoryRestResource(
    path = "roles",
    collectionResourceRel = "roles",
    itemResourceRel = "role",
    excerptProjection = RoleProjection.class)
public interface RoleRepository
    extends BaseRepository<RoleEntity, Long> {
}
