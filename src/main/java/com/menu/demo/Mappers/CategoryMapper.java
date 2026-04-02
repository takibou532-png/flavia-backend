package com.menu.demo.Mappers;

import org.mapstruct.Mapper;

import com.menu.demo.Dtos.CategoryResponseDto;
import com.menu.demo.Models.Category;

@Mapper(componentModel="spring")
public interface CategoryMapper {
	public CategoryResponseDto toDto(Category category);
	

}
