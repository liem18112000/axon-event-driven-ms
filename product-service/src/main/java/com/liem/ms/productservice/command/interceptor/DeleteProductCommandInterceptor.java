package com.liem.ms.productservice.command.interceptor;

import com.liem.ms.productservice.command.commands.common.DeleteCommand;
import java.util.List;
import java.util.function.BiFunction;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

/**
 * The type Delete product command interceptor.
 */
@Slf4j
@Component
@AllArgsConstructor
public class DeleteProductCommandInterceptor
    implements MessageDispatchInterceptor<CommandMessage<?>> {

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
      if (DeleteCommand.class.equals(commandType)) {
        log.info("Delete product command interceptor handle");
      } else {
        log.info("Pass to next dispatch interceptor");
      }
      return command;
    };
  }
}
