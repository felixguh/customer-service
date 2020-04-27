package br.com.customerservice.service;

import org.springframework.stereotype.Service;

import br.com.customerservice.repository.CustomerRepository;

@Service
public class CustomerService {

	private final CustomerRepository repository;

	public CustomerService(final CustomerRepository repository) {
		this.repository = repository;
	}

}
