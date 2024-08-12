package com.menete.ORDEM_SERVICO.domain.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.menete.ORDEM_SERVICO.domain.Cliente;
import com.menete.ORDEM_SERVICO.domain.dto.ClienteDto;
import com.menete.ORDEM_SERVICO.domain.dto.TecnicoDto;
import com.menete.ORDEM_SERVICO.domain.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@Operation(description = "get custummers by id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "return Custummer"),
	@ApiResponse(responseCode = "400", description = "Custummer not found ! id"), })
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDto> finById(@PathVariable Integer id) {
		Cliente obj = clienteService.findById(id);
		ClienteDto objDto = new ClienteDto(obj);

		return ResponseEntity.ok().body(objDto);

	}

	@Operation(description = "get All Custumers")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "return all custumers"),
	@ApiResponse(responseCode = "200", description = "return all custumers"),
	@ApiResponse(responseCode = "400", description = "not found"),})
	@GetMapping
	public ResponseEntity<List<ClienteDto>> findAll() {

		// 1- Ou apenas executar este mapeamento com apenas uma linha
		List<ClienteDto> listDto = clienteService.findAll().stream().map(obj -> new ClienteDto(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);

		// 2- podemos escrever desta forma

//		List<Tecnico> list = clienteService.findAll();
//		List<TecnicoDto> listDto = new ArrayList<>();
//		
		// 3- ou enta com apenas uma linha
//		for (Tecnico obj : list) {
//			listDto.add(new TecnicoDto(obj));
//		}

//           list.forEach(obj -> listDto.add(new TecnicoDto(obj)));
	}

	@Operation(description = "Create Custumer")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "return all custumers"),
	@ApiResponse(responseCode = "200", description = "created"),
	@ApiResponse(responseCode = "400", description = "Ids must not be null"),})
	@PostMapping
	public ResponseEntity<Cliente> create(@Valid @RequestBody Cliente objDto) {
		Cliente obj = clienteService.create(objDto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();

	}

	@Operation(description = "update Custumer")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
	@ApiResponse(responseCode = "200", description = "updated"),
	@ApiResponse(responseCode = "400", description = "Fields must not be null"),})
	@PutMapping(value = "/{id}")
	public void upDate(@PathVariable Integer id, @Valid @RequestBody Cliente objDto) {

		ClienteDto newObj = new ClienteDto(clienteService.update(id, objDto));

		// return newObj;
	}
	@Operation(description = "delete Custumer")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
	@ApiResponse(responseCode = "200", description = "delete"),
	@ApiResponse(responseCode = "400", description = "ok"),})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {

		clienteService.delete(id);

		return ResponseEntity.noContent().build();

	}

}
