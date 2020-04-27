package br.com.customerservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.customerservice.helper.CustomerBuilder;
import br.com.customerservice.listener.CustomerListener;
import br.com.customerservice.model.Customer;

@RunWith(SpringRunner.class)
public class CustomerListenerTest {

	@Mock
	private SequenceGeneratorService sequenceGeneratorService;

	@InjectMocks
	private CustomerListener productListener;

	public static final String CUSTOMER_SEQUENCE = "customer_sequence";
	
	private static final String COLLECTION_NAME =  "customers";

	@Test
	public void shouldGenerateSequenceProductNumber() {
		final var event = new BeforeConvertEvent<Customer>(new Customer(), COLLECTION_NAME);

		when(sequenceGeneratorService.generateSequence(CUSTOMER_SEQUENCE)).thenReturn(1L);

		productListener.onBeforeConvert(event);

		assertThat(event.getCollectionName()).isEqualTo(COLLECTION_NAME);
	}

	@Test
	public void shouldGenerateSequenceWithNotNullSources() {
		final var customer = CustomerBuilder.create().now();

		final var event = new BeforeConvertEvent<Customer>(customer, COLLECTION_NAME);

		productListener.onBeforeConvert(event);

		assertNotNull(customer);
	}

}
