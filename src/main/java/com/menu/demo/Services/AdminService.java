package com.menu.demo.Services;


import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;



import com.menu.demo.Models.User;
import com.menu.demo.Repositories.AdminRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService implements UserDetailsService {
	
	private final AdminRepository adminRepository;
	

	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User admin=adminRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Admin Not Foun with Email "+email));
		
		
		return  new org.springframework.security.core.userdetails.User(admin.getEmail(), admin.getPassword(),  List.of(new SimpleGrantedAuthority("ROLE_" + admin.getRole())));
		
	}

}
