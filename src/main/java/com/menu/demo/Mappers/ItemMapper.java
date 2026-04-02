package com.menu.demo.Mappers;

import org.mapstruct.Mapper;

import com.menu.demo.Dtos.ItemResponseDto;
import com.menu.demo.Models.Item;

@Mapper(componentModel="spring")
public interface ItemMapper {
	public ItemResponseDto toDto(Item item);
	

}
