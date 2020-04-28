package br.com.customerservice.helper;

import br.com.customerservice.model.response.CustomerResponse;

public class CustomerResponseBuilder {

	private CustomerResponse dataToMock;
	
	private CustomerResponseBuilder() {
		this.dataToMock = CustomerResponse.builder().entity(CustomerBuilder.create().now()).build();
	}
	
	public static CustomerResponseBuilder create() {
		return new CustomerResponseBuilder();
	}
	
	public CustomerResponse now() {
		return this.dataToMock;
	}
}
