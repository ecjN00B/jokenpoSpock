package br.com.desafio.jokenpo.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public MovementResponse findById(UUID playerId) throws Exception {
		MovementResponse movement = movementRepository.findById(playerId);
		
		if(movement == null) {
			throw new Exception("Movimento não cadastrado na base");
		}
		
		return movement;
	}

	@Override
	public List<MovementResponse> findAll() {
		return movementRepository.findAll();
	}

	@Override
	public MovementResponse create(MovementRequest movementRequest) throws Exception {
		if(movementRepository.playerPerformedMovement(movementRequest.getPlayerId())) {
			throw new Exception("Jogador já realizou um movimento");
		}
		if(!playerRepository.playerExists(movementRequest.getPlayerId())) {
			throw new Exception("Usuário não cadastrado na base");
		}
		MovementResponse movementResponse = movementRepository.create(movementRequest.toResponse());
		return movementResponse;
	}

	@Override
	public MovementResponse update(MovementRequest movementRequest) throws Exception {
		MovementResponse movement = movementRepository.update(movementRequest.toResponse());
		
		if(movement == null) {
			throw new Exception("Movimento não cadastrado na base");
		}
		
		return movement;
	}

	@Override
	public boolean delete(UUID playerId) {
		return movementRepository.delete(playerId);
	}
	
	
}
