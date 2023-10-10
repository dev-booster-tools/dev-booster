package com.ytty.responder.config;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ytty.responder.api.exception.BadRequestException;
import com.ytty.responder.api.exception.ConflictException;
import com.ytty.responder.api.exception.ErrorTypes;
import com.ytty.responder.api.exception.ForbiddenException;
import com.ytty.responder.api.exception.NotFoundException;
import com.ytty.responder.api.exception.ValidationCollectorException;
import com.ytty.responder.api.response.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class ExceptionHandlingControllerAdvice {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("Validation exception. Cause: ", exception);
        return getValidationErrorResponse("Validation exception", exception.getBindingResult());
    }

    @ExceptionHandler({BindException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse validationExceptionHandler(BindException exception) {
        log.error("Validation exception. Cause: ", exception);
        return getValidationErrorResponse(exception.getLocalizedMessage(), exception.getBindingResult());
    }

    private ErrorResponse getValidationErrorResponse(String localizedMessage, BindingResult bindingResult) {
        return ErrorResponse.badRequest(
                ErrorResponse.Error.builder()
                        .message(localizedMessage)
                        .type(ErrorTypes.VALIDATION_ERROR)
                        .details(bindingResult
                                .getAllErrors()
                                .stream()
                                .map(error -> ErrorResponse.Error.Detail.builder()
                                        .type(error.getDefaultMessage())
                                        .target(error instanceof FieldError
                                                ? ((FieldError) error).getField()
                                                : null
                                        )
                                        .build())
                                .collect(Collectors.toList()))
                        .build()
        );
    }

    @ExceptionHandler(ValidationCollectorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse detailedException(ValidationCollectorException exception) {
        log.error("Validation exception. Cause: ", exception);
        return ErrorResponse.badRequest(ErrorResponse.Error.of(exception));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse requestBodyException(HttpMessageNotReadableException exception, HttpServletRequest request) {
        log.warn(
                String.format(
                        "Failed to read %s request %s body",
                        request.getMethod(),
                        request.getRequestURI()
                ),
                exception
        );
        return ErrorResponse.badRequest(
                ErrorResponse.Error.builder()
                        .message("Invalid param")
                        .type(ErrorTypes.BAD_REQUEST)
                        .build()
        );
    }

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse badRequestException(BadRequestException exception) {
        log.error("Bad request exception. Cause: ", exception);
        return ErrorResponse.badRequest(ErrorResponse.Error.of(exception));
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse illegalArgumentException(IllegalArgumentException exception) {
        log.error("Illegal argument exception. Cause: ", exception);
        return ErrorResponse.badRequest(
                ErrorResponse.Error.builder()
                        .message(exception.getMessage())
                        .type(ErrorTypes.BAD_REQUEST)
                        .build()
        );
    }

    @ExceptionHandler({ForbiddenException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse forbiddenException(ForbiddenException exception) {
        log.error("Forbidden exception. Cause: ", exception);
        return ErrorResponse.forbidden(ErrorResponse.Error.of(exception));
    }

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse notFoundException(NotFoundException exception) {
        log.error("Not found exception. Cause: ", exception);
        return ErrorResponse.notFound(ErrorResponse.Error.of(exception));
    }

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse notFoundException(EntityNotFoundException exception) {
        log.warn("Entity not found. Cause: ", exception);
        return ErrorResponse.notFound(
                ErrorResponse.Error.builder()
                        .message(exception.getMessage())
                        .type(ErrorTypes.NOT_FOUND)
                        .build()
        );
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse accessDeniedException(AccessDeniedException exception) {
        log.warn("Access exception. Cause: ", exception);
        return ErrorResponse.forbidden(
                ErrorResponse.Error.builder()
                        .message(exception.getMessage())
                        .type(ErrorTypes.FORBIDDEN)
                        .build()
        );
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponse authException(AuthenticationException exception) {
        log.error("Auth exception. Cause: ", exception);
        return ErrorResponse.unauthorized(
                ErrorResponse.Error.builder()
                        .message(exception.getMessage())
                        .type(ErrorTypes.UNAUTHORIZED)
                        .build()
        );
    }

    @ExceptionHandler({TokenExpiredException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponse tokenExpiredException(TokenExpiredException exception) {
        log.error("Auth exception. Cause: ", exception);
        return ErrorResponse.unauthorized(
                ErrorResponse.Error.builder()
                        .message(exception.getMessage())
                        .type(ErrorTypes.UNAUTHORIZED)
                        .build()
        );
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponse conflict(ConflictException exception) {
        log.error("Conflict exception. Cause: ", exception);
        return ErrorResponse.conflict(ErrorResponse.Error.of(exception));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse serverException(Exception exception, HttpServletRequest request) {
        log.error(
                String.format(
                        "Failed to process %s request %s",
                        request.getMethod(),
                        request.getRequestURI()
                )
        );

        return ErrorResponse.internalServerError(
                ErrorResponse.Error.builder()
                        .message("Server failed to process request")
                        .type(ErrorTypes.REQUEST_FAILED)
                        .details(List.of(
                                ErrorResponse.Error.Detail.builder()
                                        .message(exception.getMessage())
                                        .build()
                        ))
                        .build()
        );
    }
}
