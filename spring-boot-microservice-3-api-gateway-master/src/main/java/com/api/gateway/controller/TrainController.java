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

import com.api.gateway.request.TrainServiceRequest;

@RestController
@RequestMapping("gateway/rail")
public class TrainController {
	@Autowired
	private TrainServiceRequest trainServiceRequest;

	@PostMapping("/create") // gateway/rail
	public ResponseEntity<String> saveTrain(@RequestBody Object train) {
		return new ResponseEntity<String>(trainServiceRequest.saveTrain(train), HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{trainNumber}") // gateway/rail/{trainNumber}
	public ResponseEntity<String> deleteTrain(@PathVariable int trainNumber) {
		trainServiceRequest.deleteTrain(trainNumber);
		return new ResponseEntity<String>("Train Deleted Succesfully", HttpStatus.OK);
	}

	@GetMapping("{trainNumber}")
	public Object getTrain(@PathVariable int trainNumber) {
		return trainServiceRequest.getTrainByNumber(trainNumber);
	}

	@GetMapping // gateway/rail
	public ResponseEntity<List<Object>> getAllTrains() {
		return ResponseEntity.ok(trainServiceRequest.getAllTrains());
	}

	@GetMapping("{fromStation}/{toStation}/{departureDate}")
	public List<Object> getTrainBetween(@PathVariable String fromStation, @PathVariable String toStation,
			@PathVariable String departureDate) {
		return trainServiceRequest.getTrainBetween(fromStation, toStation, departureDate);
	}
}
