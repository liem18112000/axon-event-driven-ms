package com.liem.ms.securityservice.service.command.impl;

import com.liem.ms.commonservice.service.impl.BaseCommandService;
import com.liem.ms.securityservice.dto.role.RoleDto;
import com.liem.ms.securityservice.entity.RoleEntity;
import com.liem.ms.securityservice.mapper.RoleMapper;
import com.liem.ms.securityservice.repository.RoleRepository;
import com.liem.ms.securityservice.service.command.RoleCommandService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * The type Role command service.
 */
@Service
public class RoleCommandServiceImpl
    extends BaseCommandService<
        Long, RoleEntity, RoleDto<Long>, RoleMapper, RoleRepository
        > implements RoleCommandService {

  /**
   * Instantiates a new Role command service.
   *
   * @param roleRepository the role repository
   * @param mapper         the mapper
   * @param publisher      the publisher
   */
  public RoleCommandServiceImpl(RoleRepository roleRepository,
      RoleMapper mapper, ApplicationEventPublisher publisher) {
    super(roleRepository, mapper, publisher);
  }
}
