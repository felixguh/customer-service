package br.com.customerservice.model;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
	
	@Transient
	public static final String CUSTOMER_SEQUENCE = "customer_sequence";
	
	@Id
	private ObjectId id;
	
	private Long customerNumber;
	
	private String name;
	
	private String email;
	
	@CreatedDate
	private LocalDateTime creationDate;
	
	@LastModifiedDate
	private LocalDateTime lastModifiedDate;

}
