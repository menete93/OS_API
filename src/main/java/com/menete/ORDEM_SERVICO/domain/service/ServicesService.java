package com.menete.ORDEM_SERVICO.domain.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.menete.ORDEM_SERVICO.domain.dto.ServicesDto;
import com.menete.ORDEM_SERVICO.domain.entity.Services;
import com.menete.ORDEM_SERVICO.domain.exceptions.objectNotFoundException;
import com.menete.ORDEM_SERVICO.domain.repository.ServicesRepository;

import jakarta.validation.Valid;

public class ServicesService {

	
	
	

	@Autowired
	private static ServicesRepository servicesRepository;

	public Services findById(Integer id) {

		Optional<Services> obj = servicesRepository.findById(id);

		return obj.orElseThrow(() -> new objectNotFoundException(
				"Tecnician Not found ! id: " + id + " ,Type:  " + Services.class.getName()));
	}

	public static List<Services> findAll() {
		// TODO Auto-generated method stub
		return servicesRepository.findAll();
	}

	public Services create(ServicesDto objDto) {
		if (findById(objDto.getId()) != null) {

			throw new DataIntegratyViolationException("CPF already registered in Data Base");

		}
		Services newObj = new Services(null, objDto.getServiceName(), objDto.getProvince(), objDto.getLocationDescription());

		return servicesRepository.save(newObj);

	}

//	 Metod que verifica se este CPF ja existe na base de dados
	public Services update(Integer id, @Valid ServicesDto objDto) {

		Services oldObj = findById(id);

		if (findByServiceName(objDto) != null ) {

			throw new DataIntegratyViolationException("CPF already registered in Data Base");

		}

		oldObj.setLocationDescription(objDto.getLocationDescription());
		oldObj.setProvince(objDto.getProvince());
		oldObj.setServiceName(objDto.getServiceName());

		return servicesRepository.save(oldObj);
	}

	private Services findByServiceName(ServicesDto objDTO) {

		Services obj = servicesRepository.findByServiceName(objDTO.getServiceName());

		if (obj != null) {

			return obj;
		}
		return obj;

	}

       public ResponseEntity<ServicesDto> delete(Integer id) {


		Optional<Services> services = servicesRepository.findById(id);

		if (services != null) {

		servicesRepository.deleteById(id);

			throw new DataIntegratyViolationException("Tecnician Removed  sucessfully !");

		}
		throw new DataIntegratyViolationException("object no found !");

	}
	
	
}
