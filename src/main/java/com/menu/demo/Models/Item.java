package com.menu.demo.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String Name;
	private String description;
	private BigDecimal price;
	private String imgUrl;
	private boolean isAvailable;
	@Builder.Default
	private Integer numberOfSales=0;
	
	 @CreationTimestamp
		private LocalDateTime createdAt;
		@UpdateTimestamp
		private LocalDateTime updatedAt;

	  @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "category_id")
	 @JsonIgnore
	    private Category category;
}
