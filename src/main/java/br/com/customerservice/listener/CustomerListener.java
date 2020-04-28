package br.com.customerservice.listener;

import static br.com.customerservice.model.Customer.CUSTOMER_SEQUENCE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import br.com.customerservice.model.Customer;
import br.com.customerservice.service.SequenceGeneratorService;

@Component
public class CustomerListener extends AbstractMongoEventListener<Customer> {

	private final SequenceGeneratorService sequenceGenerator;

	@Autowired
	public CustomerListener(SequenceGeneratorService sequenceGenerator) {
		this.sequenceGenerator = sequenceGenerator;
	}

	@Override
	public void onBeforeConvert(BeforeConvertEvent<Customer> event) {
		if (event.getSource().getCustomerNumber() == null)
			event.getSource().setCustomerNumber(sequenceGenerator.generateSequence(CUSTOMER_SEQUENCE));
	}

}
