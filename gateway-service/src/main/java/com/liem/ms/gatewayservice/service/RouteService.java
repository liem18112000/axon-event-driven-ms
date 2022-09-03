package com.liem.ms.gatewayservice.service;

import com.liem.ms.gatewayservice.dto.RouteDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The interface Route service.
 */
public interface RouteService {

  /**
   * Gets all routes.
   *
   * @return the all routes
   */
  Flux<RouteDTO> getAllRoutes();

  /**
   * Gets by service name.
   *
   * @param name the name
   * @return the by service name
   */
  Mono<RouteDTO> getByServiceName(String name);
}
