package br.com.customerservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.customerservice.model.payload.CustomerPayload;
import br.com.customerservice.model.response.CustomerResponse;
import br.com.customerservice.service.CustomerService;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController implements CustomerApi {

	private final CustomerService service;

	public CustomerController(final CustomerService service) {
		this.service = service;
	}

	@Override
	public ResponseEntity<CustomerResponse> create(@RequestBody final CustomerPayload payload) {
		final var response = service.create(payload);

		final var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/customer/{customerNumber}")
				.buildAndExpand(response.getCustomerNumber()).toUri();

		return ResponseEntity.created(uri).body(response);

	}

	@Override
	public CustomerResponse findByCustomerNumber(@PathVariable Long customerNumber) {
		return null;
	}

}
