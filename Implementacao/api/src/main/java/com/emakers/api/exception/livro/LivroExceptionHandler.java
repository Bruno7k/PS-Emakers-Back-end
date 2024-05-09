package com.emakers.api.exception.livro;

import com.emakers.api.exception.RestErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class LivroExceptionHandler {
    @ExceptionHandler(LivroNotFoundException.class)
    private ResponseEntity<RestErrorMessage> entityNotFoundHandler(LivroNotFoundException exception){
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(errorMessage.status()).body(errorMessage);
    }
}
