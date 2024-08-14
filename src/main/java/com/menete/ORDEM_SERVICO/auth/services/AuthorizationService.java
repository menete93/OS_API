package com.menete.ORDEM_SERVICO.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.menete.ORDEM_SERVICO.auth.repository.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService {

	
	@Autowired
	UserRepository repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		var n = repository.findByLogin(username);
		
		return n;
	}

}
