package com.menete.ORDEM_SERVICO.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import com.menete.ORDEM_SERVICO.auth.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	UserDetails findByLogin(String login);
}
