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

import com.menete.ORDEM_SERVICO.domain.Tecnico;
import com.menete.ORDEM_SERVICO.domain.dto.TecnicoDto;
import com.menete.ORDEM_SERVICO.domain.service.TecnicoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

	@Autowired
	TecnicoService tecnicoService;

	
	@Operation(description = "get Technician by id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "return Technician"),
	@ApiResponse(responseCode = "400", description = "Technician not found ! id"), })
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDto> finById(@PathVariable Integer id) {
		Tecnico obj = tecnicoService.findById(id);
		TecnicoDto objDto = new TecnicoDto(obj);

		return ResponseEntity.ok().body(objDto);

	}

	@Operation(description = "get All Technicians")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "return All Technicians"),
	@ApiResponse(responseCode = "200", description = "return All Technicians"),
	@ApiResponse(responseCode = "400", description = "not found"),})
	@GetMapping
	public ResponseEntity<List<TecnicoDto>> findAll() {

		// 1- Ou apenas executar este mapeamento com apenas uma linha
		List<TecnicoDto> listDto = tecnicoService.findAll().stream().map(obj -> new TecnicoDto(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);

		// 2- podemos escrever desta forma

//		List<Tecnico> list = tecnicoService.findAll();
//		List<TecnicoDto> listDto = new ArrayList<>();
//		
		// 3- ou enta com apenas uma linha
//		for (Tecnico obj : list) {
//			listDto.add(new TecnicoDto(obj));
//		}

//           list.forEach(obj -> listDto.add(new TecnicoDto(obj)));
	}

	@Operation(description = "Create Technician")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "return all Technician"),
	@ApiResponse(responseCode = "200", description = "created"),
	@ApiResponse(responseCode = "400", description = "ID's number already saved in the data base"),})
	@PostMapping
	public ResponseEntity<TecnicoDto> create(@Valid @RequestBody TecnicoDto objDto) {
		Tecnico obj = tecnicoService.create(objDto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();

	}

	@Operation(description = "update Technician")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
	@ApiResponse(responseCode = "200", description = "updated"),
	@ApiResponse(responseCode = "400", description = "Fields must not be null"),})
	@PutMapping(value = "/{id}")
	public void upDate(@PathVariable Integer id, @Valid @RequestBody TecnicoDto objDto) {

		TecnicoDto newObj = new TecnicoDto(tecnicoService.update(id, objDto));

		// return newObj;
	}

	@Operation(description = "delete Technician")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
	@ApiResponse(responseCode = "200", description = "deleted"),
	@ApiResponse(responseCode = "400", description = "OK"),})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {

		tecnicoService.delete(id);

		return ResponseEntity.noContent().build();

	}

}
