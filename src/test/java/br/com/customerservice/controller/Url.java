package br.com.customerservice.controller;

public enum Url {
	BASE_URL("/v1/customers"), 
	CUSTOMER_NUMBER(BASE_URL.getUrl().concat("/customer/1"));

	private final String url;

	private Url(final String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

}
