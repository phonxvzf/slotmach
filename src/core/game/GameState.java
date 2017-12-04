package core.game;

import core.settings.Settings;

public class GameState {
	
	private int money = 0;
	private String name;
	private int useCols = 3, useRows = 3;
	private double mana = Settings.PLAYER_MAX_MANA;
	
	public GameState() {
		
	}
	
	public int getMoney() {
		return money;
	}

	public int getUseCols() {
		return useCols;
	}

	public void setUseCols(int useCols) {
		this.useCols = useCols;
	}

	public int getUseRows() {
		return useRows;
	}

	public void setUseRows(int useRows) {
		this.useRows = useRows;
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
}
