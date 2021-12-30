package br.com.meli.springchallenge.exceptions.advice;

import br.com.meli.springchallenge.exceptions.ListProductIsEmptyException;
import br.com.meli.springchallenge.exceptions.OutOfStockException;
import br.com.meli.springchallenge.exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionsAdvice {

        @ExceptionHandler(value = OutOfStockException.class)
        protected ResponseEntity<?> noStockException(OutOfStockException ex, WebRequest request) {
            String bodyOfResponse = ex.getMessage();
            return ResponseEntity.badRequest().body(bodyOfResponse);
        }

        @ExceptionHandler(value = ProductNotFoundException.class)
        protected ResponseEntity<?> noStockException(ProductNotFoundException ex, WebRequest request) {
            String bodyOfResponse = ex.getMessage();
            return ResponseEntity.badRequest().body(bodyOfResponse);
        }

        @ExceptionHandler(value = ListProductIsEmptyException.class)
        protected ResponseEntity<?> noStockException(ListProductIsEmptyException ex, WebRequest request) {
            String bodyOfResponse = ex.getMessage();
            return ResponseEntity.badRequest().body(bodyOfResponse);
        }
}

