package br.com.desafio.jokenpo.enumeration;

public enum MovementEnum {
	TESOURA(0), PAPEL(1), PEDRA(2), LAGARTO(3), SPOCK(4);
	
	public int movement;
	MovementEnum(int movement) {
		this.movement = movement;
	}
	
	public int getMovement() {
		return this.movement;
	}
}
