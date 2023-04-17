package com.example.pastebin.exception.handlers;

import com.example.pastebin.exception.BadRequestException;
import com.example.pastebin.exception.PasteNotFoundExeption;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class ControllerException  {
@ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequest(){
    return ResponseEntity.badRequest().build();
}
    @ExceptionHandler(PasteNotFoundExeption.class)
    public ResponseEntity<?> notFound(){
    return ResponseEntity.notFound().build();
    }
}
