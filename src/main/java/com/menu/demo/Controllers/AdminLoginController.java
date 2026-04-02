package com.menu.demo.Controllers;




import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.menu.demo.Dtos.AdminRequestDto;
import com.menu.demo.Dtos.ChangePasswordReq;
import com.menu.demo.Dtos.DeliveryRequestDto;
import com.menu.demo.Enums.Role;
import com.menu.demo.Exceptions.ResourceNotFoundException;
import com.menu.demo.JWT.jwtUtil;
import com.menu.demo.Models.User;
import com.menu.demo.Repositories.AdminRepository;
import com.menu.demo.Services.AdminService;


import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminLoginController {

    private final AuthenticationManager authenticationManager;
    private final AdminService adminService;
    private final AdminRepository adminRepository;
    private final jwtUtil jwtutil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminRequestDto loginRequest,HttpServletResponse response) {
      
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
            authenticationManager.authenticate(authInputToken);
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(403).body("Login failed: " + e.getMessage());
        }

       
        UserDetails userDetails = adminService.loadUserByUsername(loginRequest.getEmail());


        String token = jwtutil.generateToken(userDetails);

   
        String cookie = String.format(
                "Jwt=%s; HttpOnly; Path=/; Max-Age=%d; SameSite=None; Secure",
                token, 7 * 24 * 60 * 60
            );
        
            response.addHeader("Set-Cookie", cookie);

        return ResponseEntity.ok("Login successful");
    }
    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordReq request,
            Authentication auth) {

        String email = auth.getName();
      User admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));

     
        if (!passwordEncoder.matches(request.getOldPassword(), admin.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Old password is incorrect");
        }

    
        String encodedNew = passwordEncoder.encode(request.getNewPassword());

   
        admin.setPassword(encodedNew);
        adminRepository.save(admin);

        return ResponseEntity.ok("Password updated successfully");
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {

        response.addHeader(
            "Set-Cookie",
            "Jwt=; HttpOnly; Path=/; Max-Age=0; SameSite=None; Secure"
        );

        return ResponseEntity.ok("Logged out");
    }




    @GetMapping("/me")
    public ResponseEntity<?> getAdminInfo(Authentication auth){
    	String email =auth.getName();
    	User admin=adminRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("Admin Not Found With email"+email));
    	return ResponseEntity.ok(admin);
    }
  
   
    

}
