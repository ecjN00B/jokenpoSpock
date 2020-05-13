package br.com.desafio.jokenpo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import br.com.desafio.jokenpo.dto.request.PlayerRequest;
import br.com.desafio.jokenpo.dto.response.PlayerResponse;
import br.com.desafio.jokenpo.repository.PlayerRepository;
import br.com.desafio.jokenpo.service.impl.PlayerServiceImpl;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

	@Mock
	private PlayerRepository playerRepository;

	@InjectMocks
	private PlayerServiceImpl playerServiceImpl;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void createPlayer() {
		final String name = "player";
		final PlayerRequest player = new PlayerRequest("player");
		when(playerRepository.create(name)).thenReturn(new PlayerResponse());

		PlayerResponse savedPlayer = playerServiceImpl.create(player);

		assertThat(savedPlayer).isNotNull();
		verify(playerRepository).create(name);

	}

	@Test
	void throwPlayerError() {
		final String name = "player";
		when(playerRepository.create(name)).thenThrow(ResponseStatusException.class);
		verify(playerRepository, never()).create(name);
	}

	@Test
	void getPlayerById() {
		final UUID uuid = UUID.randomUUID();
		final PlayerResponse player = new PlayerResponse("player");

		when(playerRepository.findById(uuid)).thenReturn(player);
		assertThat(playerServiceImpl.findById(uuid)).isNotNull();
	}

	@Test
	void getAllPlayers() {
		List<PlayerResponse> players = new ArrayList<>();
		
		PlayerResponse player1 = new PlayerResponse("player1");
		PlayerResponse player2 = new PlayerResponse("player2");
		PlayerResponse player3 = new PlayerResponse("player3");
		
		players.add(player1);
		players.add(player2);
		players.add(player3);

		when(playerRepository.findAll()).thenReturn(players);
		assertEquals(playerServiceImpl.findAll(), players);
	}
	
	@Test
	void updatePlayerById() {
		final PlayerRequest playerRequest = new PlayerRequest("player");
		final PlayerResponse playerResponse = new PlayerResponse("player");
		
		when(playerRepository.update(ArgumentMatchers.<PlayerResponse>any())).thenReturn(playerResponse);
		assertEquals(playerServiceImpl.update(playerRequest).getUuid(), playerResponse.getUuid());
	}
	
	@Test
	void updateException() {
		final PlayerResponse playerResponse = new PlayerResponse("player");
		verify(playerRepository, never()).update(playerResponse);
	}
	
	@Test
	void deleteException() {
		final UUID uuid = UUID.randomUUID();
		verify(playerRepository, never()).delete(uuid);
	}
	
	@Test
	void deletePlayerById() {
		final UUID uuid = UUID.randomUUID();
		assertFalse(playerRepository.delete(uuid));
	}

}
