package com.menete.ORDEM_SERVICO.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.menete.ORDEM_SERVICO.domain.dto.OsDto;
import com.menete.ORDEM_SERVICO.domain.entity.Custommer;
import com.menete.ORDEM_SERVICO.domain.entity.Os;
import com.menete.ORDEM_SERVICO.domain.entity.Technician;
import com.menete.ORDEM_SERVICO.domain.enums.Priority;
import com.menete.ORDEM_SERVICO.domain.enums.Status;
import com.menete.ORDEM_SERVICO.domain.exceptions.objectNotFoundException;
import com.menete.ORDEM_SERVICO.domain.repository.OSRepository;

import jakarta.validation.Valid;

@Service
public class OsService {

	@Autowired
	private OSRepository osRepository;

	@Autowired
	TecnicianService service;

	@Autowired
	CustomerService clienteService;

	public Os findById(Integer id) {

		Optional<Os> obj = osRepository.findById(id);
		return obj.orElseThrow(() -> new objectNotFoundException(
				"Object not found! id  " + id + ", Type " + Os.class.getName()));

	}

	public List<Os> findAll() {
		// TODO Auto-generated method stub
		return osRepository.findAll();
	}

	public Os create(@Valid OsDto obj) {

		return fromDto(obj);
	}

	private Os fromDto(OsDto obj) {


		Os newOs = new Os();
		//newOs.setId(obj.getId());
		newOs.setObservations(obj.getObservations());
		newOs.setPrioridade(Priority.toEnum(obj.getPriority().getCod()));
		newOs.setStatus(Status.toEnum(obj.getStatus().getCod()));
//		`

		Technician tecnician = service.findById(obj.getTechnician());

		Custommer customer = clienteService.findById(obj.getCustomer());

		newOs.setTechnician(tecnician);
		newOs.setCustomer(customer);

		return osRepository.save(newOs);
	}

	public Os update(@Valid OsDto obj) {

		Os newOs = findById(obj.getId());
		//newOs.setId(obj.getId());
		newOs.setObservations(obj.getObservations());
		newOs.setPrioridade(Priority.toEnum(obj.getPriority().getCod()));
		newOs.setStatus(Status.toEnum(obj.getStatus().getCod()));
	

		Technician tecnician = service.findById(obj.getTechnician());

		Custommer customer = clienteService.findById(obj.getCustomer());

		newOs.setTechnician(tecnician);
		newOs.setCustomer(customer);
	if(obj.getStatus().getCod().equals(2)) {
			
		newOs.setCloseDate(LocalDateTime.now());
			
		}
		return osRepository.save(newOs);
	}
}