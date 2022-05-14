package com.booking.details.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.booking.details.exception.TicketNotFoundException;
import com.booking.details.model.BookedTicketDetails;
import com.booking.details.model.RequiredTrain;
import com.booking.details.model.train.CoachesClass;
import com.booking.details.model.train.Train;
import com.booking.details.reopsitory.BookingDetailsRepository;

@Service
public class BookingDetailsServiceImpl implements BookingDetailsService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private SequenceGeneratorService service;

	@Autowired
	private BookingDetailsRepository bookingDetailsRepository;

	@Override
	public ResponseEntity<?> saveBookingDetails(BookedTicketDetails bookedTicketDetails, int trainNumber) {
		String username = "randomSecureKeyUsername!";
		String password = "randomSecureKeyPassword!";
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(username, password);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<Train> response = restTemplate.exchange("http://train-detail-service/api/rail/" + trainNumber,
				HttpMethod.GET, request, Train.class);

		Train train = response.getBody();

		if (bookedTicketDetails.getBookingDetail().getTravellerDetails().size() < 6) {
			for (CoachesClass result : train.getTravelDetails().getCoachesClass()) {
				if (result.getTravelClass().equalsIgnoreCase(bookedTicketDetails.getBookingDetail().getTravelClass())) {
					int remainingSeat = result.getAvailableTickets()
							- bookedTicketDetails.getBookingDetail().getTravellerDetails().size();
					result.setAvailableTickets(remainingSeat);
				}
			}
		} else {
			return new ResponseEntity<String>("Passenger should be less than six", HttpStatus.BAD_REQUEST);
		}

		RequiredTrain requiredTrain = new RequiredTrain(train.getTrainNumber(), train.getTrainName(),
				train.getFromStation(), train.getToStation(), train.getDepartureDate(), train.getDepartureTime(),
				train.getArrivalDate(), train.getArrivalTime());

		BookedTicketDetails bookedTicketDetails2 = new BookedTicketDetails(
				service.generateSequence(BookedTicketDetails.SEQUENCE_NAME), bookedTicketDetails.getUsername(),
				requiredTrain, bookedTicketDetails.getBookingDetail());

		HttpHeaders headers1 = new HttpHeaders();
		headers1.setBasicAuth(username, password);
		headers1.setContentType(MediaType.APPLICATION_JSON);
		headers1.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Train> entity = new HttpEntity<Train>(train, headers1);

		System.out.println(train);

		restTemplate.exchange("http://train-detail-service/api/rail/" + trainNumber, HttpMethod.PUT, entity, Void.class)
				.getBody();

//		restTemplate.postForObject("http://Email-Send-service/send/email", bookedTicketDetails2,
//				BookedTicketDetails.class);

		return new ResponseEntity<BookedTicketDetails>(bookingDetailsRepository.save(bookedTicketDetails2),
				HttpStatus.OK);

	}

	@Override
	public BookedTicketDetails getTicketByPnr(Long pnr) {
		return bookingDetailsRepository.findById(pnr)
				.orElseThrow(() -> new TicketNotFoundException("Ticket", "PNR Number", pnr));
	}

	@Override
	public String deleteTicketByPnr(Long pnr) {

		bookingDetailsRepository.findById(pnr)
				.orElseThrow(() -> new TicketNotFoundException("Ticket", "PNR Number", pnr));
		bookingDetailsRepository.deleteById(pnr);
		return "Ticket Deleted Successfully";
	}

	@Override
	public List<BookedTicketDetails> findByUsername(String username) {
		return bookingDetailsRepository.findByUsername(username);
	}

}
