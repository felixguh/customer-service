package br.com.customerservice.model.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.customerservice.util.validator.CompoundName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerPayload {

	@NotBlank(message = "{CustomerPayload.name.notBlank}")
	@CompoundName(message = "{CustomerPayload.name.invalid}")
	private String name;

	@NotBlank(message = "{CustomerPayload.email.notBlank}")
	@Email(message = "{CustomerPayload.email.invalid}")
	private String email;

}
