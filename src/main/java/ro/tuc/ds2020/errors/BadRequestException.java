package ro.tuc.ds2020.errors;

import org.springframework.http.HttpStatus;

public class BadRequestException extends CustomException{

    public BadRequestException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public BadRequestException(String message) {
        super(message);
    }

}
