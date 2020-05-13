package br.com.desafio.jokenpo.service;

import br.com.desafio.jokenpo.dto.response.GameResponse;

public interface GameService {
	
	public GameResponse play() throws Exception;

}
