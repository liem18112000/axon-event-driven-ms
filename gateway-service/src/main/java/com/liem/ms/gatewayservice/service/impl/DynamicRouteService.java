package com.liem.ms.gatewayservice.service.impl;

import com.liem.ms.gatewayservice.dto.RouteDTO;
import com.liem.ms.gatewayservice.entity.route.RouteEntity;
import com.liem.ms.gatewayservice.entity.route.RouteEntityRepository;
import com.liem.ms.gatewayservice.mapper.RouteMapper;
import com.liem.ms.gatewayservice.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The type Dynamic route service.
 */
@Slf4j
@Service
public class DynamicRouteService implements RouteService {

  /**
   * The Route repository.
   */
  private final RouteEntityRepository routeRepository;

  /**
   * The Route mapper.
   */
  private final RouteMapper routeMapper;

  /**
   * Instantiates a new Dynamic route service.
   *
   * @param routeRepository the route repository
   * @param routeMapper     the route mapper
   */
  public DynamicRouteService(
      RouteEntityRepository routeRepository, RouteMapper routeMapper) {
    this.routeRepository = routeRepository;
    this.routeMapper = routeMapper;
  }

  @Override
  public Flux<RouteDTO> getAllRoutes() {
    return routeRepository.findAll().filter(RouteEntity::isActive)
        .map(entity -> {
          final var dto = this.routeMapper.toDto(entity);
          log.info("Mapping {} - {} - {}", dto.getUri(), dto.getPath(), dto.isAuth());
          return dto;
        });
  }

  @Override
  public Mono<RouteDTO> getByServiceName(String name) {
    return this.routeRepository
        .findByServiceNameAndActiveIsTrue(name)
        .map(this.routeMapper::toDto);
  }
}
