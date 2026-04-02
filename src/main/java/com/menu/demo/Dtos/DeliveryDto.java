package com.menu.demo.Dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryDto {
	private Long id;
	private String fullname;
	private String email;

}
