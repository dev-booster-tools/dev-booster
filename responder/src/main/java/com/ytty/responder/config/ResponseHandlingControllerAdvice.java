package com.ytty.responder.config;

import com.ytty.responder.api.response.EmptyResponse;
import jakarta.annotation.Nonnull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Optional;

@ControllerAdvice(annotations = RestController.class, assignableTypes = ExceptionHandlingControllerAdvice.class)
public class ResponseHandlingControllerAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(@Nonnull MethodParameter returnType,
                            @Nonnull Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  @Nonnull MethodParameter returnType,
                                  @Nonnull MediaType selectedContentType,
                                  @Nonnull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @Nonnull ServerHttpRequest request,
                                  @Nonnull ServerHttpResponse response) {
        if (body instanceof EmptyResponse responseBody) {
            Optional.ofNullable(responseBody.getStatus()).ifPresent(response::setStatusCode);
            Optional.ofNullable(responseBody.getHeaders()).ifPresent(headers -> response.getHeaders().putAll(headers));
        }
        return body;
    }
}
