package com.ytty.responder.api.exception.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public abstract class BaseException extends RuntimeException {

    protected Collection<DetailErrorInfo> details = new ArrayList<>();

    public abstract String getErrorType();

    public abstract String getMessage();

    public BaseException detail(String field, String errorType) {
        return detail(field, errorType, null);
    }

    public BaseException detail(String field, String errorType, String message) {
        getDetails().add(
                DetailErrorInfo.builder()
                        .field(field)
                        .errorType(errorType)
                        .message(message)
                        .build()
        );

        return this;
    }

    public BaseException details(Collection<DetailErrorInfo> detailErrors) {
        getDetails().addAll(detailErrors);
        return this;
    }

    public boolean isEmpty() {
        return details.isEmpty();
    }

    public void throwIfErrorNotEmpty() {
        if (!isEmpty()) {
            throw this;
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailErrorInfo {

        private String field;
        private String message;
        private String errorType;
    }
}
