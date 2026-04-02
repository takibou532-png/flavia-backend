package com.menu.demo.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.menu.demo.Dtos.EmployeeRequestDto;
import com.menu.demo.Dtos.EmployeeResponseDto;
import com.menu.demo.Services.EmployeeService;


import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/employees")
public class EmployeeController {
	private final EmployeeService employeeService;
	
	
	@GetMapping
	public List<EmployeeResponseDto> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	@PostMapping 
	public ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody EmployeeRequestDto req){
		return employeeService.createemployee(req);
	}
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable Long id,@RequestBody EmployeeRequestDto req){
		return employeeService.updateemployee(id, req);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>  deleteEmployee(@PathVariable Long id){
		return employeeService.deleteemployee(id);
	}
	

}
