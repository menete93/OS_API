package com.menete.ORDEM_SERVICO.domain.repository.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.menete.ORDEM_SERVICO.domain.Os;

@Repository
public interface OSRepository extends JpaRepository<Os, Integer> {

}
