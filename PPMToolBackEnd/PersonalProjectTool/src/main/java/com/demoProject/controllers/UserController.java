package com.demoProject.controllers;

import static com.demoProject.security.SecurityConstants.TOKEN_PREFIX;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demoProject.entities.User;
import com.demoProject.exceptions.InvalidLoginResponse;
import com.demoProject.exceptions.RequestHasErrorsException;
import com.demoProject.exceptions.UserNotFoundException;
import com.demoProject.payload.JWTLoginSuccessResponse;
import com.demoProject.payload.LoginRequest;
import com.demoProject.security.JwtTokenProvider;
import com.demoProject.services.UserService;
import com.demoProject.validator.UserValidator;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService service;

	@Autowired
	UserValidator userValidator;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult res) {
		if (res.hasErrors()) {
			throw new RequestHasErrorsException(res.getFieldErrors());
		}

		try {
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(auth);
			String jwt = TOKEN_PREFIX + tokenProvider.generateToken(auth);

			return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));

		}

		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new InvalidLoginResponse(), HttpStatus.CONFLICT);
		}

	}

	@PostMapping("/register")
	public ResponseEntity<?> registeruser(@Valid @RequestBody User user, BindingResult result) {

		userValidator.validate(user, result);

		if (result.hasErrors()) {
			throw new RequestHasErrorsException(result.getFieldErrors());
		}

		User newUser = service.saveUser(user);

		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);

	}

}
