package br.com.desafio.jokenpo.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.jokenpo.dto.request.MovementRequest;
import br.com.desafio.jokenpo.dto.response.MovementResponse;
import br.com.desafio.jokenpo.service.MovementService;

@RestController
@RequestMapping("/api/v1/movement")
public class MovementController {

	@Autowired
	private MovementService movementService;

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MovementResponse>> findAll() {
		List<MovementResponse> response = movementService.findAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MovementResponse> findById(@PathVariable(name = "playerId") UUID playerId) {
		MovementResponse response = movementService.findById(playerId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MovementResponse> create(@RequestBody @Valid MovementRequest movementRequest) {
		MovementResponse response = movementService.create(movementRequest);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MovementResponse> update(@RequestBody @Valid MovementRequest movementRequest) {
		MovementResponse response = movementService.update(movementRequest);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> delete(@PathVariable(name = "uuid") UUID uuid) {
		boolean response = movementService.delete(uuid);
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	}

}
