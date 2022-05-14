package com.booking.details.model;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravellerDetails {
	@NotEmpty(message = "name may not be empty")
	private String name;
	@NotEmpty(message = "age may not be empty")
	private int age;
	@NotEmpty(message = "Gender may not be empty")
	private String gender;
}
