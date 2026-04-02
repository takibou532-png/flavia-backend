package com.menu.demo.Controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.menu.demo.Dtos.OrderResponseDto;

import com.menu.demo.Services.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/orders")
public class AdminOrederController {
	private final OrderService orderService;
	
	
	@GetMapping
	public List<OrderResponseDto> getAllOrders(){
		return orderService.getAllOrders();
		
	}
	@PatchMapping("/delivered/{orderId}")
	public ResponseEntity<Void> setOrderDelivered(@PathVariable Long orderId){
	   return orderService.setOrderDelivered(orderId);
	}
	@PatchMapping("/cancel/{orderId}")
	public ResponseEntity<Void> CancelOrder(@PathVariable Long orderId){
	   return orderService.cancelOrder(orderId);
	}
	
	@GetMapping("/profit/daily")
	public BigDecimal dailyProfit() {
	    return orderService.getDailyProfit();
	}

	@GetMapping("/profit/weekly")
	public BigDecimal weeklyProfit() {
	    return orderService.getWeeklyProfit();
	}

	@GetMapping("/profit/monthly")
	public BigDecimal monthlyProfit() {
	    return orderService.getMonthlyProfit();
	}

	@GetMapping("/profit/all")
	public BigDecimal allProfit() {
	    return orderService.getAllTimeProfit();
	}
}
