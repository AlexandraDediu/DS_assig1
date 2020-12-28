package ro.tuc.ds2020.controllers.security;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ro.tuc.ds2020.errors.CustomException;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler({CustomException.class})
    public final ResponseEntity handleCustomExceptions(CustomException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = ex.getHttpStatus();
        return handleException(ex, status, request);
    }

    @ExceptionHandler({Exception.class})
    public final ResponseEntity handleExceptions(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return handleException(ex, status, request);
    }

    protected ResponseEntity handleException(Exception ex, HttpStatus status, WebRequest request) {
        return new ResponseEntity(ex.getMessage(), status);
    }
}