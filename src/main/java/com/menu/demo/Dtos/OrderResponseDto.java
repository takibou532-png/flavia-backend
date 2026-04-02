package com.menu.demo.Dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.menu.demo.Enums.OrderStatus;


import lombok.Data;
@Data
public class OrderResponseDto {
	private Long id;
	
	private LocalDateTime createdAt;
	
	private String phoneNumber;
	private String address;
	private String fullName;
	private BigDecimal totalPrice;

	private OrderStatus orderStatus;
	private List<OrderItemResponseDto> items;
}
