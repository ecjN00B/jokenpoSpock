package br.com.desafio.jokenpo.dto.request;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.desafio.jokenpo.dto.response.PlayerResponse;

public class PlayerRequest {

	
	private UUID uuid = UUID.randomUUID();
	
	@NotNull(message = "Name is required" )
	@NotBlank(message = "Name cannot be blank")
	private String name;

	public PlayerRequest() {
		super();
	}

	public PlayerRequest(String name) {
		super();
		this.name = name;
	}
	
	public PlayerRequest(UUID uuid, String name) {
		super();
		this.uuid = uuid;
		this.name = name;
	}

	public UUID getUuid() {
		return uuid;
	}
	
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public PlayerResponse toResponse() {
		PlayerResponse playerResponse = new PlayerResponse();
		playerResponse.setUuid(this.uuid);
		playerResponse.setName(this.name);
		return playerResponse;
	}

}
