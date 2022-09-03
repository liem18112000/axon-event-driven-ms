package com.liem.ms.gatewayservice.mapper;

import com.liem.ms.gatewayservice.dto.RouteDTO;
import com.liem.ms.gatewayservice.entity.route.RouteEntity;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

/**
 * The type Route mapper.
 */
@Component
public class RouteMapper {

  /**
   * To dto route dto.
   *
   * @param entity the entity
   * @return the route dto
   */
  public RouteDTO toDto(final RouteEntity entity) {
    if (Objects.isNull(entity)) {
      return null;
    }

    return getRouteDTO(entity);
  }

  /**
   * To dto with exception route dto.
   *
   * @param entity the entity
   * @return the route dto
   * @throws IllegalArgumentException the illegal argument exception
   */
  public RouteDTO toDtoWithException(final RouteEntity entity)
      throws IllegalArgumentException {
    if (Objects.isNull(entity)) {
      throw new IllegalArgumentException("Entity is null");
    }

    return getRouteDTO(entity);
  }

  /**
   * Gets route dto.
   *
   * @param entity the entity
   * @return the route dto
   */
  private RouteDTO getRouteDTO(@NotNull final RouteEntity entity) {
    return RouteDTO.builder()
        .id(entity.getId())
        .serviceName(entity.getServiceName())
        .uri(entity.getUri())
        .path(entity.getPath())
        .active(entity.isActive())
        .auth(entity.isAuth())
        .build();
  }

  /**
   * To entity route entity.
   *
   * @param routeDto the route dto
   * @return the route entity
   */
  public RouteEntity toEntity(final RouteDTO routeDto) {

    if (Objects.isNull(routeDto)) {
      return null;
    }

    return getRouteEntity(routeDto);

  }

  /**
   * To entity with exception route entity.
   *
   * @param routeDto the route dto
   * @return the route entity
   * @throws IllegalArgumentException the illegal argument exception
   */
  public RouteEntity toEntityWithException(final RouteDTO routeDto)
      throws IllegalArgumentException {
    if (Objects.isNull(routeDto)) {
      throw new IllegalArgumentException("DTO is null");
    }

    return getRouteEntity(routeDto);
  }

  /**
   * Gets route entity.
   *
   * @param routeDto the route dto
   * @return the route entity
   */
  private RouteEntity getRouteEntity(@NotNull final RouteDTO routeDto) {
    return RouteEntity.builder()
        .id(routeDto.getId())
        .serviceName(routeDto.getServiceName())
        .uri(routeDto.getUri())
        .path(routeDto.getPath())
        .auth(routeDto.isAuth())
        .active(routeDto.isActive())
        .build();
  }
}
