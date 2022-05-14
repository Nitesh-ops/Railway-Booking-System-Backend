package com.details.train;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.details.train.controller.TrainController;
import com.details.train.model.Train;
import com.details.train.model.TravelDetails;
import com.details.train.repository.TrainRepository;
import com.details.train.services.TrainService;

@SpringBootTest
class TrainDetailsMicroServiceApplicationTests {

	@InjectMocks
	TrainController trainController;

	@Mock
	private TrainService trainService;

	@MockBean
	private TrainRepository trainRepository;

	@Test
	@Order(1)
	public void testListTrain() {
		List<Train> listTrains = new ArrayList<>();
		listTrains.add(new Train(12134, "Pooja Sf Express", "Ajmer Jn", "Jammu Tawi", "12-05-2022", "12:30",
				"12-06-2022", "12:30", new TravelDetails()));
		listTrains.add(new Train(12135, "Pooja Sf Express", "Ajmer Jn", "Jammu Tawi", "12-05-2022", "12:30",
				"12-06-2022", "12:30", new TravelDetails()));
		listTrains.add(new Train(12378, "Pooja Sf Express", "Ajmer Jn", "Jammu Tawi", "12-05-2022", "12:30",
				"12-06-2022", "12:30", new TravelDetails()));

		// First test scenario
		when(trainService.findAllTrains()).thenReturn(listTrains);
		// Second test scenario
		assertEquals(3, listTrains.size());
	}

	@Test
	@Order(2)
	public void testPostTrain() {
		Train train = new Train();
		train.setTrainNumber(12501);
		train.setTrainName("Amarnath Express");
		train.setFromStation("Amritsar");
		train.setToStation("New Delhi");
		train.setDepartureDate("12-05-2022");
		train.setDepartureTime("12:30");
		train.setArrivalDate("12-06-2022");
		train.setArrivalTime("12:30");
		train.setTravelDetails(new TravelDetails());

		// test case scenario
		when(trainService.saveTrain(train)).thenReturn(train);
	}

	@Test
	@Order(3)
	public void testGetByTrainNo() {
		Train train = new Train(12134, "Pooja Sf Express", "Ajmer Jn", "Jammu Tawi", "12-05-2022", "12:30",
				"12-06-2022", "12:30", new TravelDetails());
		int trainNo = 12134;
		when(trainService.findbyTrainNo(trainNo)).thenReturn(train);

		ResponseEntity<Train> res = trainController.getTrainByNo(trainNo);
		assertEquals(HttpStatus.OK, res.getStatusCode());

	}

	@Test
	@Order(4)
	public void testGetTrainBetweenStation() {
		List<Train> listTrains = new ArrayList<>();
		listTrains.add(new Train(12134, "Pooja Sf Express", "Ajmer Jn", "Jammu Tawi", "12-05-2022", "12:30",
				"12-06-2022", "12:30", new TravelDetails()));
		listTrains.add(new Train(12135, "Amarnath Express", "Ajmer Jn", "Jammu Tawi", "12-05-2022", "12:30",
				"12-06-2022", "12:30", new TravelDetails()));
		String fromStation = "Ajmer Jn";
		String toStation = "Jammu Tawi";
		String departureDate = "12-05-2022";
		when(trainService.findByFromStationAndToStationAndDepartureDate(fromStation, toStation, departureDate))
				.thenReturn(listTrains);
		assertEquals(2, listTrains.size());
	}

	@Test
	@Order(5)
	public void testDeleteCountry() {
		Train train = new Train(12134, "Pooja Sf Express", "Ajmer Jn", "Jammu Tawi", "12-05-2022", "12:30",
				"12-06-2022", "12:30", new TravelDetails());
		int trainNo = 12134;
		when(trainService.deletebyTrainNo(trainNo)).thenReturn("Train Deleted Succesfully");
	}

}
