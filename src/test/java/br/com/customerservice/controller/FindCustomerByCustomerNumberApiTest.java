package br.com.customerservice.controller;

import static org.mockito.Mockito.doThrow;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.customerservice.exception.CustomerNotExistsException;
import br.com.customerservice.exception.handler.ExceptionHandlerController;
import br.com.customerservice.service.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CustomerController.class)
@ContextConfiguration(classes = { CustomerController.class, ExceptionHandlerController.class })
public class FindCustomerByCustomerNumberApiTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	@Test
	public void shouldFindCustomerByCustomerNumberAndReturnOk() throws Exception {
		mockMvc.perform(get(Url.CUSTOMER_NUMBER.getUrl()).accept(APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void whenFindCustomerByCustomerNumberAndNotExistsCustomerShouldReturnNoContent() throws Exception {
		doThrow(new CustomerNotExistsException()).when(customerService).findByCustomerNumber(1L);
		
		mockMvc.perform(get(Url.CUSTOMER_NUMBER.getUrl()).accept(APPLICATION_JSON)).andExpect(status().isNoContent());
	}

}
