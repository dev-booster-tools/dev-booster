package com.ytty.responder.api.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@JsonSerialize
public class EmptyResponse {

    @JsonIgnore
    private HttpStatus status;

    @JsonIgnore
    private HttpHeaders headers;

    protected EmptyResponse(HttpStatus status) {
        this.status = status;
    }

    protected EmptyResponse(HttpStatus status, HttpHeaders headers) {
        this.status = status;
        this.headers = headers;
    }

    /**
     * Response with http-status = 200
     *
     * @return response with http-status
     */
    public static EmptyResponse ok() {
        return new EmptyResponse(HttpStatus.OK);
    }

    /**
     * Response with http-status = 201
     *
     * @return response with http-status
     */
    public static EmptyResponse created() {
        return new EmptyResponse(HttpStatus.CREATED);
    }

    /**
     * Response with http-status = 202
     *
     * @return response with http-status
     */
    public static EmptyResponse accepted() {
        return new EmptyResponse(HttpStatus.ACCEPTED);
    }

    /**
     * Response with http-status = 204
     *
     * @return response with http-status
     */
    public static EmptyResponse noContent() {
        return new EmptyResponse(HttpStatus.NO_CONTENT);
    }

    @JsonIgnore
    public boolean isEmpty() {
        return true;
    }
}
