package com.menete.ORDEM_SERVICO.auth;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.menete.ORDEM_SERVICO.auth.entity.User;
import com.menete.ORDEM_SERVICO.auth.repository.UserRepository;
import com.menete.ORDEM_SERVICO.domain.exceptions.objectNotFoundException;

@Service
public class UserService {
	
	
	@Autowired
	UserRepository userRepository;
	
	public User findById(UUID id ) {
		
		Optional<User> obj = userRepository.findById(id);
		
		return obj.orElseThrow(() -> new objectNotFoundException(
				"User not found ! id: " + id + " ,type:  " + User.class.getName()));

	}
	
}
