package com.example.brandproductcrud.configer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.brandproductcrud.filter.JwtAuthenticationFilter;

@Configuration
public class SecurityConfiger {
	
	@Value("${app.user.password}")
	private String userPassword;

	@Value("${app.admin.password}")
	private String adminPassword;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	 
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
		UserDetails user = User.builder().username("Adarsh").password(passwordEncoder.encode("userPassword")).roles("USER").build();
		
		UserDetails admin = User.builder().username("admin").password(passwordEncoder.encode("adminPassword")).roles("ADMIN").build();
		return new InMemoryUserDetailsManager(user,admin);
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception{
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth
			    .requestMatchers(
			            "/auth/login",
			            "/swagger-ui/**",
			            "/swagger-ui.html",
			            "/v3/api-docs/**"
			        ).permitAll()
				.requestMatchers(HttpMethod.GET,"/products/**")
				.hasAnyRole("USER","ADMIN")
				
				.requestMatchers(HttpMethod.POST, "/products/**")
				.hasRole("ADMIN")
				
				.requestMatchers(HttpMethod.PUT, "/products/**")
				.hasRole("ADMIN")
				
				.requestMatchers(HttpMethod.DELETE, "/products/**")
				.hasRole("ADMIN")
				
				.anyRequest().authenticated()
			)
		.sessionManagement(session ->
        session.sessionCreationPolicy(
            org.springframework.security.config.http.SessionCreationPolicy.STATELESS
        )
    );

return http.build();
}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
		return configuration.getAuthenticationManager();
	}
}

 