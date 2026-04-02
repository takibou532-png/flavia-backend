package com.menu.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.menu.demo.Models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository< Employee , Long> {

}
