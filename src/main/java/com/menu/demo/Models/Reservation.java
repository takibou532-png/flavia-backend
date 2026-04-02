package com.menu.demo.Models;

import java.time.LocalDate;

import java.time.LocalTime;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String name;
    private String partySize;
    private String phoneNumber;
    @Email
    private String email;
    private LocalDate date;
    private LocalTime time;
    @Column(length=500)
    private String specialRequests;
	 


}
