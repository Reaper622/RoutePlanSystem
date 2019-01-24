package com.may.routeplansystem.exception;

import com.may.routeplansystem.constant.ResponseStatu;
import com.may.routeplansystem.entity.dto.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * @author 10587
 */
@RestControllerAdvice
@Slf4j
public class GlobleExceptionHandler {

    @ExceptionHandler(AuthentationException.class)
    public ResponseEntity authentationExceptionHandler(AuthentationException e) {
        return new ResponseEntity<>(ResponseStatu.AUTHENTATION_FIAL, e.getMessage(), null);
    }

    @ExceptionHandler(ThreeServiceException.class)
    public ResponseEntity baiduMapException(ThreeServiceException e) {
        return new ResponseEntity<>(ResponseStatu.THREE_SERVICE_FILE, e.getMessage(), null);
    }

    @ExceptionHandler(FinalSolutionUserChoiceException.class)
    public ResponseEntity finalSolutionUserChoiceExceptionHandler(FinalSolutionUserChoiceException e) {
        return new ResponseEntity<>(ResponseStatu.SERVER_EXCUTE_FAIL, e.getMessage(), null);
    }

    @ExceptionHandler(NodeTransferException.class)
    public ResponseEntity nodeTransferExceptionHandler(NodeTransferException e) {
        return new ResponseEntity<>(ResponseStatu.FILE_TRANSFER_FIAL, e.getMessage(), null);
    }

    @ExceptionHandler(ParameterException.class)
    public ResponseEntity parameterExceptionHandler(ParameterException e) {
        return new ResponseEntity<>(ResponseStatu.PARMETER_INVALIATE, e.getMessage(), null);
    }

    @ExceptionHandler(ProcessException.class)
    public ResponseEntity processExceptionHandler(ProcessException e) {
        return new ResponseEntity<>(ResponseStatu.USER_OPERATION_ERROR, e.getMessage(), null);
    }

    @ExceptionHandler(SqlExecutedException.class)
    public ResponseEntity sqlExecutedExceptionHandler(SqlExecutedException e) {
        return new ResponseEntity<>(ResponseStatu.SERVER_EXCUTE_FAIL, e.getMessage(), null);
    }

    @ExceptionHandler(VehicleTransferException.class)
    public ResponseEntity vehicleTransferException(VehicleTransferException e) {
        return new ResponseEntity<>(ResponseStatu.FILE_TRANSFER_FIAL, e.getMessage(), null);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity constraintViolationExceptionHandler(ConstraintViolationException e){
        return new ResponseEntity<>(ResponseStatu.PARMETER_INVALIATE, e.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity vehicleTransferException(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(ResponseStatu.SERVER_EXCUTE_FAIL, e.getMessage(), null);
    }


}
