package com.menete.ORDEM_SERVICO.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.menete.ORDEM_SERVICO.domain.dto.TechnicianDto;
import com.menete.ORDEM_SERVICO.domain.entity.Technician;
import com.menete.ORDEM_SERVICO.domain.exceptions.objectNotFoundException;
import com.menete.ORDEM_SERVICO.domain.repository.TecnicianRepository;

import jakarta.validation.Valid;

@Service
public class TecnicianService {

	@Autowired
	private TecnicianRepository tecnicoRepository;

	public Technician findById(Integer id) {

		Optional<Technician> obj = tecnicoRepository.findById(id);

		return obj.orElseThrow(() -> new objectNotFoundException(
				"Tecnician Not found ! id: " + id + " ,Type:  " + Technician.class.getName()));
	}

	public List<Technician> findAll() {
		// TODO Auto-generated method stub
		return tecnicoRepository.findAll();
	}

	public Technician create(TechnicianDto objDto) {
		if (findByCpf(objDto) != null) {

			throw new DataIntegratyViolationException("CPF already registered in Data Base");

		}
		Technician newObj = new Technician(null, objDto.getName(), objDto.getCpf(), objDto.getCellPhone());

		return tecnicoRepository.save(newObj);

	}

	// Metod que verifica se este CPF ja existe na base de dados
	public Technician update(Integer id, @Valid TechnicianDto objDto) {

		Technician oldObj = findById(id);

		if (findByCpf(objDto) != null && findByCpf(objDto).getId() != id) {

			throw new DataIntegratyViolationException("CPF already registered in Data Base");

		}

		oldObj.setCpf(objDto.getCpf());
		oldObj.setName(objDto.getName());
		oldObj.setCellPhone(objDto.getCellPhone());

		return tecnicoRepository.save(oldObj);
	}

	private Technician findByCpf(TechnicianDto objDTO) {

		Technician obj = tecnicoRepository.findByCpf(objDTO.getCpf());

		if (obj != null) {

			return obj;
		}
		return obj;

	}

	public ResponseEntity<TechnicianDto> delete(Integer id) {


		Optional<Technician> tecnico = tecnicoRepository.findById(id);

		if (tecnico != null) {

			tecnicoRepository.deleteById(id);

			throw new DataIntegratyViolationException("Tecnician Removed  sucessfully !");

		}
		throw new DataIntegratyViolationException("object no found !");

	}
}
