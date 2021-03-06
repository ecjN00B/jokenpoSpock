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

import br.com.desafio.jokenpo.dto.request.PlayerRequest;
import br.com.desafio.jokenpo.dto.response.PlayerResponse;
import br.com.desafio.jokenpo.service.PlayerService;

@RestController
@RequestMapping("/api/v1/player")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PlayerResponse>> findAll() {
		List<PlayerResponse> response = playerService.findAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlayerResponse> findById(@PathVariable(name = "uuid") UUID uuid) {
		PlayerResponse response = playerService.findById(uuid);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlayerResponse> create(@RequestBody @Valid PlayerRequest playerRequest) {
		PlayerResponse response = playerService.create(playerRequest);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlayerResponse> update(@RequestBody @Valid PlayerRequest playerRequest) {
		PlayerResponse response = playerService.update(playerRequest);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> delete(@PathVariable(name = "uuid") UUID uuid) {
		boolean response = playerService.delete(uuid);
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	}

}
