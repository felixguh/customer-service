package br.com.customerservice.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import br.com.customerservice.common.model.ErrorResponse;
import br.com.customerservice.model.payload.CustomerPayload;
import br.com.customerservice.model.response.CustomerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "CustomerApi")
public interface CustomerApi {

	@ApiOperation("Create customer")
	@ApiResponses({ @ApiResponse(code = 201, message = "Customer was created with success"),
			@ApiResponse(code = 400, message = "Wrong information was sent", response = ErrorResponse.class),
			@ApiResponse(code = 409, message = "Customer e-mail already is registered", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "An unexpected error occurred", response = ErrorResponse.class) })
	ResponseEntity<CustomerResponse> create(@Valid CustomerPayload payload);

	@ApiOperation("Find Customer by customerNumber")
	@ApiResponses({ @ApiResponse(code = 200, message = "Customer was found with success"),
			@ApiResponse(code = 204, message = "Customer not exists"),
			@ApiResponse(code = 400, message = "Wrong information was sent", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "An unexpected error occurred", response = ErrorResponse.class) })
	CustomerResponse findByCustomerNumber(
			@ApiParam(value = "Customer number", required = true, example = "1") final Long customerNumber);

}
