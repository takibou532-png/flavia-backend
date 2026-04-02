package com.menu.demo.Dtos;

import java.math.BigDecimal;
import java.time.LocalTime;
import lombok.Data;
@Data
public class EmployeeResponseDto {
	private Long id;
	private String fullName;
	private String faceImgUrl;
	private LocalTime startWorkAt;
	private LocalTime endWorkAt;
	private BigDecimal dailySalery;
}
