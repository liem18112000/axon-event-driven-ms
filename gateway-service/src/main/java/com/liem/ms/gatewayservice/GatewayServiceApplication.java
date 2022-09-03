package com.liem.ms.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * The type Gateway service application.
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayServiceApplication {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(GatewayServiceApplication.class, args);
  }

}
