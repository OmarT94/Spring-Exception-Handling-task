package de.neuefische.springexceptionhandlingtask.Exception;


import de.neuefische.springexceptionhandlingtask.Model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Global exception
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        ErrorMessage message = new ErrorMessage(e.getMessage(), LocalDateTime.now().toString());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);

    }

    //Global exception
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessage>handleNoSuchElementException(NoSuchElementException e) {
        ErrorMessage message = new ErrorMessage(e.getMessage(), LocalDateTime.now().toString());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGenericException(Exception ex) {
        ErrorMessage error = new ErrorMessage("An unexpected error occurred: " + ex.getMessage(),
                LocalDateTime.now().toString());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
