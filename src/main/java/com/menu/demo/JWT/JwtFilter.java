package com.menu.demo.JWT;

import java.io.IOException;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import com.menu.demo.Services.AdminService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {


private final jwtUtil jwtutil;
private final AdminService adminService;

@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

    String authorizationHeader = request.getHeader("Authorization");
    System.out.println("Authorization header: " + authorizationHeader);
    String jwt = null;
    String email = null;

    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
        jwt = authorizationHeader.substring(7);
    }
    if(jwt==null) {
		   Cookie[] cookies=request.getCookies();
		   if(cookies!=null) {
		   for(Cookie cookie:cookies) {
			   if("Jwt".equals(cookie.getName())) {
				   jwt=cookie.getValue();
				   System.out.println(jwt);
				   break;
			   }
		   }
	   }
    }

    if (jwt != null) {
        email = jwtutil.extractEmail(jwt);
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = adminService.loadUserByUsername(email);
            if (jwtutil.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("Authentication set: " + SecurityContextHolder.getContext().getAuthentication());

            }
        }
    }

    filterChain.doFilter(request, response);
}


}

