package com.liem.ms.productservice.core.config;

import org.axonframework.eventsourcing.AggregateLoadTimeSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Snap shot configuration.
 */
@Configuration
public class SnapShotConfiguration {

  /**
   * The constant DEFAULT_SNAPSHOT_TRIGGER.
   */
  public static final String DEFAULT_SNAPSHOT_TRIGGER = "default-snapshot-trigger";

  /**
   * Default snapshot trigger definition snapshot trigger definition.
   *
   * @param snapshotter the snapshotter
   * @return the snapshot trigger definition
   */
  @Bean(name = DEFAULT_SNAPSHOT_TRIGGER)
  public SnapshotTriggerDefinition defaultSnapshotTriggerDefinition(Snapshotter snapshotter) {
    return new AggregateLoadTimeSnapshotTriggerDefinition(snapshotter, 100);
  }

}
