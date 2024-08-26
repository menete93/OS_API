package com.menete.ORDEM_SERVICO.domain.repository.domain.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.menete.ORDEM_SERVICO.domain.Services;


@Repository
public interface ServicesRepository extends JpaRepository<Services, UUID>{

}
