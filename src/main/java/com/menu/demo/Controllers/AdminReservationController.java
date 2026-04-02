package com.menu.demo.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.menu.demo.Dtos.ReservationResponseDto;
import com.menu.demo.Services.ReservationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/reservations")
public class AdminReservationController {
	
	private final ReservationService reservationService;

	@GetMapping
	public List<ReservationResponseDto> getAllReservations(){
		return reservationService.getAllReservations();
		
	}
}
