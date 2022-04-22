package com.example.AgreementMakerSpring.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ErrorDto {

    private int status;
    private String message;
    private Date timestamp;


    public ErrorDto(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();

    }
}
