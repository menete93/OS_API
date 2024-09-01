package com.menete.ORDEM_SERVICO.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.menete.ORDEM_SERVICO.domain.entity.Services;


@Repository
public interface ServicesRepository extends JpaRepository<Services, Integer>{

	
	@Query("SELECT i FROM Services i  WHERE i.serviceName =:serviceName")
	Services findByServiceName(@Param("serviceName") String serviceName);
}
