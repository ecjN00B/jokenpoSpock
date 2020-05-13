package br.com.desafio.jokenpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.jokenpo.dto.response.GameResponse;
import br.com.desafio.jokenpo.service.GameService;

@RestController
@RequestMapping("/api/v1/game")
public class GameController {

	@Autowired
	private GameService gameService;

	@PostMapping(value = "/play", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GameResponse> play() {
		GameResponse response = gameService.play();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
