package br.com.desafio.jokenpo.service;

import java.util.List;
import java.util.UUID;

import br.com.desafio.jokenpo.dto.request.MovementRequest;
import br.com.desafio.jokenpo.dto.response.MovementResponse;

public interface MovementService {
	
	public MovementResponse findById(UUID playerId) throws Exception;

	public List<MovementResponse> findAll();
	
	public MovementResponse create(MovementRequest movementRequest) throws Exception;
	
	public MovementResponse update(MovementRequest movementRequest) throws Exception;
	
	public boolean delete(UUID playerId);

}
