package br.com.customerservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.customerservice.model.payload.CustomerPayload;
import br.com.customerservice.model.response.CustomerResponse;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController implements CustomerApi {

	@Override
	public ResponseEntity<CustomerResponse> create(@RequestBody final CustomerPayload payload) {
		return null;
	}

	@Override
	public CustomerResponse findByCustomerNumber(@PathVariable Long customerNumber) {
		
		return null;
	}

}
