package br.com.projects.challenge.softexpert.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(NumberExceptions.class)
    public ResponseEntity<StandardError> numberException(NumberExceptions e, HttpServletRequest request){
        String error = "Valor total dos itens é zero!";
        HttpStatus httpStatus = HttpStatus.PRECONDITION_FAILED;
        StandardError err = new StandardError(Instant.now(), httpStatus.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(err);
    }
}
