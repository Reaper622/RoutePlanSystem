package com.may.routeplansystem.exception;

import com.may.routeplansystem.entity.dto.ResponseEntity;
import com.may.routeplansystem.util.NetWorkUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobleExceptionHandler {

    @ExceptionHandler(NetWorkUtil.URLVisitException.class)
    public ResponseEntity handleURLVisitException(NetWorkUtil.URLVisitException e){
        return new ResponseEntity<>(500, e.getMessage());
    }
}
