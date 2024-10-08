package com.menete.ORDEM_SERVICO.auth.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.menete.ORDEM_SERVICO.auth.entity.Role;
import com.menete.ORDEM_SERVICO.auth.entity.User;
import com.menete.ORDEM_SERVICO.auth.repository.RoleRepository;
import com.menete.ORDEM_SERVICO.auth.repository.UserRepository;
import jakarta.transaction.Transactional;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;

	private BCryptPasswordEncoder passwordEncoder;

	public AdminUserConfig(RoleRepository roleRepository, UserRepository userRepository,
			BCryptPasswordEncoder passwordEncoder) {
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public void run(String... args) {
		try {
			var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());
			if (roleAdmin == null) {
				System.out.println("Role 'ADMIN' not found!.");
				return;
			}

			var userAdmin = userRepository.findByUsername("admin");
			userAdmin.ifPresentOrElse(user -> System.out.println("User admin already exist!: " + user.getUsername()),
					() -> {
						var user = new User();
						user.setUsername("admin");
						user.setPassword(passwordEncoder.encode("123"));
						user.setRoles(Set.of(roleAdmin));
						userRepository.save(user);
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
