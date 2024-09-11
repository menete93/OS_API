
package com.menete.ORDEM_SERVICO.domain.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.menete.ORDEM_SERVICO.domain.dto.OsAllDTO;
import com.menete.ORDEM_SERVICO.domain.dto.OsCreateDTO;
import com.menete.ORDEM_SERVICO.domain.dto.OsDto;
import com.menete.ORDEM_SERVICO.domain.service.OsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/os")
public class OsResource {

	@Autowired
	OsService osService;

	@Operation(description = "get order service manager by id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "return OS by id"),
	@ApiResponse(responseCode = "400", description = "Object not found!"), })
	@GetMapping(value = "find-by-id/{id}")
	public ResponseEntity<OsDto> findById(@PathVariable Integer id) {

		OsDto objDto = new OsDto(osService.findById(id));

		return ResponseEntity.ok().body(objDto);

	}

	@Operation(description = "get order service manager")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "return All OS"),
	@ApiResponse(responseCode = "400", description = "Error request check the documentation"), })
	@GetMapping(value = "find-all")
	public ResponseEntity<List<OsAllDTO>> findAll() {

		// Conversao em DTO
		List<OsAllDTO> list = osService.findAll().stream().map(osObj -> new OsAllDTO(osObj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);

	}

	@Operation(description = "Create Order Service Manager")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Create Order Service Manager"),
	@ApiResponse(responseCode = "200", description = "created"),
	@ApiResponse(responseCode = "400", description = "Ids must not be null"),})
	@PostMapping(value = "create")
	public ResponseEntity<OsCreateDTO> create(@Valid @RequestBody OsCreateDTO obj) {

		 obj = new OsCreateDTO(osService.create(obj));
		// return the created obj
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj).toUri();
		return ResponseEntity.created(uri).build();

	}

	@Operation(description = "update Order Service Manager")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
	@ApiResponse(responseCode = "200", description = "updated"),
	@ApiResponse(responseCode = "400", description = "Fields must not be null"),})
	 @PutMapping(value = "update/{id}")
	 public ResponseEntity<OsCreateDTO> update(@PathVariable Integer id ,@Valid @RequestBody OsCreateDTO dto) {
		 
		OsCreateDTO obj = new OsCreateDTO(osService.update(id,dto));
		return ResponseEntity.ok().body(obj);
	 }
	
}
