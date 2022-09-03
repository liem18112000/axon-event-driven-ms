package com.liem.ms.gatewayservice.controller;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.liem.ms.gatewayservice.config.ApiV1;
import com.liem.ms.gatewayservice.service.GatewayService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * The type Gateway controller.
 */
@RestController
@RequestMapping(value = ApiV1.URI_API, produces = ApiV1.MIME_API)
public class GatewayController {

  /**
   * The Gateway service.
   */
  private final GatewayService gatewayService;

  /**
   * Instantiates a new Gateway controller.
   *
   * @param gatewayService the gateway service
   */
  public GatewayController(GatewayService gatewayService) {
    this.gatewayService = gatewayService;
  }

  /**
   * Refresh route mono.
   *
   * @return the mono
   */
  @PostMapping("route/refresh")
  public Mono<?> refreshRoute() {
    try {
      this.gatewayService.refreshRoutes();
      return ServerResponse.ok().build();
    } catch (Exception exception) {
      return ServerResponse.status(INTERNAL_SERVER_ERROR)
          .bodyValue(exception.getStackTrace());
    }
  }
}
