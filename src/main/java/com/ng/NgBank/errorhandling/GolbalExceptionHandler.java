package com.ng.NgBank.errorhandling;

import com.ng.NgBank.exception.AccountUnverified;
import com.ng.NgBank.exception.InvalidAccountNo;
import com.ng.NgBank.response.ExceptionResponse;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@RestControllerAdvice
public class GolbalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handler(
            BadCredentialsException exception) {
        var e = ExceptionResponse.builder()
                .error(exception.getMessage())
                .build();
        return ResponseEntity.badRequest().body(e);
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ExceptionResponse> handler(
            MessagingException exception) {
        var e = ExceptionResponse.builder()
                .message("Internal Server Error. Please Contact the developer" +
                        " team")
                .build();
        return ResponseEntity.badRequest().body(e);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handler(
            SQLIntegrityConstraintViolationException exception) {
        var e = ExceptionResponse.builder()
                .message("You are already registered with that email.Please " +
                        "register with new email or proceed to log in")
                .build();
        return ResponseEntity.badRequest().body(e);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionResponse> handler(
            NoSuchElementException exception) {
        var e = ExceptionResponse.builder()
                .message("The user with the provided credentials does not " +
                        "exist in the database")
                .error(exception.getMessage())
                .build();
        return ResponseEntity.badRequest().body(e);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handler(
            MethodArgumentNotValidException exception) {
        Set<String> validationErros = new HashSet<>();
        exception.getBindingResult().getAllErrors().forEach(e -> validationErros.add(e.getDefaultMessage()));
        var e = ExceptionResponse.builder()
                .validationErros(validationErros)
                .error("Method Argumnt not is not valid")
                .build();
        return ResponseEntity.badRequest().body(e);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handler(
            IllegalArgumentException exception) {
        var e = ExceptionResponse.builder()
                .error(exception.getMessage())
                .build();
        return ResponseEntity.badRequest().body(e);
    }

    @ExceptionHandler(AccountUnverified.class)
    public ResponseEntity<ExceptionResponse> handler(
            AccountUnverified exception) {
        var e = ExceptionResponse.builder()
                .error(exception.getMessage())
                .build();
        return ResponseEntity.badRequest().body(e);
    }

    @ExceptionHandler(InvalidAccountNo.class)
    public ResponseEntity<ExceptionResponse> handler(
            InvalidAccountNo exception) {
        var e = ExceptionResponse.builder()
                .error(exception.getMessage())
                .build();
        return ResponseEntity.badRequest().body(e);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handler(
            RuntimeException exception) {
        var e = ExceptionResponse.builder()
                .error(exception.getMessage())
                .build();
        return ResponseEntity.badRequest().body(e);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handler(
            Exception exception) {
        var e = ExceptionResponse.builder()
                .message("Internal Server Error. Please Contact the developer" +
                        " team")
                .build();
        exception.printStackTrace();
        return ResponseEntity.badRequest().body(e);
    }


}
