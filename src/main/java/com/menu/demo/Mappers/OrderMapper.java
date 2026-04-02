package com.menu.demo.Mappers;

import org.mapstruct.Mapper;

import com.menu.demo.Dtos.OrderResponseDto;
import com.menu.demo.Models.Order;

@Mapper(componentModel="spring")
public interface OrderMapper {
	public OrderResponseDto toDto(Order order);

}
