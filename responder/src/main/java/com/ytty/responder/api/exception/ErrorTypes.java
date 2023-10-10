package com.ytty.responder.api.exception;


public final class ErrorTypes {

    private ErrorTypes() {
    }

    public static final String REQUEST_FAILED = "request-failed";
    public static final String BAD_REQUEST = "bad-request";
    public static final String UNAUTHORIZED = "unauthorized";
    public static final String FORBIDDEN = "forbidden";
    public static final String NOT_FOUND = "not-found";
    public static final String ENTITY_ALREADY_EXISTS = "entity-already-exists";

    // Validation
    public static final String VALIDATION_ERROR = "validation-error";
    public static final String INVALID_PARAM = "invalid-param";
    public static final String EMPTY_PARAM = "empty-param";
}
