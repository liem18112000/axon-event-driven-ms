package com.liem.ms.coreservice.config;

import com.thoughtworks.xstream.XStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type X stream configuration.
 */
@Configuration
public class XStreamConfiguration {

  /**
   * X stream x stream.
   *
   * @return the x stream
   */
  @Bean
  public XStream xStream() {
    var xStream = new XStream();
    xStream.allowTypesByWildcard(new String[] {"com.liem.ms.**"});
    return xStream;
  }
}
