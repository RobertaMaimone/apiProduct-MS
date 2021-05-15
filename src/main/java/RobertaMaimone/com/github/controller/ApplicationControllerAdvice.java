package RobertaMaimone.com.github.controller;

import RobertaMaimone.com.github.exception.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handleResponseStatusException(ResponseStatusException ex){
        ApiErrors apiErrors = new ApiErrors();
        apiErrors.setMessage(ex.getReason());
        apiErrors.setStatus(ex.getStatus().value());

        return new ResponseEntity(apiErrors, HttpStatus.NOT_FOUND);
    }
}
