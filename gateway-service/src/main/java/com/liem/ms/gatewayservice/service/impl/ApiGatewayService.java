package com.liem.ms.gatewayservice.service.impl;

import com.liem.ms.gatewayservice.service.GatewayService;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * The type Api gateway service.
 */
@Service
public class ApiGatewayService implements GatewayService {

  /**
   * The Application event publisher.
   */
  private final ApplicationEventPublisher applicationEventPublisher;

  /**
   * Instantiates a new Api gateway service.
   *
   * @param applicationEventPublisher the application event publisher
   */
  public ApiGatewayService(ApplicationEventPublisher applicationEventPublisher) {
    this.applicationEventPublisher = applicationEventPublisher;
  }

  @Override
  public void refreshRoutes() {
    this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
  }
}
