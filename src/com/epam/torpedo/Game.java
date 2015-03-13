package com.epam.torpedo;

public abstract class Game implements Startable {
	protected BattleField battleField;
	protected Hunter hunter;

	public void setBattleFieldFillingStrategy(BattleField battleField) {
		this.battleField = battleField;
	}

	public void setHunterStrategy(Hunter hunter) {
		this.hunter = hunter;
	}
}
