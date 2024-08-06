package com.menete.ORDEM_SERVICO.domain.service;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.menete.ORDEM_SERVICO.domain.Cliente;
import com.menete.ORDEM_SERVICO.domain.dto.ClienteDto;
import com.menete.ORDEM_SERVICO.domain.exceptions.objectNotFoundException;
import com.menete.ORDEM_SERVICO.domain.repository.ClienteRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;		


@Service
@AllArgsConstructor
public class ClienteService {
	




		@Autowired
		private ClienteRepository clienteRepository;

		public Cliente findById(Integer id) {

			Optional<Cliente> obj = clienteRepository.findById(id);

			return obj.orElseThrow(() -> new objectNotFoundException(
					"Cliente Nao Encontrado ! id: " + id + " ,Tipo:  " + Cliente.class.getName()));
		}

		public List<Cliente> findAll() {
			// TODO Auto-generated method stub
			return clienteRepository.findAll();
		}

		public Cliente create(Cliente objDto) {
			if (findByCpf(objDto) != null) {

				throw new DataIntegratyViolationException("CPF ja cadastrado na Base de dados");

			}
			Cliente newObj = new Cliente(null, objDto.getNome(), objDto.getCpf(), objDto.getTelefone());

			return clienteRepository.save(newObj);

		}

		// Metod que verifica se este CPF ja existe na base de dados
		public Cliente update(Integer id, @Valid Cliente objDto) {

			Cliente oldObj = findById(id);

			if (findByCpf(objDto) != null && findByCpf(objDto).getId() != id) {

				throw new DataIntegratyViolationException("CPF ja cadastrado na Base de dados");

			}

			oldObj.setCpf(objDto.getCpf());
			oldObj.setNome(objDto.getNome());
			oldObj.setTelefone(objDto.getTelefone());

			return clienteRepository.save(oldObj);
		}

		private Cliente findByCpf(Cliente objDto) {

			Cliente obj = clienteRepository.findByCpf(objDto.getCpf());

			if (obj != null) {

				return obj;
			}
			return obj;

		}

		public ResponseEntity<ClienteDto> delete(Integer id) {


			Optional<Cliente> Cliente = clienteRepository.findById(id);

			if (Cliente != null) {

				clienteRepository.deleteById(id);

				throw new DataIntegratyViolationException("Cliente Removido com sucesso !");

			}
			throw new DataIntegratyViolationException("Objecto nao encontrado !");

		}
	}
