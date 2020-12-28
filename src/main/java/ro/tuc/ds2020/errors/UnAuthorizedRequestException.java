package ro.tuc.ds2020.errors;

import org.springframework.http.HttpStatus;

public class UnAuthorizedRequestException extends CustomException {

    public UnAuthorizedRequestException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public UnAuthorizedRequestException(String message) {
        super(message);
    }

}