package com.liem.ms.gatewayservice.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * The type Route dto.
 */
@Builder
@Data
@AllArgsConstructor
public class RouteDTO implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  public static final long serialVersionUID = 780145961842519590L;

  /**
   * The Id.
   */
  protected Long id;

  /**
   * The Service name.
   */
  protected String serviceName;

  /**
   * The Uri.
   */
  protected String uri;

  /**
   * The Path.
   */
  protected String path;

  /**
   * The Auth.
   */
  protected boolean auth;

  /**
   * The Active.
   */
  protected boolean active;
}
