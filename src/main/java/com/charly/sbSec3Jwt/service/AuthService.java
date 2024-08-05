package com.charly.sbSec3Jwt.service;

import com.charly.sbSec3Jwt.models.AuthResponse;
import com.charly.sbSec3Jwt.models.AuthenticationRequest;
import com.charly.sbSec3Jwt.models.RegisterRequest;

public interface AuthService {

	AuthResponse register ( RegisterRequest request);
	
	AuthResponse authenticate ( AuthenticationRequest request);
	
}
