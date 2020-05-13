package br.com.desafio.jokenpo.dto.response;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PlayerResponse {

	
	private UUID uuid = UUID.randomUUID();
	
	@NotNull(message = "Name is required")
	@NotBlank(message = "Name cannot be blank")
	private String name;

	public PlayerResponse() {
		super();
	}

	public PlayerResponse(String name) {
		super();
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

}
