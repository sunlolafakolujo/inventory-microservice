package com.inventory.appuserservice.userrole.exception;

import com.inventory.appuserservice.address.errormessage.ValidateErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class RoleNotFoundExceptionRestHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ValidateErrorMessage> roleNotFoundException(RoleNotFoundException rnfe, WebRequest webRequest){
        ValidateErrorMessage vem=new ValidateErrorMessage(HttpStatus.NOT_FOUND, rnfe.getMessage(),
                webRequest.getDescription(false), new Date());
        return new ResponseEntity<>(vem, HttpStatus.BAD_REQUEST);
    }
}
