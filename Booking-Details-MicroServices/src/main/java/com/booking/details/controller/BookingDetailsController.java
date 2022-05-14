package com.booking.details.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.booking.details.model.BookedTicketDetails;
import com.booking.details.services.BookingDetailsService;

@RestController
@RequestMapping("api/book")
public class BookingDetailsController {

	Logger logger = LoggerFactory.getLogger(BookingDetailsController.class);

	@Autowired
	private BookingDetailsService bookingDetailsService;

	/* Saving Booked Ticket Details */
	@PostMapping("{trainNumber}")
	public ResponseEntity<?> saveBokBookedTicketDetails(@RequestBody @Valid BookedTicketDetails bookedTicketDetails,
			@PathVariable int trainNumber) {
		logger.trace("Ticket Added Succesfully");
		return bookingDetailsService.saveBookingDetails(bookedTicketDetails, trainNumber);

	}

	@GetMapping("{pnr}")
	public ResponseEntity<?> getTicketByPnr(@PathVariable Long pnr) {
		logger.trace("Showing ticket by pnr");
		return new ResponseEntity<BookedTicketDetails>(bookingDetailsService.getTicketByPnr(pnr), HttpStatus.OK);
	}

	@GetMapping("/booked/{username}")
	public ResponseEntity<List<BookedTicketDetails>> getTicketByUsername(@PathVariable String username) {
		return new ResponseEntity<List<BookedTicketDetails>>(bookingDetailsService.findByUsername(username),
				HttpStatus.OK);
	}

	@DeleteMapping("{pnr}")
	public String deleteTicketByPnr(@PathVariable Long pnr) {
		logger.trace("Deleting ticket by pnr");
		return bookingDetailsService.deleteTicketByPnr(pnr);
	}
}
