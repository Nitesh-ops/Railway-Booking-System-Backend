package com.booking.details.model.train;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoachesClass {

	@NotEmpty
	@Max(30)
	private int availableTickets;

	@NotEmpty
	@Size(max = 2, message = "It should be only 2 characters long")
	private String travelClass;

	@NotEmpty
	@Size(max = 5, message = "Fare should be more than 2 numeric characters")
	private int fare;

}
