package com.details.train.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.details.train.model.Train;

public interface TrainService {

	Train saveTrain(Train train);

	List<Train> findAllTrains();

	Train findbyTrainNo(int TrainNo);

	String deletebyTrainNo(int TrainNo);

	List<Train> findByFromStationAndToStationAndDepartureDate(String fromSource, String fromDestination,
			String departureDate);

	ResponseEntity<?> updateTrainbyNo(int TrainNo, Train trainDetails);

}
