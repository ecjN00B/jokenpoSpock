package br.com.desafio.jokenpo.dto.response;

import java.util.ArrayList;
import java.util.List;

import br.com.desafio.jokenpo.enumeration.MovementEnum;

public class GameResponse {

	private List<PlayerResponse> winners = new ArrayList<>();
	private List<MovementEnum> winnersMovements = new ArrayList<>();
	private String message;

	public GameResponse() {
		super();
	}

	public GameResponse(List<PlayerResponse> winners, List<MovementEnum> winnersMovements, String message) {
		super();
		this.winners = winners;
		this.winnersMovements = winnersMovements;
		this.message = message;
	}

	public List<PlayerResponse> getWinners() {
		return winners;
	}

	public void setWinners(List<PlayerResponse> winners) {
		this.winners = winners;
	}

	public List<MovementEnum> getWinnersMovements() {
		return winnersMovements;
	}

	public void setWinnersMovements(List<MovementEnum> winnersMovements) {
		this.winnersMovements = winnersMovements;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
