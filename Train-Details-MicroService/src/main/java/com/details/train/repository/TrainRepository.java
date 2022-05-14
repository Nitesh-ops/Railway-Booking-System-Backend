package com.details.train.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.details.train.model.Train;

@Repository
public interface TrainRepository extends MongoRepository<Train, Integer> {

//	@Query(value = "{'fromStation' : ?0, 'toStation' : ?1}")
//	List<Train> findTrainsByFromStaionAndToStation(String fromStation, String toStation);

	List<Train> findByFromStationAndToStationAndDepartureDate(String fromSource, String fromDestination,
			String departureDate);
}
