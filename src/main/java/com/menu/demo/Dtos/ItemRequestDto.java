package com.menu.demo.Dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ItemRequestDto {
	private String categoryName;
	private String name;
	private String description;
	private BigDecimal price;
	private String imgUrl;
	private boolean isAvailable;

}
 