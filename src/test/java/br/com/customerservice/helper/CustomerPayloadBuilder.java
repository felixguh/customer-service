package br.com.customerservice.helper;

import br.com.customerservice.model.payload.CustomerPayload;

public class CustomerPayloadBuilder {

	private CustomerPayload dataToMock;

	private CustomerPayloadBuilder() {
		this.dataToMock = CustomerPayload.builder().email("jhon.doe@gmail.com").name("Jhon Doe").build();
	}
	
	public static CustomerPayloadBuilder create() {
		return new CustomerPayloadBuilder();
	}
	
	public CustomerPayload now() {
		return this.dataToMock;
	}
	
	public CustomerPayloadBuilder withEmail(final String email) {
		this.dataToMock.setEmail(email);
		
		return this;
	}
	
	public CustomerPayloadBuilder withName(final String name) {
		this.dataToMock.setName(name);
		
		return this;
	}

}
