package br.com.customerservice.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.customerservice.exception.CustomerNotExistsException;

@RestControllerAdvice
public class ExceptionHandlerController {
	
	 private final MessageSource messageSource;

	    public ExceptionHandlerController(final MessageSource messageSource) {
	        this.messageSource = messageSource;
	    }
	
    @ExceptionHandler(CustomerNotExistsException.class)
    public ResponseEntity<Object> handleCustomerNotExistsException() {
        return ResponseEntity.noContent().build();
    }
    

}
