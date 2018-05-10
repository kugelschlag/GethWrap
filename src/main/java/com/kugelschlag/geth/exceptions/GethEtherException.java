package com.kugelschlag.geth.exceptions;

public class GethEtherException extends RuntimeException {

    public GethEtherException(String message) {
        super(message);
    }

    public GethEtherException(String message, Throwable cause) {
        super(message, cause);
    }
}
