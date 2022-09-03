package com.liem.ms.gatewayservice.service.impl;

import com.liem.ms.gatewayservice.dto.RouteDTO;
import com.liem.ms.gatewayservice.service.RouteService;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * The type Route locator service.
 */
@Service
public class RouteLocatorService implements RouteLocator {

  /**
   * The Route service.
   */
  private final RouteService routeService;

  /**
   * The Route builder.
   */
  private final RouteLocatorBuilder.Builder routeBuilder;

  /**
   * Instantiates a new Route locator service.
   *
   * @param routeService the route service
   * @param routeBuilder the route builder
   */
  public RouteLocatorService(
      RouteService routeService,
      RouteLocatorBuilder routeBuilder
  ) {
    this.routeService = routeService;
    this.routeBuilder = routeBuilder.routes();
  }

  @Override
  public Flux<Route> getRoutes() {
    return routeService.getAllRoutes()
        .map(route -> routeBuilder.route(predicate -> setPredicate(route, predicate)))
        .collectList().flatMapMany(builders -> routeBuilder.build().getRoutes());
  }

  /**
   * Sets predicate.
   *
   * @param route     the route
   * @param predicate the predicate
   * @return the predicate
   */
  private Buildable<Route> setPredicate(RouteDTO route, PredicateSpec predicate) {
    return predicate
        .path(route.getPath())
        .uri(route.getUri());
  }
}
