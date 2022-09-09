package com.liem.ms.commonservice.mapper;

import com.liem.ms.commonservice.message.BaseMessage;

/**
 * The interface Message mapper.
 *
 * @param <BODY> the type parameter
 * @param <T>    the type parameter
 */
public interface MessageMapper<BODY, T> {

  /**
   * Map message to object t.
   *
   * @param message the message
   * @return the t
   */
  T mapToObject(BaseMessage<BODY> message);

  /**
   * Map object to message rest message.
   *
   * @param object the object
   * @return the rest message
   */
  BaseMessage<BODY> mapToMessage(T object);

}
