package com.lifung.todoitem.exception;

public class TodoItemException extends Exception {
    private static final long serialVersionUID = 1L;

    private final String errorCode;

    public TodoItemException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public TodoItemException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
