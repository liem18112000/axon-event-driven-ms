package com.liem.ms.productservice.command.interceptor;

import com.liem.ms.productservice.command.commands.product.CreateProductCommand;
import com.liem.ms.productservice.command.commands.product.UpdateProductCommand;
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
 * The type Update product command interceptor.
 */
@Slf4j
@Component
@AllArgsConstructor
public class UpdateProductCommandInterceptor
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
      if (UpdateProductCommand.class.equals(commandType)) {
        log.info("Update product command interceptor handle");
        final UpdateProductCommand commandPayload = (UpdateProductCommand) command.getPayload();
        final var productId = commandPayload.getId();
        final var productName = commandPayload.getName();
        if (this.service.isUpdatedProductExistByName(productId, productName)) {
          log.error("Product with id '{}' has duplicated name '{}'", productId, productName);
          throw new CommandServiceException(
              String.format("Product with id '%s' has duplicated name '%s'",
                  productId, productName));
        }
      } else {
        log.info("Pass to next dispatch interceptor");
      }
      return command;
    };
  }
}
