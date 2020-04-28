package br.com.customerservice.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.customerservice.model.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, ObjectId> {
	
	Optional<Customer> findByCustomerNumber(final Long customerNumber);
	
	boolean existsByEmail(final String email);

}
