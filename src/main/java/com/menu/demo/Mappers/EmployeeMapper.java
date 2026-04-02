package com.menu.demo.Mappers;

import org.mapstruct.Mapper;

import com.menu.demo.Dtos.EmployeeResponseDto;
import com.menu.demo.Models.Employee;

@Mapper(componentModel="spring")
public interface EmployeeMapper {
public EmployeeResponseDto toDto(Employee empl);
}
