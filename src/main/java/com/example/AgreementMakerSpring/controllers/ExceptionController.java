package com.example.AgreementMakerSpring.controllers;

import com.example.AgreementMakerSpring.dto.ErrorDto;
import com.example.AgreementMakerSpring.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity<?> catchResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> catchInvalidFileExtensionException(InvalidFileExtensionException e) {
        return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> catchFileNameNotFoundException(FileNameNotFoundException e) {
        return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> catchNoAnyDiscountException(NoAnyDiscountException e) {
        return new ResponseEntity<>(new ErrorDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> catchInvalidDiscountAmountException(InvalidDiscountAmountException e) {
        return new ResponseEntity<>(new ErrorDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> catchLoadDiscountsErrorException(LoadDiscountsErrorException e) {
        return new ResponseEntity<>(new ErrorDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
