package core.game;

import core.settings.Settings;

public class GameState {
	
	private int money = 10000;
	String name = "";
	private double mana = Settings.PLAYER_MAX_MANA;
	
	public GameState() {
		
	}
	
	public int getMoney() {
		return money;
	}

	public void giveMoney(int amount) {
		int newAmount = money + amount;
		if (newAmount < 0) newAmount = 0;
		if (newAmount > Settings.PLAYER_MAX_MONEY) newAmount = Settings.PLAYER_MAX_MONEY;
		money = newAmount;
	}
	
	public void giveMana(double amount) {
		double newAmount = mana + amount;
		if (newAmount < 0) newAmount = 0;
		if (newAmount > Settings.PLAYER_MAX_MONEY) newAmount = Settings.PLAYER_MAX_MONEY;
		mana = newAmount;
	}

	public double getMana() {
		return mana;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMoney(int money) {
		this.money = money;
	}
}
