package com.menete.ORDEM_SERVICO.auth.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.menete.ORDEM_SERVICO.auth.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

	Optional<User> findByUsername(String username);
	
	Optional<User> findById(@NonNull UUID id);
}
