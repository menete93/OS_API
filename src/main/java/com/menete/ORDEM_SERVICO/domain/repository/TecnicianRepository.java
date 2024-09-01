package com.menete.ORDEM_SERVICO.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.menete.ORDEM_SERVICO.domain.entity.Technician;

@Repository
public interface TecnicianRepository extends JpaRepository<Technician, Integer> {

	@Query("SELECT i FROM Technician i  WHERE i.cpf =:cpf")
	Technician findByCpf(@Param("cpf") String cpf);

	
	
	
}
