package com.booking.details.model.train;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelDetails {

	private List<CoachesClass> coachesClass;
}
