package com.booking.details.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequiredTrain {
	@Id
	private int trainNumber;

	@NotEmpty
	@Size(max = 50, message = "It should be less than 50 characters")
	private String trainName;

	@NotEmpty
	@Size(max = 50, message = "It should be less than 50 characters")
	private String fromStation;

	@NotEmpty
	@Size(max = 50, message = "It should be less than 50 characters")
	private String toStation;

	private String departureDate;

	private String departureTime;

	private String arrivalDate;

	private String arrivalTime;
}
