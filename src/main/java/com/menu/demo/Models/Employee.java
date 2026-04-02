package com.menu.demo.Models;

import java.math.BigDecimal;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	 @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fullName;
	@JsonFormat(pattern = " HH:mm:ss")
	private LocalTime startWorkAt;
	@JsonFormat(pattern = " HH:mm:ss")
	private LocalTime endWorkAt;
	private BigDecimal dailySalery;
	private String faceImgUrl;

}
