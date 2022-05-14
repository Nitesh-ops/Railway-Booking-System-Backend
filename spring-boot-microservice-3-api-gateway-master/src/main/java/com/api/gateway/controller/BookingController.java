package com.api.gateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.gateway.request.BookingServiceRequest;

@RestController
@RequestMapping("gateway/book") // pre-path
public class BookingController {
	@Autowired
	private BookingServiceRequest bookingServiceRequest;

	@GetMapping("/{pnr}")
	public Object getTrain(@PathVariable Long pnr) {
		return bookingServiceRequest.getTrain(pnr);
	}

	@PostMapping("/createTicket/{trainNumber}")
	public Object saveTicket(@PathVariable int trainNumber, @RequestBody Object bookingDetail) {
		return bookingServiceRequest.saveTicket(trainNumber, bookingDetail);
	}

	@DeleteMapping("/cancelTicket/{pnr}")
	public ResponseEntity<String> cancelTicket(@PathVariable Long pnr) {
		return new ResponseEntity<String>(bookingServiceRequest.cancelTicket(pnr), HttpStatus.OK);
	}

	@GetMapping("/booked/{username}")
	List<Object> getTicketByUsername(@PathVariable String username) {
		return bookingServiceRequest.getTicketByUsername(username);
	}
}
