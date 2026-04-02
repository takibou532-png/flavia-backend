package com.menu.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.menu.demo.Models.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository < Reservation ,Long> {

}
