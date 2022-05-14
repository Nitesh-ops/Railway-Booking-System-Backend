package com.details.train.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Train_Details")
public class Train {
	@Id
	private int trainNumber;

	@NotEmpty
	@Size(max = 50, message = "It should be less than 50 characters")
	private String trainName;

	@NotEmpty
	@Size(max = 50, message = "It should be less than 50 characters")
	@ApiModelProperty(notes = "from Station is required",name="cls",required=true,value="from station")
	private String fromStation;

	@NotEmpty
	@Size(max = 50, message = "It should be less than 50 characters")
	private String toStation;

	private String departureDate;

	private String departureTime;

	private String arrivalDate;

	private String arrivalTime;

	private TravelDetails travelDetails;

}
