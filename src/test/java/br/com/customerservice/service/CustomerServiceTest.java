package br.com.customerservice.service;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.customerservice.exception.CustomerNotExistsException;
import br.com.customerservice.exception.EmailAlreadyExistsToCustomerException;
import br.com.customerservice.helper.CustomerBuilder;
import br.com.customerservice.helper.CustomerPayloadBuilder;
import br.com.customerservice.model.Customer;
import br.com.customerservice.model.payload.CustomerPayload;
import br.com.customerservice.repository.CustomerRepository;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

	@Mock
	private CustomerRepository repository;

	@InjectMocks
	private CustomerService service;

	private static final CustomerPayload PAYLOAD = CustomerPayloadBuilder.create().now();
	
	private static final Long CUSTOMER_NUMBER = 1L;

	@Test
	public void shouldCreateCustomer() {
		theSameToCreateCustomer(PAYLOAD, FALSE);

		verify(repository).save(ArgumentMatchers.any());
	}

	private void theSameToCreateCustomer(final CustomerPayload payload, final Boolean result) {
		when(repository.existsByEmail(payload.getEmail())).thenReturn(result);

		when(repository.save(ArgumentMatchers.any())).thenReturn(CustomerBuilder.create().now());

		service.create(payload);

		verify(repository).existsByEmail(payload.getEmail());
	}

	@Test(expected = EmailAlreadyExistsToCustomerException.class)
	public void whenCreateCustomerWithAlreadyExistsEmailShouldReturnEmailAlreadyExistsToCustomerException() {
		theSameToCreateCustomer(PAYLOAD, TRUE);
	}

	@Test
	public void shouldFindCustomerByCustomerNumber() {
		final var customerOptional = CustomerBuilder.create().optional();

		theSameToFindCustomer(CUSTOMER_NUMBER, customerOptional);
	}

	private void theSameToFindCustomer(final long customerNumber, final Optional<Customer> customerOptional) {
		when(repository.findByCustomerNumber(customerNumber)).thenReturn(customerOptional);

		service.findByCustomerNumber(customerNumber);

		verify(repository).findByCustomerNumber(customerNumber);
	}

	@Test(expected = CustomerNotExistsException.class)
	public void whenFindCustomerWithNotExistsCustomerShouldReturnCustomerNotExistsException() {
		theSameToFindCustomer(CUSTOMER_NUMBER, Optional.empty());
	}

}
