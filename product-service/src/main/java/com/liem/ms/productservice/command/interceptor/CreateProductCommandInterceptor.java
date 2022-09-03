package com.liem.ms.productservice.command.interceptor;

import com.liem.ms.productservice.command.commands.product.CreateProductCommand;
import com.liem.ms.productservice.command.service.ProductLookupService;
import com.liem.ms.productservice.core.exception.CommandServiceException;
import java.util.List;
import java.util.function.BiFunction;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

/**
 * The type Create product command interceptor.
 */
@Slf4j
@Component
@AllArgsConstructor
public class CreateProductCommandInterceptor
    implements MessageDispatchInterceptor<CommandMessage<?>> {

  /**
   * The Service.
   */
  private final ProductLookupService service;

  /**
   * Handle bi function.
   *
   * @param list the list
   * @return the bi function
   */
  @Override
  public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(
      List<? extends CommandMessage<?>> list) {
    return (index, command) -> {
      log.trace("Interceptor command message: {}", command);
      final var commandType = command.getPayloadType();
      if (CreateProductCommand.class.equals(commandType)) {
        log.info("Create product command interceptor handle");
        final CreateProductCommand commandPayload = (CreateProductCommand) command.getPayload();
        final var productName = commandPayload.getName();
        if (this.service.isProductExistByName(productName)) {
          log.error("Product name '{}' is duplicated", productName);
          throw new CommandServiceException(
              String.format("Product name '%s' is duplicated", productName));
        } else {
          log.info("Product creation is valid");
        }
      } else {
        log.info("Pass to next dispatch interceptor");
      }
      return command;
    };
  }
}
