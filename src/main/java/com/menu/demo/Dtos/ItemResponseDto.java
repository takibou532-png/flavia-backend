package com.menu.demo.Dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import lombok.Data;
@Data
public class ItemResponseDto {
	private Long id;
	private String Name;
	private String categoryName;
	private BigDecimal price;
	private String description; 
	private String imgUrl;
	private boolean isAvailable;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
