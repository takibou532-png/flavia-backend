package com.menu.demo.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.menu.demo.Enums.Role;
import com.menu.demo.Models.User;

@Repository
public interface AdminRepository extends JpaRepository<User,Long>{
	Optional<User> findByEmail(String email);
	
	
	List<User> findByRole(Role role);

}
