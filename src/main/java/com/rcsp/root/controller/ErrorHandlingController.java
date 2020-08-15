package com.rcsp.root.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.rcsp.root.common.ResponseType;
import com.rcsp.root.model.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Collections;

/**
 * Created by satish on 09/08/20.
 */
@ControllerAdvice
@ApiIgnore
@Slf4j
public class ErrorHandlingController {

    /**
     * @param e
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Result handleNullPointerException(final NullPointerException e) {
        log.error(e.getMessage());
        return new Result(ResponseType.INTERNAL_SERVER_ERROR, Collections.emptySet());
    }

    /**
     * @param e
     * @return
     */
    @ExceptionHandler(JsonParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Result handleBadRequestException(final JsonParseException e) {
        log.error(e.getOriginalMessage());
        return new Result(ResponseType.BAD_REQUEST, Collections.emptySet());
    }

    /**
     * @param e
     * @return
     */
    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Result handleInvalidFormatException(final InvalidFormatException e) {
        log.error(e.getOriginalMessage());
        return new Result(ResponseType.BAD_REQUEST, Collections.emptySet());
    }


}
