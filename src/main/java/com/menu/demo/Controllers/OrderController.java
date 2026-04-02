package com.menu.demo.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.menu.demo.Dtos.OrderRequestDto;
import com.menu.demo.Dtos.OrderResponseDto;
import com.menu.demo.Services.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
	
	private final OrderService orderService;
	
	@PostMapping
	public ResponseEntity<OrderResponseDto> createOrder(@RequestBody  OrderRequestDto request){
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(request));
	}

}
