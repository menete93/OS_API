package com.menete.ORDEM_SERVICO.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.menete.ORDEM_SERVICO.domain.entity.Services;

public interface ServiceRepository extends JpaRepository<Services, UUID> {
	
	

}
