package com.menete.ORDEM_SERVICO.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.menete.ORDEM_SERVICO.domain.entity.Custommer;

@Repository
public interface CustomerRepository extends JpaRepository<Custommer, Integer> {

	@Query("SELECT i FROM Custommer i  WHERE i.cpf =:cpf")
	Custommer findByCpf(@Param("cpf") String cpf);
}
