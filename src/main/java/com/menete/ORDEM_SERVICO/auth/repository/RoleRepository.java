package com.menete.ORDEM_SERVICO.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.menete.ORDEM_SERVICO.auth.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);

}

