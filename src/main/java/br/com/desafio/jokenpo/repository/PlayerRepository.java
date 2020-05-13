package br.com.desafio.jokenpo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.desafio.jokenpo.dto.response.PlayerResponse;

@Service
public class PlayerRepository {
	
	private List<PlayerResponse> players = new ArrayList<>();

	public boolean playerExists(String name) {
		for(PlayerResponse p : this.players) {
	        if(p != null && p.getName().equals(name)) {
                return true;
            }
        }
        return false;
	}
	
	public List<PlayerResponse> findAll() {
		return this.players;
	}
	
	public PlayerResponse findById(UUID uuid) {
		for (PlayerResponse p : this.players) {
			if(p != null && p.getUuid().equals(uuid)) {
				return p;
			}
		}
		return null;
	}
	
	public PlayerResponse create(String name) {
		PlayerResponse player = new PlayerResponse(name);
		this.players.add(player);
		return player;
	}
	
	public PlayerResponse update(PlayerResponse player) {
		for (PlayerResponse p : this.players) {
			if(p != null && p.getUuid().equals(player.getUuid())) {
				this.players.set(this.players.indexOf(p), player);
				return player;
			}
		}
		return null;
	}
	
	public boolean delete(UUID uuid) {
		for (PlayerResponse p : this.players) {
			if(p != null && p.getUuid().equals(uuid)) {
				this.players.remove(p);
				return true;
			}
		}
		return false;
	}
	
}
