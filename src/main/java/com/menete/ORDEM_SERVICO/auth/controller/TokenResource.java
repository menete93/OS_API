package com.menete.ORDEM_SERVICO.auth.controller;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimAccessor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.menete.ORDEM_SERVICO.auth.dto.LoginRequest;
import com.menete.ORDEM_SERVICO.auth.dto.LoginResponse;
import com.menete.ORDEM_SERVICO.auth.entity.Role;
import com.menete.ORDEM_SERVICO.auth.repository.UserRepository;
@CrossOrigin(origins = "https://localhost:4200", allowCredentials = "true")
@RestController
public class TokenResource {

	
	private final JwtEncoder jwtEncoder;
	
	private final UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public TokenResource(JwtEncoder jwtEncoder, UserRepository userRepository 
			,BCryptPasswordEncoder bCryptPasswordEncoder) {
	
		this.jwtEncoder = jwtEncoder;
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse>login(@RequestBody LoginRequest loginRequest){
		
		var User =this.userRepository.findByUsername(loginRequest.username());
		
		if(User.isEmpty() || !User.get().isLoginCorrect(loginRequest, bCryptPasswordEncoder)){
			
			throw new BadCredentialsException("user or password is invalid !");
			
		}
		var now = Instant.now();
		
		var expiresIn = 600L;
		
		  var scopes = User.get().getRoles()
	                .stream()
	                .map(Role::getName)
	                .collect(Collectors.joining(" "));
		
		var claims = JwtClaimsSet.builder()
				.issuer("mybackend")
				.subject(User.get().getUserId().toString())
				.issuedAt(now)
				.expiresAt(now.plusSeconds(expiresIn))
				.claim("scope", scopes)
				.build();
		
		var JwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
				
				return ResponseEntity.ok(new LoginResponse(JwtValue,expiresIn));
	}
	
}
