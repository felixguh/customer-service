package br.com.customerservice.controller;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.customerservice.exception.EmailAlreadyExistsToCustomerException;
import br.com.customerservice.exception.handler.ExceptionHandlerController;
import br.com.customerservice.helper.CustomerPayloadBuilder;
import br.com.customerservice.helper.CustomerResponseBuilder;
import br.com.customerservice.model.payload.CustomerPayload;
import br.com.customerservice.service.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CustomerController.class)
@ContextConfiguration(classes = { CustomerController.class, ExceptionHandlerController.class })
public class CreateCustomerApiTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private CustomerService customerService;

	@Test
	public void shouldCreateCustomerAndReturnCreated() throws Exception {
		final var payload = CustomerPayloadBuilder.create().now();

		when(customerService.create(payload)).thenReturn(CustomerResponseBuilder.create().now());

		callPost(payload, 201);
	}
	
	@Test
	public void whenCreateCustomerWithExistsEmailShouldReturnConflict() throws Exception {
		final var payload = CustomerPayloadBuilder.create().now();
		
		doThrow(new EmailAlreadyExistsToCustomerException()).when(customerService).create(payload);

		callPost(payload, 409);
	}

	@Test
	public void whenCreateCustomerWithNullNameShouldReturnBadRequest() throws Exception {
		final var payload = CustomerPayloadBuilder.create().withName(null).now();

		callPost(payload, 400);
	}

	@Test
	public void whenCreateCustomerWithNotCompoundNameShouldReturnBadRequest() throws Exception {
		final var payload = CustomerPayloadBuilder.create().withName("Jhon").now();

		callPost(payload, 400);
	}

	@Test
	public void whenCreateCustomerWithNullEmailShouldReturnBadRequest() throws Exception {
		final var payload = CustomerPayloadBuilder.create().withEmail(null).now();

		callPost(payload, 400);
	}

	@Test
	public void whenCreateCustomerWithInvalidEmailShouldReturnBadRequest() throws Exception {
		final var payload = CustomerPayloadBuilder.create().withEmail("jhon.doe").now();

		callPost(payload, 400);
	}
	
	

	private void callPost(final CustomerPayload payload, final int status) throws Exception {
		mockMvc.perform(
				post(Url.BASE_URL.getUrl()).contentType(APPLICATION_JSON).content(mapper.writeValueAsString(payload)))
				.andExpect(status().is(status));
	}

}
