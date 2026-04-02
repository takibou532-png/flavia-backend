package com.menu.demo.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.menu.demo.Dtos.ReservationRequestDto;
import com.menu.demo.Dtos.ReservationResponseDto;
import com.menu.demo.Exceptions.ResourceNotFoundException;
import com.menu.demo.Mappers.ReservationMapper;

import com.menu.demo.Models.Reservation;
import com.menu.demo.Repositories.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {
	private final ReservationRepository reservationRepository;
	private final ReservationMapper reservationMapper;

	public List<ReservationResponseDto> getAllReservations(){
		return reservationRepository.findAll().stream().map(reservationMapper::toDto).collect(Collectors.toList());
		
	}
	public ReservationResponseDto getReservationById(Long id){
		return reservationMapper.toDto(reservationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("reservation not found with id "+id)));
		
	}
	public ResponseEntity<ReservationResponseDto> createreservation(ReservationRequestDto dto){

		
		 Reservation reservation =Reservation.builder()
			.email(dto.getEmail())
			.name(dto.getName())
			.date(dto.getDate())
			.time(dto.getTime())
			.partySize(dto.getPartySize())
			.specialRequests(dto.getSpecialRequests())
			.phoneNumber(dto.getPhoneNumber())
			.build();
		 reservationRepository.save(reservation);
		 return ResponseEntity.status(HttpStatus.CREATED).body(reservationMapper.toDto(reservation));
		
		
	}
	

	public ResponseEntity<Void> deletereservation(Long id){
		Reservation reservation =reservationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("reservation not found with id :"+ id));
		reservationRepository.delete(reservation);
		return ResponseEntity.ok(null);
	}
}
