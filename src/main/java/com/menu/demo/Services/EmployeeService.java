package com.menu.demo.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.menu.demo.Dtos.EmployeeRequestDto;
import com.menu.demo.Dtos.EmployeeResponseDto;


import com.menu.demo.Exceptions.ResourceNotFoundException;
import com.menu.demo.Mappers.EmployeeMapper;

import com.menu.demo.Models.Employee;

import com.menu.demo.Repositories.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	private final EmployeeRepository employeeRepository;
	private final EmployeeMapper employeeMapper;
	

	
	
	public List<EmployeeResponseDto> getAllEmployees(){
		return employeeRepository.findAll().stream().map(employeeMapper::toDto).collect(Collectors.toList());
		
	}
	public EmployeeResponseDto getEmployeeById(Long id){
		return employeeMapper.toDto(employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("employee not found with id "+id)));
		
	}
	public ResponseEntity<EmployeeResponseDto> createemployee(EmployeeRequestDto dto){

		
		 Employee employee =Employee.builder()
				 .dailySalery(dto.getDailySalery())
				 .endWorkAt(dto.getEndWorkAt())
				 .startWorkAt(dto.getStartWorkAt())
				 .fullName(dto.getFullName())
				 .faceImgUrl(dto.getFaceImgUrl())
				 .build();
		 employeeRepository.save(employee);
		 return ResponseEntity.status(HttpStatus.CREATED).body(employeeMapper.toDto(employee));
		
		
	}
	
	public ResponseEntity<EmployeeResponseDto> updateemployee(Long id,EmployeeRequestDto dto){
		Employee employee =employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("employee not found with id :"+ id));
	     employee.setDailySalery(dto.getDailySalery());
	     employee.setEndWorkAt(dto.getEndWorkAt());
	     employee.setFullName(dto.getFullName());
	     employee.setStartWorkAt(dto.getStartWorkAt());
	     employee.setFaceImgUrl(dto.getFaceImgUrl());
	   
		employeeRepository.save(employee);
		return ResponseEntity.ok(employeeMapper.toDto(employee));
		
	}
	public ResponseEntity<Void> deleteemployee(Long id){
		Employee employee =employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("employee not found with id :"+ id));
		employeeRepository.delete(employee);
		return ResponseEntity.ok(null);
	}
	
	

}
