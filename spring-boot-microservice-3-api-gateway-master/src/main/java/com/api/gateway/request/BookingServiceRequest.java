package com.api.gateway.request;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "booking-details-microservice", // microservice-application-name
		path = "api/book", // pre-path for service endpoints
		configuration = FeignConfiguration.class)
public interface BookingServiceRequest {

	@GetMapping("{pnr}")
	Object getTrain(@PathVariable Long pnr);

	@PostMapping("{trainNumber}")
	Object saveTicket(@PathVariable int trainNumber, @RequestBody Object requestBody);

	@DeleteMapping("{pnr}")
	String cancelTicket(@PathVariable Long pnr);

	@GetMapping("/booked/{username}")
	List<Object> getTicketByUsername(@PathVariable String username);

}
