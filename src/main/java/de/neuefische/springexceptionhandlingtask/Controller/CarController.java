package de.neuefische.springexceptionhandlingtask.Controller;

import de.neuefische.springexceptionhandlingtask.Model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @GetMapping("{brand}")
    String getCarBrand(@PathVariable String brand) {
        if (!brand.equals("porsche")) {
            throw new IllegalArgumentException("Only 'porsche' allowed");
        }
        return brand;
    }

    @GetMapping
    String getAllCars() {
        throw new NoSuchElementException("No Cars found");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorMessage error = new ErrorMessage(ex.getMessage(), LocalDateTime.now().toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
