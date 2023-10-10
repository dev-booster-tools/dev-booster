package com.ytty.responder.api.exception;

import com.ytty.responder.api.exception.base.BaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class BadRequestException extends BaseException {

    private final String errorType;
    private String message;
}
