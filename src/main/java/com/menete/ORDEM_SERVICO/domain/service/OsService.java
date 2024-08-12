package com.menete.ORDEM_SERVICO.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.menete.ORDEM_SERVICO.domain.Cliente;
import com.menete.ORDEM_SERVICO.domain.Os;
import com.menete.ORDEM_SERVICO.domain.Prioridade;
import com.menete.ORDEM_SERVICO.domain.Status;
import com.menete.ORDEM_SERVICO.domain.Tecnico;
import com.menete.ORDEM_SERVICO.domain.dto.OsDto;
import com.menete.ORDEM_SERVICO.domain.exceptions.objectNotFoundException;
import com.menete.ORDEM_SERVICO.domain.repository.domain.repository.OSRepository;

import jakarta.validation.Valid;

@Service
public class OsService {

	@Autowired
	private OSRepository osRepository;

	@Autowired
	TecnicoService service;

	@Autowired
	ClienteService clienteService;

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
		newOs.setObservacoes(obj.getObservacoes());
		newOs.setPrioridade(Prioridade.toEnum(obj.getPrioridade().getCod()));
		newOs.setStatus(Status.toEnum(obj.getStatus().getCod()));
//		`

		Tecnico tecnico = service.findById(obj.getTecnico());

		Cliente cliente = clienteService.findById(obj.getCliente());

		newOs.setTecnico(tecnico);
		newOs.setCliente(cliente);

		return osRepository.save(newOs);
	}

	public Os update(@Valid OsDto obj) {

		
		
		
		
		
//		Tecnico tecnico = service.findById(dto.getTecnico());
//		tecnico.setCpf(null);
//		Cliente cliente = clienteService.findById(dto.getCliente());
//		
//		
//		Os newOs = new Os();
		//return fromDto(dto);
		
		

		Os newOs = findById(obj.getId());
		//newOs.setId(obj.getId());
		newOs.setObservacoes(obj.getObservacoes());
		newOs.setPrioridade(Prioridade.toEnum(obj.getPrioridade().getCod()));
		newOs.setStatus(Status.toEnum(obj.getStatus().getCod()));
//		`

		Tecnico tecnico = service.findById(obj.getTecnico());

		Cliente cliente = clienteService.findById(obj.getCliente());

		newOs.setTecnico(tecnico);
		newOs.setCliente(cliente);

		return osRepository.save(newOs);
	}
}