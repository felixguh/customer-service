package br.com.customerservice.helper;

import java.util.Optional;

import br.com.customerservice.model.Customer;
import br.com.customerservice.model.mapper.CustomerMapper;

public class CustomerBuilder {
	
	private Customer dataToMock;
	
	private CustomerBuilder() {
		this.dataToMock = CustomerMapper.toEntity(CustomerPayloadBuilder.create().now());
	}
	
	public static CustomerBuilder create() {
		return new CustomerBuilder();
	}
	
	public Customer now() {
		return this.dataToMock;
	}
	
	public Optional<Customer> optional(){
		return Optional.of(this.dataToMock);
	}

}
