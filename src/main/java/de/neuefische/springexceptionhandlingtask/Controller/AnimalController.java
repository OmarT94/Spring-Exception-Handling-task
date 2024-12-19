package de.neuefische.springexceptionhandlingtask.Controller;

import de.neuefische.springexceptionhandlingtask.Model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    @GetMapping("{species}")
    String getAnimalSpecies(@PathVariable String species) {
        if (!species.equals("dog")) {
            throw new IllegalArgumentException("Only 'dog' is allowed");
        }
        return species;
    }

    @GetMapping
    String getAllAnimals() {
        throw new NoSuchElementException("No Animals found");
    }

    @GetMapping("/null")
    String getAllAnimalsNullException() throws Exception {
        throw new Exception("null");
    }

    //Local exception
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorMessage message = new ErrorMessage(ex.getMessage(), LocalDateTime.now().toString());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }


}
