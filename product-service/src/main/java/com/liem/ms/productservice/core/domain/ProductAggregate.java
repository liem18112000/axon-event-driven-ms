package com.liem.ms.productservice.core.domain;

import com.liem.ms.productservice.command.commands.common.DeleteCommand;
import com.liem.ms.productservice.command.commands.product.CreateProductCommand;
import com.liem.ms.productservice.command.commands.product.UpdateProductCommand;
import com.liem.ms.productservice.command.event.common.DeletedEvent;
import com.liem.ms.productservice.command.event.product.ProductCreatedEvent;
import com.liem.ms.productservice.command.event.product.ProductUpdatedEvent;
import java.io.Serializable;
import java.time.Instant;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

/**
 * The type Product aggregate.
 */
@Aggregate
@Data
@Slf4j
@NoArgsConstructor
public class ProductAggregate implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1491481265233062131L;

  /**
   * The Id.
   */
  @AggregateIdentifier
  protected String id;

  /**
   * The Name.
   */
  protected String name;

  /**
   * The Description.
   */
  protected String description;

  /**
   * The Updated at.
   */
  protected String updatedAt = Instant.now().toString();

  /**
   * The Created at.
   */
  protected String createdAt = Instant.now().toString();

  /**
   * The Version.
   */
  protected int version = 1;

  /**
   * The Is active.
   */
  protected boolean isActive = true;

  /**
   * The Price.
   */
  private Float price;

  /**
   * Instantiates a new Product aggregate.
   *
   * @param command the command
   */
  @CommandHandler
  public ProductAggregate(@NotNull @Valid CreateProductCommand command) {
    log.trace("Product aggregate command handle: {}", command);
    final var event = ProductCreatedEvent.builder()
        .id(command.getId())
        .name(command.getName())
        .description(command.getDescription())
        .price(command.getPrice())
        .build();
    AggregateLifecycle.apply(event);
  }

  /**
   * On create.
   *
   * @param event the event
   */
  @EventSourcingHandler
  public void onCreate(final @NotNull ProductCreatedEvent event) {
    log.trace("Product aggregate event sourcing handle: {}", event);
    this.setId(event.getId());
    this.setName(event.getName());
    this.setPrice(event.getPrice());
    this.setDescription(event.getDescription());
  }

  /**
   * Instantiates a new Product aggregate.
   *
   * @param command the command
   */
  @CommandHandler
  public void delete(@NotNull @Valid DeleteCommand<String> command) {
    log.trace("Product aggregate command handle: {}", command);
    var event = new DeletedEvent<>();
    event.setId(command.getId());
    AggregateLifecycle.apply(event);
  }

  /**
   * On delete.
   *
   * @param event the event
   */
  @EventSourcingHandler
  public void onDelete(final @NotNull DeletedEvent<String> event) {
    log.trace("Product aggregate event sourcing handle: {}", event);
    this.setId(event.getId());
  }

  /**
   * Instantiates a new Product aggregate.
   *
   * @param command the command
   */
  @CommandHandler
  public void update(@NotNull @Valid UpdateProductCommand command) {
    log.trace("Product aggregate command handle: {}", command);
    final var event = ProductUpdatedEvent.builder()
        .id(command.getId())
        .name(command.getName())
        .description(command.getDescription())
        .price(command.getPrice())
        .build();
    AggregateLifecycle.apply(event);
  }

  /**
   * On update.
   *
   * @param event the event
   */
  @EventSourcingHandler
  public void onUpdate(final @NotNull ProductUpdatedEvent event) {
    log.trace("Product aggregate event sourcing handle: {}", event);
    this.setId(event.getId());
    this.setName(event.getName());
    this.setPrice(event.getPrice());
    this.setDescription(event.getDescription());
    this.setVersion(this.getVersion() + 1);
  }

}
