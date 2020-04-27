package br.com.customerservice.model.mapper;

import br.com.customerservice.model.Customer;
import br.com.customerservice.model.payload.CustomerPayload;

public class CustomerMapper {

	private CustomerMapper() {

	}

	public static Customer toEntity(final CustomerPayload payload) {
		return Customer.builder().email(payload.getEmail()).name(payload.getName()).build();
	}

}
