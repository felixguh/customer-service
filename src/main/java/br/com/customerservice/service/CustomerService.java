package br.com.customerservice.service;

import org.springframework.stereotype.Service;

import br.com.customerservice.exception.CustomerNotExistsException;
import br.com.customerservice.model.mapper.CustomerMapper;
import br.com.customerservice.model.payload.CustomerPayload;
import br.com.customerservice.model.response.CustomerResponse;
import br.com.customerservice.repository.CustomerRepository;

@Service
public class CustomerService {

	private final CustomerRepository repository;

	public CustomerService(final CustomerRepository repository) {
		this.repository = repository;
	}

	public CustomerResponse create(final CustomerPayload payload) {
		final var entity = CustomerMapper.toEntity(payload);

		return CustomerResponse.builder().entity(repository.save(entity)).build();
	}

	public CustomerResponse findByCustomerNumber(final Long customerNumber) {
		final var entity = repository.findByCustomerNumber(customerNumber).orElseThrow(CustomerNotExistsException::new);

		return CustomerResponse.builder().entity(entity).build();
	}

}
