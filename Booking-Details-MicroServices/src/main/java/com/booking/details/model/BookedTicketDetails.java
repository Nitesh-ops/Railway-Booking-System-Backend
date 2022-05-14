package com.booking.details.model;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookedTicketDetails {
	@Transient
	public static final String SEQUENCE_NAME = "pnr_sequence";
	@Id
	private long pnr;

	@NotEmpty(message = "username may not be empty")
	private String username;
	private RequiredTrain requiredTrain;
	private BookingDetail bookingDetail;
}
