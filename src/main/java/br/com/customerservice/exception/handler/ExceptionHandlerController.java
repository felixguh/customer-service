package br.com.customerservice.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.customerservice.exception.CustomerNotExistsException;

@RestControllerAdvice
public class ExceptionHandlerController {
	
    @ExceptionHandler(CustomerNotExistsException.class)
    public ResponseEntity<Object> handleCustomerNotExistsException() {
        return ResponseEntity.noContent().build();
    }
    

}
