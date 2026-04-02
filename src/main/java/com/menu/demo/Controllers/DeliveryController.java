package com.menu.demo.Controllers;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.menu.demo.Dtos.DeliveryDto;
import com.menu.demo.Dtos.DeliveryRequestDto;
import com.menu.demo.Dtos.DeliveryUpdateDto;
import com.menu.demo.Enums.Role;
import com.menu.demo.Exceptions.ResourceNotFoundException;
import com.menu.demo.Models.User;
import com.menu.demo.Repositories.AdminRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/delivery")
public class DeliveryController {
	   private final AdminRepository adminRepository;
	   private final PasswordEncoder passwordEncoder;

	   @PostMapping("/createDelivery")
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<?> createDelivery(@Valid @RequestBody DeliveryRequestDto dto){

	        // Check if email exists
	        if (adminRepository.findByEmail(dto.getEmail()).isPresent()) {
	            return ResponseEntity
	                    .status(HttpStatus.CONFLICT)
	                    .body("Email already exists");
	        }

	        User user = User.builder()
	                .email(dto.getEmail())
	                .fullname(dto.getFullname())
	                .password(passwordEncoder.encode(dto.getPassword()))
	                .role(Role.DELEVERY)
	                .build();

	        adminRepository.save(user);

	        return ResponseEntity
	                .status(HttpStatus.CREATED)
	                .body("Delivery has been created successfully");
	    }
	   
	   @GetMapping("get-deliveries")
	   public List<DeliveryDto> getAllDeliveries(){
		   
		   return adminRepository.findByRole(Role.DELEVERY).stream().map(this::toDto).collect(Collectors.toList());
		   
		   
	   }
	   @PutMapping("/{id}")
	   public ResponseEntity<?> updateDelivery(@PathVariable Long id,@RequestBody DeliveryUpdateDto dto){
		   User delivery =adminRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Delivery Not Found With id"+ id));
		   
		   delivery.setEmail(dto.getEmail());
		   delivery.setFullname(dto.getFullname());
		
		   adminRepository.save(delivery);
		   
		   return ResponseEntity.ok("Delivery Updated Successfully ");
		   
	   }
	   
	   @DeleteMapping("/{id}")
	   public ResponseEntity<?> deleteDelivery(@PathVariable Long id){
		   User delivery =adminRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Delivery Not Found With id"+ id));
		   adminRepository.delete(delivery);
		   return ResponseEntity.ok("Delivery Deleted Successfully");
	   }
	   
	   
	   
	   
	   private DeliveryDto toDto(User user) {
		   
		   DeliveryDto dto=DeliveryDto.builder().id(user.getId()).email(user.getEmail())
				   .fullname(user.getFullname()).build();
		   return dto;
		   
	   }
}
