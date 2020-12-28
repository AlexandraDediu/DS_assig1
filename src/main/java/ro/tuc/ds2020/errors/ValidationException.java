package ro.tuc.ds2020.errors;

import org.springframework.http.HttpStatus;

public class ValidationException extends CustomException{

    public ValidationException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public ValidationException(String message) {
        super(message);
    }

}
