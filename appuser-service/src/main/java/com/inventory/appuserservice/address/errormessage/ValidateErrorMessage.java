package com.inventory.appuserservice.address.errormessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateErrorMessage {
    private HttpStatus httpStatus;
    private String message;
    private String description;
    private Date date;

}
