package com.booking.details;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.booking.details.controller.BookingDetailsController;
import com.booking.details.model.BookedTicketDetails;
import com.booking.details.model.BookingDetail;
import com.booking.details.model.RequiredTrain;
import com.booking.details.reopsitory.BookingDetailsRepository;
import com.booking.details.services.BookingDetailsService;

@SpringBootTest
class BookingDetailsMicroServicesApplicationTests {

	@InjectMocks
	BookingDetailsController bookingDetailsController;

	@Mock
	private BookingDetailsService bookingDetailsService;

	@MockBean
	private BookingDetailsRepository bookingDetailsRepository;

//	@Test
//	@Order(1)
//	public void testSaveBookTicket() {
//		BookedTicketDetails bookedTicketDetails = new BookedTicketDetails(1245789, "world", new RequiredTrain(),
//				new BookingDetail());
//		int trainNo = 12415;
//		ResponseEntity<>=bookingDetailsService.saveBookingDetails(bookedTicketDetails, 12145).getStatusCode();
//		
//	}
	
	@Test
	@Order(1)
	public void testDeleteCountry() {
		BookedTicketDetails bookedTicketDetails = new BookedTicketDetails(1245789L, "world", new RequiredTrain(),
				new BookingDetail());
		when(bookingDetailsService.deleteTicketByPnr(1245789L)).thenReturn("Ticket Deleted Successfully");
	}
	
	@Test
	@Order(2)
	public void testGetTicketByPnr() {
		BookedTicketDetails bookedTicketDetails = new BookedTicketDetails(1245789, "world", new RequiredTrain(),
				new BookingDetail());
		Long Pnr = 1245789L;
		when(bookingDetailsService.getTicketByPnr(Pnr)).thenReturn(bookedTicketDetails);
	}
}
