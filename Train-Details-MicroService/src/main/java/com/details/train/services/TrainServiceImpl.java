package com.details.train.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.details.train.exceptions.TrainNotFoundException;
import com.details.train.model.Train;
import com.details.train.repository.TrainRepository;

@Service
public class TrainServiceImpl implements TrainService {

	@Autowired
	private TrainRepository trainRepository;

	@Override
	public Train saveTrain(Train train) {
		return trainRepository.save(train);
	}

	@Override
	public Train findbyTrainNo(int TrainNo) {
		return trainRepository.findById(TrainNo)
				.orElseThrow(() -> new TrainNotFoundException("Train", "Train Number", TrainNo));
	}

	@Override
	public String deletebyTrainNo(int TrainNo) {
		trainRepository.findById(TrainNo)
				.orElseThrow(() -> new TrainNotFoundException("Train", "Train Number", TrainNo));

		trainRepository.deleteById(TrainNo);
		return "Train Deleted Succesfully";
	}

	@Override
	public List<Train> findAllTrains() {
		return trainRepository.findAll();
	}

	@Override
	public List<Train> findByFromStationAndToStationAndDepartureDate(String fromSource, String fromDestination,
			String departureDate) {
		return trainRepository.findByFromStationAndToStationAndDepartureDate(fromSource, fromDestination,
				departureDate);
	}

	@Override
	public ResponseEntity<?> updateTrainbyNo(int TrainNo, Train trainDetails) {
		Train train = trainRepository.findById(TrainNo)
				.orElseThrow(() -> new TrainNotFoundException("Train", "Train Number", TrainNo));
		train.setTrainName(trainDetails.getTrainName());
		train.setFromStation(trainDetails.getFromStation());
		train.setToStation(trainDetails.getToStation());
		train.setDepartureDate(trainDetails.getDepartureDate());
		train.setDepartureTime(trainDetails.getDepartureTime());
		train.setArrivalTime(trainDetails.getArrivalTime());
		train.setArrivalDate(trainDetails.getArrivalDate());
		train.setTravelDetails(trainDetails.getTravelDetails());
		trainRepository.save(train);
		return new ResponseEntity<String>("Train Updated", HttpStatus.OK);

	}

}
