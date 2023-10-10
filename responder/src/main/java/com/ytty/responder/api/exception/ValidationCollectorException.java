package com.ytty.responder.api.exception;

import com.ytty.responder.api.exception.base.BaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ValidationCollectorException extends BaseException {

    private final String errorType = ErrorTypes.VALIDATION_ERROR;
    private String message;

    public ValidationCollectorException(Collection<DetailErrorInfo> details) {
        this.details = details;
    }

    public ValidationCollectorException(String message, Collection<DetailErrorInfo> details) {
        this.message = message;
        this.details = details;
    }

    public static ValidationCollectorException mergeAll(ValidationCollectorException... exceptions) {
        return mergeAll(null, exceptions);
    }

    public static ValidationCollectorException mergeAll(String message, ValidationCollectorException... exceptions) {
        if (exceptions.length == 0) {
            return empty();
        }
        List<DetailErrorInfo> details = Stream.of(exceptions)
                .map(ValidationCollectorException::getDetails)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return new ValidationCollectorException(message, details);
    }

    public static ValidationCollectorException empty() {
        return new ValidationCollectorException(Collections.emptyList());
    }

    public ValidationCollectorException add(String field, String errorType) {
        detail(field, errorType);
        return this;
    }
}
