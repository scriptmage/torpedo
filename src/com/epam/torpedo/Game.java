package com.epam.torpedo;

public abstract class Game {
	protected BattleField battleField;
	protected Hunter hunter;

	public void setBattleFieldFillingStrategy(BattleField battleField) {
		this.battleField = battleField;
	}

	public void setHunterStrategy(Hunter hunter) {
		this.hunter = hunter;
	}
	
	abstract public void start();
}
