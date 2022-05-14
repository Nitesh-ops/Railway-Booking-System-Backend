package com.booking.details.reopsitory;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.booking.details.model.BookedTicketDetails;

public interface BookingDetailsRepository extends MongoRepository<BookedTicketDetails, Long> {

	List<BookedTicketDetails> findByUsername(String username);

}
