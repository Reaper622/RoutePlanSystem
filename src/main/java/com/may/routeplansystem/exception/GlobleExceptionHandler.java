package com.may.routeplansystem.exception;

import com.may.routeplansystem.entity.dto.ResponseEntity;
import com.may.routeplansystem.util.NetWorkUtil;
import com.may.routeplansystem.util.ServiceUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobleExceptionHandler {

    @ExceptionHandler(NetWorkUtil.URLVisitException.class)
    public ResponseEntity handleURLVisitException(NetWorkUtil.URLVisitException e){
        return new ResponseEntity<>(500, e.getMessage());
    }

    @ExceptionHandler(FinalSolutionUserChoiceException.class)
    public ResponseEntity handleFinalSolutionUserChoiceException(FinalSolutionUserChoiceException e){
        return new ResponseEntity<>(400, e.getMessage());
    }

    @ExceptionHandler(ServiceUtil.SqlExecuteException.class)
    public ResponseEntity handleSqlExecuteException(ServiceUtil.SqlExecuteException e){
        return new ResponseEntity<>(500, e.getMessage());
    }
}
