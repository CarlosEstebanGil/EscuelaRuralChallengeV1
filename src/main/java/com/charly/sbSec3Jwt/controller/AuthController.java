package com.charly.sbSec3Jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.charly.sbSec3Jwt.models.AuthResponse;
import com.charly.sbSec3Jwt.models.AuthenticationRequest;
import com.charly.sbSec3Jwt.models.RegisterRequest;
import com.charly.sbSec3Jwt.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	@Autowired
	private AuthService authService;
	

	@PostMapping("/register")										
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) { 
		return ResponseEntity.ok(authService.register(request)); 
																
	}
	
	@PostMapping("/authenticate")								
	public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthenticationRequest request) { 
		return ResponseEntity.ok(authService.authenticate(request)); 
																
	}

	
}
