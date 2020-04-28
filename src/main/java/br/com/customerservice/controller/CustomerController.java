package br.com.customerservice.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.customerservice.model.payload.CustomerPayload;
import br.com.customerservice.model.response.CustomerResponse;
import br.com.customerservice.service.CustomerService;

@RestController
@RequestMapping("/v1/customers")
@CrossOrigin(origins = "*")
public class CustomerController implements CustomerApi {

	private final CustomerService service;

	@Autowired
	public CustomerController(final CustomerService service) {
		this.service = service;
	}

	@PostMapping
	@Override
	public ResponseEntity<CustomerResponse> create(@RequestBody final CustomerPayload payload) {
		final var response = service.create(payload);

		final var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/customer/{customerNumber}")
				.buildAndExpand(response.getCustomerNumber()).toUri();

		return ResponseEntity.created(uri).body(response);

	}

	@GetMapping("/customer/{customerNumber}")
	@ResponseStatus(code = OK)
	@Override
	public CustomerResponse findByCustomerNumber(@PathVariable Long customerNumber) {
		return service.findByCustomerNumber(customerNumber);
	}

}
