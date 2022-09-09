package com.liem.ms.securityservice.repository;

import com.liem.ms.commonservice.repository.BaseRepository;
import com.liem.ms.securityservice.dto.user.UserProjection;
import com.liem.ms.securityservice.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * The interface User repository.
 */
@Repository
@RepositoryRestResource(
    path = "users",
    collectionResourceRel = "users",
    itemResourceRel = "user",
    excerptProjection = UserProjection.class)
public interface UserRepository
    extends BaseRepository<UserEntity, Long> {

  /**
   * Find by username and active is true optional.
   *
   * @param username the username
   * @return the optional
   */
  Optional<UserEntity> findByUsernameAndIsActiveIsTrue(String username);
}
