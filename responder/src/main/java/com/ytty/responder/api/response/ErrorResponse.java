package com.ytty.responder.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ytty.responder.api.exception.base.BaseException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ErrorResponse extends EmptyResponse {

    private Error error;


    protected ErrorResponse(Error error, HttpStatus status) {
        super(status);
        this.error = error;
    }

    protected ErrorResponse(Error error, HttpStatus status, HttpHeaders headers) {
        super(status, headers);
        this.error = error;
    }

    /**
     * Response with http-status = 400
     *
     * @param error - error info
     * @return response with error info and http-status
     */
    public static ErrorResponse badRequest(Error error) {
        return new ErrorResponse(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Response with http-status = 401
     *
     * @param error - error info
     * @return response with error info and http-status
     */
    public static ErrorResponse unauthorized(Error error) {
        return new ErrorResponse(error, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Response with http-status = 403
     *
     * @param error - error info
     * @return response with error info and http-status
     */
    public static ErrorResponse forbidden(Error error) {
        return new ErrorResponse(error, HttpStatus.FORBIDDEN);
    }

    /**
     * Response with http-status = 404
     *
     * @param error - error info
     * @return response with error info and http-status
     */
    public static ErrorResponse notFound(Error error) {
        return new ErrorResponse(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Response with http-status = 409
     *
     * @param error - error info
     * @return response with error info and http-status
     */
    public static ErrorResponse conflict(Error error) {
        return new ErrorResponse(error, HttpStatus.CONFLICT);
    }

    /**
     * Response with http-status = 500
     *
     * @param error - error info
     * @return response with error info and http-status
     */
    public static ErrorResponse internalServerError(Error error) {
        return new ErrorResponse(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Error {

        /**
         * Human-readable message
         */
        private String message;

        /**
         * Type for error handling
         */
        private String type;

        @Builder.Default
        @JsonFormat(pattern = AppConstants.DATETIME_PATTERN)
        private LocalDateTime timestamp = LocalDateTime.now();

        /**
         * Error details
         */
        private List<Detail> details;

        public static Error of(final BaseException baseException) {
            return Error.builder()
                    .type(baseException.getErrorType())
                    .message(baseException.getMessage())
                    .details(
                            baseException.getDetails()
                                    .stream()
                                    .map(detailError -> Detail.builder()
                                            .type(detailError.getErrorType())
                                            .message(detailError.getMessage())
                                            .target(detailError.getField())
                                            .build())
                                    .collect(Collectors.toList())
                    )
                    .build();
        }

        public Error addDetail(@NonNull final Detail detail) {
            if (details == null) {
                details = new ArrayList<>();
            }
            details.add(detail);
            return this;
        }

        @Getter
        @Setter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Detail {

            /**
             * Human-readable message
             */
            private String message;

            /**
             * Type for detail handling
             */
            private String type;

            /**
             * Target field or other object
             */
            private String target;

            /**
             * Retry after n seconds
             */
            private Long waitingInSeconds;
        }
    }
}
