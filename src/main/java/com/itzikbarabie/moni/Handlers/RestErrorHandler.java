package com.itzikbarabie.moni.Handlers;

import com.itzikbarabie.moni.Entity.ExceptionResponseEntity;
import com.itzikbarabie.moni.Exceptions.CustomException;
import com.itzikbarabie.moni.Exceptions.UnauthorizedException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
@Slf4j
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CustomException.class, UnauthorizedException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponseEntity handleCustomException(Exception ce) {
        if(log.isInfoEnabled()) {
            log.info(ce.getStackTrace()[0]+" Has Retreive an a exception: "+ce.getMessage());
        }
        return new ExceptionResponseEntity(ce.getMessage());
    }

}

