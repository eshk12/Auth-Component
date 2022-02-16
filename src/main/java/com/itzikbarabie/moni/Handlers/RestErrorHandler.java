package com.itzikbarabie.moni.Handlers;

import com.itzikbarabie.moni.Entity.ExceptionResponseEntity;
import com.itzikbarabie.moni.Exceptions.CustomException;
import com.itzikbarabie.moni.Exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CustomException.class, UnauthorizedException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponseEntity handleCustomException(Exception ce) {
        return new ExceptionResponseEntity(ce.getMessage());
    }

}

