package com.menete.ORDEM_SERVICO.domain.service;

import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.menete.ORDEM_SERVICO.domain.Tecnico;
import com.menete.ORDEM_SERVICO.domain.dto.TecnicoDto;
import com.menete.ORDEM_SERVICO.domain.exceptions.objectNotFoundException;
import com.menete.ORDEM_SERVICO.domain.repository.TecnicoRepository;

import jakarta.validation.Valid;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	public Tecnico findById(Integer id) {

		Optional<Tecnico> obj = tecnicoRepository.findById(id);

		return obj.orElseThrow(() -> new objectNotFoundException(
				"Tecnico Nao Encontrado ! id: " + id + " ,Tipo:  " + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {
		// TODO Auto-generated method stub
		return tecnicoRepository.findAll();
	}

	public Tecnico create(TecnicoDto objDto) {
		if (findByCpf(objDto) != null) {

			throw new DataIntegratyViolationException("CPF ja cadastrado na Base de dados");

		}
		Tecnico newObj = new Tecnico(null, objDto.getNome(), objDto.getCpf(), objDto.getTelefone());

		return tecnicoRepository.save(newObj);

	}

	// Metod que verifica se este CPF ja existe na base de dados
	public Tecnico update(Integer id, @Valid TecnicoDto objDto) {

		Tecnico oldObj = findById(id);

		if (findByCpf(objDto) != null && findByCpf(objDto).getId() != id) {

			throw new DataIntegratyViolationException("CPF ja cadastrado na Base de dados");

		}

		oldObj.setCpf(objDto.getCpf());
		oldObj.setNome(objDto.getNome());
		oldObj.setTelefone(objDto.getTelefone());

		return tecnicoRepository.save(oldObj);
	}

	private Tecnico findByCpf(TecnicoDto objDTO) {

		Tecnico obj = tecnicoRepository.findByCpf(objDTO.getCpf());

		if (obj != null) {

			return obj;
		}
		return obj;

	}

	public ResponseEntity<TecnicoDto> delete(Integer id) {


		Optional<Tecnico> tecnico = tecnicoRepository.findById(id);

		if (tecnico != null) {

			tecnicoRepository.deleteById(id);

			throw new DataIntegratyViolationException("Tecnico Removido com sucesso !");

		}
		throw new DataIntegratyViolationException("Objecto nao encontrado !");

	}
}
