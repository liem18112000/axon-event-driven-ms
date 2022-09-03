package com.liem.ms.gatewayservice.controller;

import com.liem.ms.gatewayservice.config.ApiV1;
import com.liem.ms.gatewayservice.service.GatewayService;
import com.liem.ms.gatewayservice.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The type Route controller.
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@RequestMapping(value = ApiV1.URI_API + "/routes", produces = ApiV1.MIME_API)
public class RouteController {

  /**
   * The Route service.
   */
  private final RouteService routeService;

  /**
   * The Gateway service.
   */
  private final GatewayService gatewayService;

  /**
   * Gets all routes.
   *
   * @return the all routes
   */
  @GetMapping
  public Flux<?> getAllRoutes() {
    return this.routeService.getAllRoutes();
  }

  /**
   * Gets route.
   *
   * @param name the name
   * @return the route
   */
  @GetMapping("{name}")
  public Mono<?> getRoute(@PathVariable String name) {
    return Mono.just(this.routeService.getByServiceName(name));
  }

  /**
   * Update route mono.
   *
   * @param name the name
   * @return the mono
   */
  @PutMapping("{name}")
  public Mono<?> updateRoute(@PathVariable String name) {
    this.gatewayService.refreshRoutes();
    // TODO: Add logic
    return Mono.just(this.routeService.getByServiceName(name));
  }
}
