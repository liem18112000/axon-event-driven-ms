package com.liem.ms.securityservice.service.command.impl;

import static com.liem.ms.commonservice.service.util.ServiceUtil.validateNotNull;

import com.liem.ms.commonservice.auth.token.TokenGenerator;
import com.liem.ms.commonservice.service.impl.BaseCommandService;
import com.liem.ms.securityservice.config.security.RefreshTokenConfiguration;
import com.liem.ms.securityservice.dto.token.RefreshTokenDto;
import com.liem.ms.securityservice.entity.RefreshTokenEntity;
import com.liem.ms.securityservice.mapper.RefreshTokenMapper;
import com.liem.ms.securityservice.repository.RefreshTokenRepository;
import com.liem.ms.securityservice.service.command.RefreshTokenCommandService;
import java.time.Instant;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.rest.core.event.AfterCreateEvent;
import org.springframework.data.rest.core.event.BeforeCreateEvent;
import org.springframework.stereotype.Service;

/**
 * The type Refresh token command service.
 */
@Service
public class RefreshTokenCommandServiceImpl
    extends BaseCommandService<
        Long, RefreshTokenEntity, RefreshTokenDto<Long>,
        RefreshTokenMapper, RefreshTokenRepository
        > implements RefreshTokenCommandService {

  /**
   * Instantiates a new Refresh token command service.
   *
   * @param refreshTokenRepository the refresh token repository
   * @param mapper                 the mapper
   * @param publisher              the publisher
   * @param tokenGenerator         the token generator
   * @param configuration          the configuration
   */
  public RefreshTokenCommandServiceImpl(
      RefreshTokenRepository refreshTokenRepository,
      RefreshTokenMapper mapper,
      ApplicationEventPublisher publisher,
      TokenGenerator<RefreshTokenDto<?>, String> tokenGenerator,
      RefreshTokenConfiguration configuration) {
    super(refreshTokenRepository, mapper, publisher);
    this.tokenGenerator = tokenGenerator;
    this.configuration = configuration;
  }

  /**
   * The Token generator.
   */
  private final TokenGenerator<RefreshTokenDto<?>, String> tokenGenerator;

  /**
   * The Configuration.
   */
  private final RefreshTokenConfiguration configuration;

  /**
   * Create dto.
   *
   * @param dto the dto
   * @return the dto
   */
  @Override
  public RefreshTokenDto<Long> create(RefreshTokenDto<Long> dto) {
    validateNotNull(dto, "DTO");
    final var entityToCreate = this.mapper.mapToEntity(dto);
    final var refreshToken = this.tokenGenerator.generateToken(dto);
    entityToCreate.setToken(refreshToken);
    entityToCreate.setExpiredAt(Instant.now()
        .plus(this.configuration.getTokenExpiration()));
    publisher.publishEvent(new BeforeCreateEvent(entityToCreate));
    final var createdEntity = this.repo.save(entityToCreate);
    publisher.publishEvent(new AfterCreateEvent(createdEntity));
    return this.mapper.mapToDto(createdEntity);
  }
}
