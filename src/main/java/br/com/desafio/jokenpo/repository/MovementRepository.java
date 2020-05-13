package br.com.desafio.jokenpo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.desafio.jokenpo.dto.response.MovementResponse;

@Service
public class MovementRepository {
	
	private List<MovementResponse> movements = new ArrayList<>();

	public boolean playerPerformedMovement(UUID playerId) {
		for(MovementResponse m : this.movements) {
	        if(m != null && m.getPlayerId().equals(playerId)) {
                return true;
            }
        }
        return false;
	}
	
	public List<MovementResponse> findAll() {
		return this.movements;
	}
	
	public MovementResponse findById(UUID playerId) {
		for (MovementResponse m : this.movements) {
			if(m != null && m.getPlayerId().equals(playerId)) {
				return m;
			}
		}
		return null;
	}
	
	public MovementResponse create(MovementResponse movement) {
		this.movements.add(movement);
		return movement;
	}
	
	public MovementResponse update(MovementResponse movement) {
		for (MovementResponse m : this.movements) {
			if(m != null && m.getPlayerId().equals(movement.getPlayerId())) {
				this.movements.set(this.movements.indexOf(m), movement);
				return movement;
			}
		}
		return null;
	}
	
	public boolean delete(UUID playerId) {
		for (MovementResponse m : this.movements) {
			if(m != null && m.getPlayerId().equals(playerId)) {
				this.movements.remove(m);
				return true;
			}
		}
		return false;
	}
	
}
