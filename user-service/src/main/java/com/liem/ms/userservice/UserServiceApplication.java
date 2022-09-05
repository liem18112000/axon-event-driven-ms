package com.liem.ms.userservice;

import com.liem.ms.coreservice.config.XStreamConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/**
 * The type User service application.
 */
@EnableDiscoveryClient
@SpringBootApplication
@Import({XStreamConfiguration.class})
public class UserServiceApplication {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(UserServiceApplication.class, args);
  }

}
