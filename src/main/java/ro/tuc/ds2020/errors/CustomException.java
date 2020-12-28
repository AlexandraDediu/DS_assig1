package ro.tuc.ds2020.errors;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{

    private final HttpStatus httpStatus;

    public CustomException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public CustomException(String message) {
        super(message);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}