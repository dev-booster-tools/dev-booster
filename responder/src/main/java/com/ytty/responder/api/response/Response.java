package com.ytty.responder.api.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class Response<T> extends EmptyResponse {

    private T result;

    protected Response(T result, HttpStatus status) {
        super(status);
        this.result = result;
    }

    protected Response(T result, HttpStatus status, HttpHeaders headers) {
        super(status, headers);
        this.result = result;
    }

    /**
     * Response with http-status = 200
     *
     * @param result
     * @param <T>    - response data type
     * @return response with data type and http-status
     */
    public static <T> Response<T> ok(T result) {
        return new Response<>(result, HttpStatus.OK);
    }

    /**
     * Response with http-status = 201
     *
     * @param result
     * @param <T>    - response data type
     * @return response with data type and http-status
     */
    public static <T> Response<T> created(T result) {
        return new Response<>(result, HttpStatus.CREATED);
    }

    /**
     * Response with http-status = 202
     *
     * @param result
     * @param <T>    - response data type
     * @return response with data type and http-status
     */
    public static <T> Response<T> accepted(T result) {
        return new Response<>(result, HttpStatus.ACCEPTED);
    }

    @Override
    public boolean isEmpty() {
        return result == null;
    }
}
