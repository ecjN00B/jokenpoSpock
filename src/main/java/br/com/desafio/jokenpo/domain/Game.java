package br.com.desafio.jokenpo.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.desafio.jokenpo.dto.response.GameResponse;
import br.com.desafio.jokenpo.dto.response.MovementResponse;
import br.com.desafio.jokenpo.dto.response.PlayerResponse;
import br.com.desafio.jokenpo.enumeration.MovementEnum;
import br.com.desafio.jokenpo.service.PlayerService;

@Service
public class Game {

	private static final String EMPATE = "EMPATE";
	private static final String JOGADOR1 = "JOGADOR1 VENCE";
	private static final String JOGADOR2 = "JOGADOR2 VENCE";

	@Autowired
	private PlayerService playerService;

	public GameResponse getWinnersList(List<MovementResponse> movements) {
		List<PlayerResponse> players = playerService.findAll();
		List<PlayerResponse> winners = new ArrayList<>();
		List<MovementEnum> winnersMovements = new ArrayList<>();

		if (players.size() == 0 || movements.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não há jogadores/movimentos cadastrados");
		}

		GameResponse response = new GameResponse();
		String message = "";

		for (MovementResponse m : movements) {
			if (this.winnerMovement(m, movements)) {
				winners.add(playerService.findById(m.getPlayerId()));
				winnersMovements.add(m.getMovement());
				message = "Jogo com vencedores";
			}
		}

		if (winners.size() == movements.size()) {
			message = "Empate";
			winners = new ArrayList<>();
			winnersMovements = Arrays.asList(movements.get(0).getMovement());
		} else if (winners.size() == 0 || winnersMovements.size() == 0) {
			message = "Não há vencedores";
		}

		response.setMessage(message);
		response.setWinners(winners);
		response.setWinnersMovements(winnersMovements.stream().distinct().collect(Collectors.toList()));

		return response;
	}

	private String gameRule(Integer movement1, Integer movement2) {
		if (movement1 == movement2) {
			return EMPATE;
		}

		if ((movement1 % 5 + 1) % 5 == movement2 || (movement1 % 5 + 3) % 5 == movement2) {
			return JOGADOR1;
		}

		return JOGADOR2;
	}

	private boolean winnerMovement(MovementResponse movement, List<MovementResponse> movements) {
		boolean vencedor = true;

		List<Integer> movementsInteger = movements.stream().map(movementNumber -> movementNumber.getMovement().movement)
				.collect(Collectors.toList());
		Integer movimentInteger = movement.getMovement().movement;

		for (Integer m : movementsInteger) {
			if (this.gameRule(movimentInteger, m).equals(JOGADOR2)) {
				vencedor = false;
				break;
			}
		}
		return vencedor;
	}

}
