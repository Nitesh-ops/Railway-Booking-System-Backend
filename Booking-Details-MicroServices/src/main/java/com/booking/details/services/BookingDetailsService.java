package com.booking.details.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.booking.details.model.BookedTicketDetails;

public interface BookingDetailsService {

	ResponseEntity<?> saveBookingDetails(@RequestBody BookedTicketDetails bookedTicketDetails, int trainNumber);

	BookedTicketDetails getTicketByPnr(@PathVariable Long pnr);

	List<BookedTicketDetails> findByUsername(String username);

	String deleteTicketByPnr(@PathVariable Long pnr);
}
