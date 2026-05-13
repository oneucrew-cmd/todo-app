package com.example.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
// говорит Spring: этот класс перехватывает ошибки со всего приложения

public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    // сработает когда выбросится TaskNotFoundException
    public ResponseEntity<Map<String, String>> handleNotFound(TaskNotFoundException ex) {

        Map<String, String> error = new HashMap<>();
        // создаём пустую коробку для ответа

        error.put("error", ex.getMessage());
        // кладём текст ошибки в коробку
        // например: { "error": "Task not found with id: 5" }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        // возвращаем клиенту 404 + коробку с текстом
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    // сработает когда @Valid найдёт ошибку валидации
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        // создаём пустую коробку для ошибок валидации

        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                        errors.put(fieldError.getField(), fieldError.getDefaultMessage())
                // проходим по всем ошибкам и кладём в коробку
                // например: { "title": "Title cannot be empty" }
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        // возвращаем клиенту 400 + список что именно не так
    }
}