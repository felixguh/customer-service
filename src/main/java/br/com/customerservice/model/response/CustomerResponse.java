package br.com.customerservice.model.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.customerservice.model.Customer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(description = "Customer data")
public class CustomerResponse {
	
	@JsonIgnore
	private final Customer entity;
	
	@ApiModelProperty(value = "Customer id", example = "1")
	public Long getCustomerNumber() {
		return entity.getCustomerNumber();
	}
	
	@ApiModelProperty(value = "Customer name", example = "Jhon Doe")
	public String getName() {
		return entity.getName();
	}
	
	@ApiModelProperty(value = "Customer e-mail", example = "jhon.doe@gmail.com")
	public String getEmail() {
		return entity.getEmail();
	}
	
	@ApiModelProperty(value = "Customer creation date", example = "2020-04-25T15:53:46.673577")
	public LocalDateTime getCreationDate() {
		return entity.getCreationDate();
	}
	
	@ApiModelProperty(value = "Customer change date", example = "2020-04-25T15:53:46.673577")
	public LocalDateTime getLastModifiedDate() {
		return entity.getLastModifiedDate();
	}

}
