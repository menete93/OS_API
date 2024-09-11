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

import com.menete.ORDEM_SERVICO.domain.dto.TechnicialAllDTO;
import com.menete.ORDEM_SERVICO.domain.dto.TechnicianDto;
import com.menete.ORDEM_SERVICO.domain.entity.Technician;
import com.menete.ORDEM_SERVICO.domain.service.TecnicianService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/technician")
public class TechnicianResource {

	@Autowired
	TecnicianService tecnicoService;

	@Operation(description = "get Technician by id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "return Technician"),
			@ApiResponse(responseCode = "400", description = "Technician not found ! id"), })
	@GetMapping(value = "find-by/{id}")
	public ResponseEntity<TechnicianDto> finById(@PathVariable Integer id) {
		Technician obj = tecnicoService.findById(id);
		TechnicianDto objDto = new TechnicianDto(obj);

		return ResponseEntity.ok().body(objDto);

	}

	@Operation(description = "get All Technicians")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "return All Technicians"),
			@ApiResponse(responseCode = "200", description = "return All Technicians"),
			@ApiResponse(responseCode = "400", description = "not found"), })
	@GetMapping(value = "find-all")
	public ResponseEntity<List<TechnicialAllDTO>> findAll() {

		// 1- Ou apenas executar este mapeamento com apenas uma linha
		List<TechnicialAllDTO> listDto = tecnicoService.findAll().stream().map(obj -> new TechnicialAllDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);

	}

	@Operation(description = "Create Technician")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "return all Technician"),
			@ApiResponse(responseCode = "200", description = "created"),
			@ApiResponse(responseCode = "400", description = "ID's number already saved in the data base"), })
	@PostMapping(value = "create")
	public ResponseEntity<TechnicianDto> create(@Valid @RequestBody TechnicianDto objDto) {
		Technician obj = tecnicoService.create(objDto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();

	}

	@Operation(description = "update Technician")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "200", description = "updated"),
			@ApiResponse(responseCode = "400", description = "Fields must not be null"), })
	@PutMapping(value = "update/{id}")
	public void upDate(@PathVariable Integer id, @Valid @RequestBody TechnicianDto objDto) {

		TechnicianDto newObj = new TechnicianDto(tecnicoService.update(id, objDto));

		// return newObj;
	}

	@Operation(description = "delete Technician")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "200", description = "deleted"),
			@ApiResponse(responseCode = "400", description = "OK"), })
	@DeleteMapping(value = "delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {

		tecnicoService.delete(id);

		return ResponseEntity.noContent().build();

	}

}
