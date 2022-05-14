package com.api.gateway.request;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "train-detail-service", path = "/api/rail", /* url = "${train.service.url}", */ configuration = FeignConfiguration.class)
public interface TrainServiceRequest {
	@PostMapping /// api/rail
	String saveTrain(@RequestBody Object requestBody);

	@DeleteMapping("{trainNumber}") // api/rail/{trainNumber}
	void deleteTrain(@PathVariable("trainNumber") int trainNumber);

	@GetMapping // api/trains
	List<Object> getAllTrains();

	@GetMapping("{fromStation}/{toStation}/{departureDate}")
	List<Object> getTrainBetween(@PathVariable String fromStation, @PathVariable String toStation,
			@PathVariable String departureDate);

	@GetMapping("{trainNumber}")
	Object getTrainByNumber(@PathVariable int trainNumber);
}
