package com.extrawest.persons_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessage {
    EMPTY_FEIGN_CLIENT_RESULT("Empty result of feign client request"),
    WRONG_SELL_POINT_ID("Sell point don`t exist"),
    WEB_SOCKET_SENDING_ERROR("Error while sending message via WebSocket");

    private final String exMessage;
}
