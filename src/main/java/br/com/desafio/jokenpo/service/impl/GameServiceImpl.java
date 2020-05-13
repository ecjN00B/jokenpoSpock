package br.com.desafio.jokenpo.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.jokenpo.domain.Game;
import br.com.desafio.jokenpo.dto.response.GameResponse;
import br.com.desafio.jokenpo.dto.response.MovementResponse;
import br.com.desafio.jokenpo.dto.response.PlayerResponse;
import br.com.desafio.jokenpo.repository.MovementRepository;
import br.com.desafio.jokenpo.repository.PlayerRepository;
import br.com.desafio.jokenpo.service.GameService;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private MovementRepository movementRepository;
	
	@Autowired
	private Game gameDomain;

	@Override
	public GameResponse play() throws Exception {
		if (this.allPlayersAlreadyPerformedAMovement()) {
			return gameDomain.getWinnersList(movementRepository.findAll());
		}
		throw new Exception("Todos os Jogadores precisam efetuar seu movimento");
	}

	private boolean allPlayersAlreadyPerformedAMovement() {
		List<PlayerResponse> players = playerRepository.findAll();
		List<MovementResponse> movements = movementRepository.findAll();

		List<UUID> playersId = players.stream()
				.map(PlayerResponse::getUuid).collect(Collectors.toList());
		List<UUID> playersWhoPerformedMovements = movements.stream()
				.map(MovementResponse::getPlayerId).collect(Collectors.toList());
		
		return playersId.containsAll(playersWhoPerformedMovements);
	}

}
