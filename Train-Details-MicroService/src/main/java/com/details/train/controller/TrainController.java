package com.details.train.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.details.train.model.Train;
import com.details.train.repository.TrainRepository;
import com.details.train.services.TrainService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/rail")
public class TrainController {

	Logger logger = LoggerFactory.getLogger(TrainController.class);

	@Autowired
	TrainService trainService;

	@Autowired
	TrainRepository trainRepository;

//	Add Train into Database
	@PostMapping
	@ApiOperation(value = "Add Train Details to the database ", response = String.class, tags = "postTrain")
	public ResponseEntity<String> postTrain(@Valid @RequestBody Train train) {
		trainService.saveTrain(train);
		logger.trace("Train Added Succesfully");
		return new ResponseEntity<String>("Train Added Succesfully", HttpStatus.CREATED);
	}

	// Showing all trains
	@GetMapping
	public ResponseEntity<?> getAllTrains() {
		List<Train> list = trainService.findAllTrains();

		if (list.size() <= 0) {
			return new ResponseEntity<String>("No Train Found", HttpStatus.NOT_FOUND);
		}
		logger.trace("Showing all trains");
		return new ResponseEntity<List<Train>>(list, HttpStatus.OK);
	}

	// get train between station by date

	@GetMapping("/{fromStation}/{toStation}/{departureDate}")
	public ResponseEntity<?> getTrainBetweenStations(@Valid @PathVariable String fromStation,
			@PathVariable String toStation, @PathVariable String departureDate) {
		logger.trace("Showing Trains Between stations");
		return new ResponseEntity<>(
				trainService.findByFromStationAndToStationAndDepartureDate(fromStation, toStation, departureDate),
				HttpStatus.OK);

	}

	// Show train by Number
	@GetMapping("/{trainNumber}")
	public ResponseEntity<Train> getTrainByNo(@PathVariable int trainNumber) {
		logger.trace("Showing Train By Train Number");
		return new ResponseEntity<Train>(trainService.findbyTrainNo(trainNumber), HttpStatus.OK);
	}

	// Delete Train by number
	@DeleteMapping("{trainNumber}")
	public ResponseEntity<String> deleteTrainByNo(@PathVariable int trainNumber) {
		trainService.deletebyTrainNo(trainNumber);
		logger.trace("Deleted Train by Number");
		return new ResponseEntity<String>("Train Deleted Succesfully", HttpStatus.OK);
	}

	// update train by number
	@PutMapping("{trainNumber}")
	public void updateTrainbyNo(@PathVariable int trainNumber, @RequestBody Train train) {
		logger.trace("Inside Update Train  details by Number");
		trainService.updateTrainbyNo(trainNumber, train);

	}

}
