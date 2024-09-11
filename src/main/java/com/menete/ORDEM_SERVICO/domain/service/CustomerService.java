package com.menete.ORDEM_SERVICO.domain.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.menete.ORDEM_SERVICO.domain.dto.CustomerDto;
import com.menete.ORDEM_SERVICO.domain.entity.Custommer;
import com.menete.ORDEM_SERVICO.domain.exceptions.objectNotFoundException;
import com.menete.ORDEM_SERVICO.domain.repository.CustomerRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;		


@Service
@AllArgsConstructor
public class CustomerService {
	




		@Autowired
		private CustomerRepository clienteRepository;

		public Custommer findById(Integer id) {

			Optional<Custommer> obj = clienteRepository.findById(id);

			return obj.orElseThrow(() -> new objectNotFoundException(
					"Custummer not found ! id: " + id + " ,type:  " + Custommer.class.getName()));
		}

		public List<Custommer> findAll() {
			// TODO Auto-generated method stub
			return clienteRepository.findAll();
		}

		public Custommer create(CustomerDto objDto) {
			if (findByCpf(objDto) != null) {

				throw new DataIntegratyViolationException("ID number already saved in the data base");

			}
			Custommer newObj = new Custommer(null, objDto.getName(), objDto.getCpf(), objDto.getCellPhone());

			return clienteRepository.save(newObj);

		}

		// Metod que verifica se este CPF ja existe na base de dados
		public Custommer update(Integer id, @Valid CustomerDto objDto) {

			Custommer oldObj = findById(id);

			if (findByCpf(objDto) != null && findByCpf(objDto).getId() != id) {

				throw new DataIntegratyViolationException("Fields must not be null");

			}

			oldObj.setCpf(objDto.getCpf());
			oldObj.setName(objDto.getName());
			oldObj.setCellPhone(objDto.getCellPhone());

			return clienteRepository.save(oldObj);
		}

		private Custommer findByCpf(CustomerDto objDto) {

			Custommer obj = clienteRepository.findByCpf(objDto.getCpf());

			if (obj != null) {

				return obj;
			}
			return obj;

		}

		public ResponseEntity<CustomerDto> delete(Integer id) {


			Optional<Custommer> Cliente = clienteRepository.findById(id);

			if (Cliente != null) {

				clienteRepository.deleteById(id);

				throw new DataIntegratyViolationException("Customer Removed sucessfully !");

			}
					throw new DataIntegratyViolationException("Objecto not found !");
		}
	}
