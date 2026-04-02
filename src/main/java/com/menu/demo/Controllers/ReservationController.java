package com.menu.demo.Controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.menu.demo.Dtos.ReservationRequestDto;
import com.menu.demo.Dtos.ReservationResponseDto;
import com.menu.demo.Services.ReservationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {
	private final ReservationService reservationService;
	
	@PostMapping
	public ResponseEntity<ReservationResponseDto> createOrder(@RequestBody  ReservationRequestDto request){
		return reservationService.createreservation(request);
	}
	

}
