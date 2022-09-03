package com.liem.ms.discoverserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * The type Discover server application.
 */
@EnableEurekaServer
@SpringBootApplication
public class DiscoverServerApplication {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(DiscoverServerApplication.class, args);
  }

}
