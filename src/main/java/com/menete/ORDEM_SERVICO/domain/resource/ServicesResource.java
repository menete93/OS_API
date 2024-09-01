package com.menete.ORDEM_SERVICO.domain.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.menete.ORDEM_SERVICO.domain.dto.ServicesDto;
import com.menete.ORDEM_SERVICO.domain.entity.Services;
import com.menete.ORDEM_SERVICO.domain.service.ServicesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

public class ServicesResource {
	


	@Autowired
	ServicesService servicesService;

	@Operation(description = "get get service by name")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "return service"),
			@ApiResponse(responseCode = "400", description = "service not found ! id"), })
	@GetMapping(value = "/{id}")
	public ResponseEntity<ServicesDto> finById(@PathVariable Integer id) {
		Services obj = servicesService.findById(id);
		ServicesDto objDto = new ServicesDto(obj);

		return ResponseEntity.ok().body(objDto);

	}

	@Operation(description = "get All services")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "return All services"),
			@ApiResponse(responseCode = "200", description = "return All services"),
			@ApiResponse(responseCode = "400", description = "not found"), })
	@GetMapping
	public ResponseEntity<List<ServicesDto>> findAll() {

		// 1- Ou apenas executar este mapeamento com apenas uma linha
		List<ServicesDto> listDto = ServicesService.findAll().stream().map(obj -> new ServicesDto(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);

	}

	@Operation(description = "Create service")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "return all service"),
			@ApiResponse(responseCode = "200", description = "created"),
			@ApiResponse(responseCode = "400", description = "ID's number already saved in the data base"), })
	@PostMapping
	public ResponseEntity<ServicesDto> create(@Valid @RequestBody ServicesDto objDto) {
		Services obj = servicesService.create(objDto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();

	}

	@Operation(description = "update Services")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "200", description = "updated"),
			@ApiResponse(responseCode = "400", description = "Fields must not be null"), })
	@PutMapping(value = "/{id}")
	public void upDate(@PathVariable Integer id, @Valid @RequestBody ServicesDto objDto) {

		ServicesDto newObj = new ServicesDto(servicesService.update(id, objDto));

		// return newObj;
	}

	@Operation(description = "delete Technician")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "200", description = "deleted"),
			@ApiResponse(responseCode = "400", description = "OK"), })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {

		servicesService.delete(id);

		return ResponseEntity.noContent().build();

	}


}
