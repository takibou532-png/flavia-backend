package com.menu.demo.Mappers;

import org.mapstruct.Mapper;

import com.menu.demo.Dtos.ReservationResponseDto;
import com.menu.demo.Models.Reservation;

@Mapper(componentModel="spring")
public interface ReservationMapper {
	public ReservationResponseDto toDto(Reservation res);

}
