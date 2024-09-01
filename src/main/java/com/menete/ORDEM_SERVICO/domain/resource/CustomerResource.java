
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

import com.menete.ORDEM_SERVICO.domain.dto.CustomerDto;
import com.menete.ORDEM_SERVICO.domain.entity.Custommer;
import com.menete.ORDEM_SERVICO.domain.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/customer")
public class CustomerResource {

	@Autowired
	CustomerService clienteService;

	@Operation(description = "get custummers by id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "return Custummer"),
	@ApiResponse(responseCode = "400", description = "Custummer not found ! id"), })
	@GetMapping(value = "/find-by/{id}")
	public ResponseEntity<CustomerDto> finById(@PathVariable Integer id) {
		Custommer obj = clienteService.findById(id);
		CustomerDto objDto = new CustomerDto(obj);

		return ResponseEntity.ok().body(objDto);

	}

	@Operation(description = "get All Custumers")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "return all custumers"),
	@ApiResponse(responseCode = "200", description = "return all custumers"),
	@ApiResponse(responseCode = "400", description = "not found"),})
	@GetMapping(value = "/find-all")
	public ResponseEntity<List<CustomerDto>> findAll() {

		// 1- Ou apenas executar este mapeamento com apenas uma linha
		List<CustomerDto> listDto = clienteService.findAll().stream().map(obj -> new CustomerDto(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);


	}

	@Operation(description = "Create Custumer")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "return all custumers"),
	@ApiResponse(responseCode = "200", description = "created"),
	@ApiResponse(responseCode = "400", description = "Ids must not be null"),})
	@PostMapping(value = "/create")
	public ResponseEntity<Custommer> create(@Valid @RequestBody Custommer objDto) {
		Custommer obj = clienteService.create(objDto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();

	}

	@Operation(description = "update Custumer")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
	@ApiResponse(responseCode = "200", description = "updated"),
	@ApiResponse(responseCode = "400", description = "Fields must not be null"),})
	@PutMapping(value = "update/{id}")
	public void upDate(@PathVariable Integer id, @Valid @RequestBody Custommer objDto) {

		CustomerDto newObj = new CustomerDto(clienteService.update(id, objDto));

		// return newObj;
	}
	@Operation(description = "delete Custumer")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
	@ApiResponse(responseCode = "200", description = "delete"),
	@ApiResponse(responseCode = "400", description = "ok"),})
	@DeleteMapping(value = "delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {

		clienteService.delete(id);

		return ResponseEntity.noContent().build();

	}

}
