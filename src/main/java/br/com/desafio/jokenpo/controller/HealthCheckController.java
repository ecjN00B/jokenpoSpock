package br.com.desafio.jokenpo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HealthCheckController {

	@GetMapping("/probe/alive")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
	
}
