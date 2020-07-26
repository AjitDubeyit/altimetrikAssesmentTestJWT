package com.test.altimetrik.springsecurityjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.altimetrik.springsecurityjwt.constant.APIConstant;
import com.test.altimetrik.springsecurityjwt.models.AuthenticationFailureResponse;
import com.test.altimetrik.springsecurityjwt.models.AuthenticationRequest;
import com.test.altimetrik.springsecurityjwt.models.AuthenticationResponse;
import com.test.altimetrik.springsecurityjwt.service.MyUserDetailsService;
import com.test.altimetrik.springsecurityjwt.util.JwtUtil;

@RestController
public class JWTAuthController {


	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@RequestMapping({ "/hello" })
	public ResponseEntity<AuthenticationFailureResponse> firstPage() {
		
		//boolean isValid = jwtTokenUtil.validateToken(jwt, userDetails);
		return ResponseEntity.ok(new AuthenticationFailureResponse(APIConstant.SUCCESS));
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
					);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		//boolean isValid = jwtTokenUtil.validateToken(jwt, userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));

	}



}
