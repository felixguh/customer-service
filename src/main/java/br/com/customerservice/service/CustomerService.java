package br.com.customerservice.service;

import org.springframework.stereotype.Service;

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

}
