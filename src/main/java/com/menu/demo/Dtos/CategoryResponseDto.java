package com.menu.demo.Dtos;

import java.time.LocalDateTime;


import lombok.Data;

@Data
public class CategoryResponseDto {
	private Long id;
	private String name;


	private String description; 
	private String imgUrl;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
