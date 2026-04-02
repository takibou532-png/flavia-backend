package com.menu.demo.Dtos;

import java.time.LocalDate;
import java.time.LocalTime;


import lombok.Data;

@Data
public class ReservationResponseDto {
	private Long id;
    private String name;
    private String partySize;
    private String phoneNumber;

    private String email;
    private LocalDate date;
    private LocalTime time;

    private String specialRequests;
}
