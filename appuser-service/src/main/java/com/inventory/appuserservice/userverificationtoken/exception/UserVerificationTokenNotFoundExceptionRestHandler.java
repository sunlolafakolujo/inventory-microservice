package com.inventory.appuserservice.userverificationtoken.exception;

import com.inventory.appuserservice.address.errormessage.ValidateErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class UserVerificationTokenNotFoundExceptionRestHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserVerificationTokenNotFoundException.class)
    public ResponseEntity<ValidateErrorMessage> userVerificationNotFoundException(UserVerificationTokenNotFoundException uvtnfe,
                                                                                  WebRequest webRequest){
        ValidateErrorMessage vem=new ValidateErrorMessage(HttpStatus.NOT_FOUND,uvtnfe.getMessage(),
                webRequest.getDescription(false), new Date());
        return new ResponseEntity<>(vem,HttpStatus.BAD_REQUEST);
    }
}
