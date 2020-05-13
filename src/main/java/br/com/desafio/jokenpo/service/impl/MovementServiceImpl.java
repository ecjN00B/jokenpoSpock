package br.com.desafio.jokenpo.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.desafio.jokenpo.dto.request.MovementRequest;
import br.com.desafio.jokenpo.dto.response.MovementResponse;
import br.com.desafio.jokenpo.repository.MovementRepository;
import br.com.desafio.jokenpo.repository.PlayerRepository;
import br.com.desafio.jokenpo.service.MovementService;

@Service
public class MovementServiceImpl implements MovementService {

	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private MovementRepository movementRepository;

	@Override
	public MovementResponse findById(UUID playerId) {
		MovementResponse movement = movementRepository.findById(playerId);
		
		if(movement == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movimento não cadastrado na base");
		}
		
		return movement;
	}

	@Override
	public List<MovementResponse> findAll() {
		return movementRepository.findAll();
	}

	@Override
	public MovementResponse create(MovementRequest movementRequest) {
		if(movementRepository.playerPerformedMovement(movementRequest.getPlayerId())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Jogador já realizou um movimento");
		}
		if(!playerRepository.playerExists(movementRequest.getPlayerId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não cadastrado na base");
		}
		MovementResponse movementResponse = movementRepository.create(movementRequest.toResponse());
		return movementResponse;
	}

	@Override
	public MovementResponse update(MovementRequest movementRequest) {
		MovementResponse movement = movementRepository.update(movementRequest.toResponse());
		
		if(movement == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movimento não cadastrado na base");
		}
		
		return movement;
	}

	@Override
	public boolean delete(UUID playerId) {
		return movementRepository.delete(playerId);
	}
	
	
}
