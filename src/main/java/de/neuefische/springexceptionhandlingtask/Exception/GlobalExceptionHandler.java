package de.neuefische.springexceptionhandlingtask.Exception;


import de.neuefische.springexceptionhandlingtask.Model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        ErrorMessage message = new ErrorMessage(e.getMessage(), LocalDateTime.now().toString());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessage>handleNoSuchElementException(NoSuchElementException e) {
        ErrorMessage message = new ErrorMessage(e.getMessage(), LocalDateTime.now().toString());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
