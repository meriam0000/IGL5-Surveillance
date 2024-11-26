package com.example.Surveillance.Exception;

import java.text.SimpleDateFormat;
import java.util.Date;

public record ErrorDetails(String at, String message, String details) {
    public ErrorDetails(Date date, String message, String details) {
        this(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(date), message, details);
    }
}