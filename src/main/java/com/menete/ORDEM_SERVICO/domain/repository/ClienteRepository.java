package com.menete.ORDEM_SERVICO.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.menete.ORDEM_SERVICO.domain.Cliente;
import com.menete.ORDEM_SERVICO.domain.Tecnico;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Query("SELECT obj FROM Cliente obj  WHERE obj.cpf =:cpf")
	Cliente findByCpf(@Param("cpf") String cpf);
}
