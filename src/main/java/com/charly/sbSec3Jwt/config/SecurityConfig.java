package com.charly.sbSec3Jwt.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;


import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity 
@EnableMethodSecurity					
@RequiredArgsConstructor
public class SecurityConfig { 
							 
	@Autowired
	private final JwtFilter jwtFilter;
	
	@Autowired
	private final AuthenticationProvider authenticationProvider; 
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{ 

	
		 httpSecurity
         .csrf(csrf -> csrf.disable())
         .cors(cors -> cors.configurationSource(corsConfigurationSource()))
         .authorizeHttpRequests(authorize -> authorize
             .requestMatchers("/api/auth/register", "/api/auth/authenticate",
                              "/v3/api-docs/**", "/swagger-resources/**", 
                              "/swagger-ui/**", "/swagger-ui.html", 
                              "/webjars/**", "/v2/api-docs").permitAll()
             .requestMatchers("/api/admin/**").hasRole("ADMIN")
             .requestMatchers("/api/alumno/**").hasRole("ALUMNO")
             .requestMatchers("/api/docente/**").hasRole("DOCENTE")
             .requestMatchers("/api/preceptor/**").hasRole("PRECEPTOR")
             .requestMatchers("/api/user/**").hasRole("USER")
             .anyRequest().authenticated()
         )
         .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
         .authenticationProvider(authenticationProvider)
         .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
         .httpBasic(httpBasic -> httpBasic.disable());

     return httpSecurity.build();


	}
	 @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("*"));
	        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
	        configuration.setAllowCredentials(true);

	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }
	private RequestMatcher publicEndpoints() {
		return new OrRequestMatcher ( 
									  new AntPathRequestMatcher("/api/auth/register"), 
									  new AntPathRequestMatcher("/api/auth/authenticate"),  
									  new AntPathRequestMatcher("/swagger-ui/index.html#/") // 
									  );															  
	}				

	
}
