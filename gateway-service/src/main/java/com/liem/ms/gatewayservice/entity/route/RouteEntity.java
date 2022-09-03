package com.liem.ms.gatewayservice.entity.route;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * The type Route entity.
 */
@Table("routes")
@Data
@Builder
public class RouteEntity {

  /**
   * The Id.
   */
  @Id
  private Long id;

  /**
   * The Uri.
   */
  @NotBlank
  @Column("uri")
  private String uri;

  /**
   * The Service name.
   */
  @NotBlank
  @Column("service_name")
  private String serviceName;

  /**
   * The Path.
   */
  @NotBlank
  @Column("path")
  private String path;

  /**
   * The Auth.
   */
  @Column("is_auth")
  private boolean auth;

  /**
   * The Active.
   */
  @Column("ia_active")
  private boolean active;
}
