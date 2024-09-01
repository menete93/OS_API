package com.menete.ORDEM_SERVICO.auth.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.menete.ORDEM_SERVICO.auth.UserService;
import com.menete.ORDEM_SERVICO.auth.dto.CreateUserDto;
import com.menete.ORDEM_SERVICO.auth.dto.UpdateUser;
import com.menete.ORDEM_SERVICO.auth.entity.Role;
import com.menete.ORDEM_SERVICO.auth.entity.User;
import com.menete.ORDEM_SERVICO.auth.repository.RoleRepository;
import com.menete.ORDEM_SERVICO.auth.repository.UserRepository;
import com.menete.ORDEM_SERVICO.domain.service.DataIntegratyViolationException;

@RestController
public class UserResource {

	@Autowired
	UserService userService;

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	public UserResource(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	@PostMapping("/register")
	public ResponseEntity<Void> newUser(@RequestBody CreateUserDto dto) {

		var basicRole = roleRepository.findByName(Role.Values.BASIC.name());

		var userFromDb = userRepository.findByUsername(dto.username());
		if (userFromDb.isPresent()) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
		}

		var user = new User();
		user.setUsername(dto.username());
		user.setPassword(passwordEncoder.encode(dto.password()));
		user.setRoles(Set.of(basicRole));

		userRepository.save(user);

		return ResponseEntity.ok().build();
	}

	@GetMapping("/find-all-users")
	public ResponseEntity<List<User>> listUsers() {
		var users = userRepository.findAll();
		return ResponseEntity.ok(users);
	}

	@PutMapping("/update-password/{id}")
	public ResponseEntity<User> updatePassword(UpdateUser dto) {

		User user = userService.findById(dto.id());

		if (!passwordEncoder.matches(dto.oldPassword(), user.getPassword())) {

			throw new DataIntegratyViolationException("The password doesn't match");
		}

		user.setPassword(passwordEncoder.encode(dto.newPassword()));

		userRepository.save(user);

		return ResponseEntity.ok().build();

	}

}