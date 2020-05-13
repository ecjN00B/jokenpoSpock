package br.com.desafio.jokenpo.service;

import java.util.List;
import java.util.UUID;

import br.com.desafio.jokenpo.dto.request.PlayerRequest;
import br.com.desafio.jokenpo.dto.response.PlayerResponse;

public interface PlayerService {
	
	public PlayerResponse findById(UUID uuid) throws Exception;

	public List<PlayerResponse> findAll();
	
	public PlayerResponse create(PlayerRequest playerRequest) throws Exception;
	
	public PlayerResponse update(PlayerRequest playerRequest) throws Exception;
	
	public boolean delete(UUID uuid);

}
