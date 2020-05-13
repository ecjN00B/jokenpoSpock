package br.com.desafio.jokenpo.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.desafio.jokenpo.dto.request.PlayerRequest;
import br.com.desafio.jokenpo.dto.response.PlayerResponse;
import br.com.desafio.jokenpo.repository.MovementRepository;
import br.com.desafio.jokenpo.repository.PlayerRepository;
import br.com.desafio.jokenpo.service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private MovementRepository movementRepository;
	
	@Override
	public PlayerResponse findById(UUID uuid) {
		PlayerResponse player = playerRepository.findById(uuid);
		
		if(player == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não cadastrado na base");
		}
		
		return player;
	}

	@Override
	public List<PlayerResponse> findAll() {
		return playerRepository.findAll();
	}

	@Override
	public PlayerResponse create(PlayerRequest playerRequest) {
		if(playerRepository.playerExists(playerRequest.getName())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuário já cadastrado");
		}
		PlayerResponse playerUuid = playerRepository.create(playerRequest.getName());
		return playerUuid;
	}

	@Override
	public PlayerResponse update(PlayerRequest playerRequest) {
		PlayerResponse player = playerRepository.update(playerRequest.toResponse());
		
		if(player == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não cadastrado na base");
		}
		
		return player;
	}

	@Override
	public boolean delete(UUID uuid) {
		movementRepository.delete(uuid);
		return playerRepository.delete(uuid);
	}

}
