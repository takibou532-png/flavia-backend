package com.menu.demo.Dtos;

import java.math.BigDecimal;
import java.util.List;


import lombok.Data;

@Data
public class OrderRequestDto {
	private String phoneNumber;
	private String address;
	private String fullName;
	private BigDecimal totalPrice;
	
	
	private  List<OrderItemRequest> items;

}
