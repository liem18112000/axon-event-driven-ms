package com.liem.ms.securityservice.service.query.impl;

import com.liem.ms.commonservice.service.impl.BaseQueryService;
import com.liem.ms.securityservice.dto.role.RoleDto;
import com.liem.ms.securityservice.entity.RoleEntity;
import com.liem.ms.securityservice.mapper.RoleMapper;
import com.liem.ms.securityservice.repository.RoleRepository;
import com.liem.ms.securityservice.service.query.RoleQueryService;
import org.springframework.stereotype.Service;

/**
 * The type User query service.
 */
@Service
public class RoleQueryServiceImpl
    extends BaseQueryService<
            Long, RoleEntity, RoleDto<Long>,
        RoleMapper, RoleRepository
            > implements RoleQueryService {

  /**
   * Instantiates a new Role query service.
   *
   * @param roleRepository the role repository
   * @param mapper         the mapper
   */
  public RoleQueryServiceImpl(RoleRepository roleRepository,
      RoleMapper mapper) {
    super(roleRepository, mapper);
  }
}
