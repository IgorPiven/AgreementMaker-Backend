package com.example.AgreementMakerSpring.exceptions;

public class InvalidDiscountAmountException extends RuntimeException{

    public InvalidDiscountAmountException(String message) {
        super(message);
    }
}
