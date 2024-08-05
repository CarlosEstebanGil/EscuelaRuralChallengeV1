package com.charly.sbSec3Jwt.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.charly.sbSec3Jwt.config.JwtService;
import com.charly.sbSec3Jwt.entity.Role;
import com.charly.sbSec3Jwt.entity.User;
import com.charly.sbSec3Jwt.models.AuthResponse;
import com.charly.sbSec3Jwt.models.AuthenticationRequest;
import com.charly.sbSec3Jwt.models.RegisterRequest;
import com.charly.sbSec3Jwt.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	@Autowired
	private final UserRepository userRepository;
	
	@Autowired
	private final PasswordEncoder passwordEncoder;

	@Autowired
	private final JwtService jwtService;
	
	@Autowired
	private final AuthenticationManager authenticationManager; 
																
	@Override
	public AuthResponse register(RegisterRequest request) {

		User user = User.builder()
						.firstName(request.getFirstName())
						.lastName(request.getLastName())
						.email(request.getEmail())
						.password(passwordEncoder.encode(request.getPassword()))
						.role(Role.USER)
						.build();
		
		userRepository.save(user);
		
		var jwtToken = jwtService.generateToken(user);  
		
		return AuthResponse.builder()
							.token(jwtToken)
							.build();
	}

	@Override
	public AuthResponse authenticate(AuthenticationRequest request) { 
																		
																		
																		
																		
		authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getEmail(), 
															request.getPassword())
				);
		
		
		User user = userRepository.findByEmail(request.getEmail()).orElseThrow(); 
																					
																					
																					
																					
		
		
		
		var jwtToken = jwtService.generateToken(user);
		
		return AuthResponse.builder().token(jwtToken).build(); 
		

	}

}
