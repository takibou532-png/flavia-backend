package com.menu.demo.Dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderItemResponseDto {
	
	
	
	  private Long itemId;
	    private String name;
	    private BigDecimal price;
	    private int quantity;

}
