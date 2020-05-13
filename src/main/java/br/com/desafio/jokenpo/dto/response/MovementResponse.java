package br.com.desafio.jokenpo.dto.response;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.desafio.jokenpo.enumeration.MovementEnum;

public class MovementResponse {

	@NotNull(message = "PlayerId is required")
	private UUID playerId;

	@NotNull(message = "Movement is required")
	@NotBlank(message = "Movement cannot be blank")
	private MovementEnum movement;

	public MovementResponse() {
		super();
	}

	public MovementResponse(
			@NotNull(message = "PlayerId is required") @NotBlank(message = "PlayerId cannot be blank") UUID playerId,
			@NotNull(message = "Movement is required") @NotBlank(message = "Movement cannot be blank") MovementEnum movement) {
		super();
		this.playerId = playerId;
		this.movement = movement;
	}

	public UUID getPlayerId() {
		return playerId;
	}

	public void setPlayerId(UUID playerId) {
		this.playerId = playerId;
	}

	public MovementEnum getMovement() {
		return movement;
	}

	public void setMovement(MovementEnum movement) {
		this.movement = movement;
	}

}
