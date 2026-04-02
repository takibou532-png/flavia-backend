package com.menu.demo.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.menu.demo.Enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")

public class Order {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	  @CreationTimestamp
	  @JsonFormat(pattern = "dd-mm-yyyy  HH:mm:ss")
	private LocalDateTime createdAt;
	  @Column(length=10)
	private String phoneNumber;
	private String address;
	private BigDecimal totalPrice;
	private String fullName;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	
	   @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	  @Builder.Default
	    private List<OrderItem> orderItems = new ArrayList<>();
	
	
	
	

}
