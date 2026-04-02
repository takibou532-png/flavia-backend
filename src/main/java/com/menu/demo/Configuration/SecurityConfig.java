package com.menu.demo.Configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.menu.demo.JWT.JwtFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final JwtFilter jwtFilter;
	
	@Bean
	public SecurityFilterChain secuirityFilterchain(HttpSecurity http) throws Exception {
		http.cors(Customizer.withDefaults())
		.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(auth ->
		auth.requestMatchers("/admin/login").permitAll()
		.requestMatchers(HttpMethod.GET,"/api/categories/**").permitAll()
		.requestMatchers(HttpMethod.GET,"/api/items/**").permitAll()
		.requestMatchers(HttpMethod.POST,"/api/reservations/").permitAll()
		.requestMatchers(HttpMethod.POST,"/api/orders/**").permitAll()
	
		.requestMatchers("/api/admin/orders/**").hasAnyRole("ADMIN","DELEVERY")
		.requestMatchers(HttpMethod.GET ,"/api/admin/reservations").hasRole("ADMIN")
		.requestMatchers("/api/admin/items/**").hasRole("ADMIN")
		.requestMatchers("/api/admin/categories/**").hasRole("ADMIN")
		.requestMatchers("/api/admin/employees/**").hasRole("ADMIN")
		.requestMatchers("/delivery/**").hasRole("ADMIN")
		.anyRequest().authenticated())
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.logout(AbstractHttpConfigurer::disable)
		.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
		
		@Bean
		public PasswordEncoder passwordEncoder() {
			
			return new BCryptPasswordEncoder();
		}
		@Bean
		public CorsFilter corsFilter() {
			return new CorsFilter(corsConfigurationSource());
	}
        
		private CorsConfigurationSource corsConfigurationSource() {
			CorsConfiguration config = new  CorsConfiguration();
			config.setAllowedOrigins(List.of("http://localhost:5173"));
			config.setAllowedMethods(List.of("POST","GET","PUT","DELETE","PATCH"));
			config.setAllowedHeaders(List.of("Authorization","Content-Type"));
			config.setAllowCredentials(true);
			UrlBasedCorsConfigurationSource source =new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", config);
			return source;
		}
		 @Bean
		    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		        return configuration.getAuthenticationManager();
		    }

}
