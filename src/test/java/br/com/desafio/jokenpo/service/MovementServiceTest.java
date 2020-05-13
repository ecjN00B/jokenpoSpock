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

import br.com.desafio.jokenpo.dto.request.MovementRequest;
import br.com.desafio.jokenpo.dto.response.MovementResponse;
import br.com.desafio.jokenpo.repository.MovementRepository;
import br.com.desafio.jokenpo.repository.PlayerRepository;
import br.com.desafio.jokenpo.service.impl.MovementServiceImpl;

@ExtendWith(MockitoExtension.class)
class MovementServiceTest {

	@Mock
	private PlayerRepository playerRepository;

	@Mock
	private MovementRepository movementRepository;

	@InjectMocks
	private MovementServiceImpl movementServiceImpl;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void createMovementWithoutPlayer() {
		final MovementResponse movementResponse = new MovementResponse();
		verify(movementRepository, never()).create(movementResponse);

	}

	@Test
	void throwMovementError() {
		final MovementResponse movementResponse = new MovementResponse();
		when(movementRepository.create(movementResponse)).thenThrow(ResponseStatusException.class);
		verify(movementRepository, never()).create(movementResponse);
	}

	@Test
	void getMovementById() {
		final UUID uuid = UUID.randomUUID();
		final MovementResponse movementResponse = new MovementResponse();
		movementResponse.setPlayerId(uuid);

		when(movementRepository.findById(uuid)).thenReturn(movementResponse);
		assertThat(movementServiceImpl.findById(uuid)).isNotNull();
	}

	@Test
	void getAllMovements() {
		List<MovementResponse> movements = new ArrayList<>();

		MovementResponse movement1 = new MovementResponse();
		MovementResponse movement2 = new MovementResponse();
		MovementResponse movement3 = new MovementResponse();

		movements.add(movement1);
		movements.add(movement2);
		movements.add(movement3);

		when(movementRepository.findAll()).thenReturn(movements);
		assertEquals(movementServiceImpl.findAll(), movements);
	}

	@Test
	void updateMovementById() {
		final MovementRequest movementRequest = new MovementRequest();
		final MovementResponse movementResponse = new MovementResponse();

		when(movementRepository.update(ArgumentMatchers.<MovementResponse>any())).thenReturn(movementResponse);
		assertEquals(movementServiceImpl.update(movementRequest), movementResponse);
	}

	@Test
	void updateException() {
		final MovementResponse movementResponse = new MovementResponse();
		verify(movementRepository, never()).update(movementResponse);
	}

	@Test
	void deleteException() {
		final UUID uuid = UUID.randomUUID();
		verify(movementRepository, never()).delete(uuid);
	}

	@Test
	void deleteMovementById() {
		final UUID uuid = UUID.randomUUID();
		assertFalse(movementRepository.delete(uuid));
	}

}
