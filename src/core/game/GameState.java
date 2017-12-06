package core.game;

import java.util.ArrayList;
import java.util.List;

import core.settings.Settings;

public class GameState {

	String name = "";

	private int money = Settings.PLAYER_START_MONEY;
	private double mana = Settings.PLAYER_MAX_MANA;
	private List<Boolean> matchRow;
	private boolean isCanPull;

	public GameState() {
		matchRow = new ArrayList<Boolean>();
		int rowCount = (int) (Settings.SLOT_DEFAULT_COLUMN_HEIGHT / Settings.SLOT_DEFAULT_WIDTH);
		for (int i = 0; i < rowCount; ++i) {
			matchRow.add(false);
		}
	}

	public boolean isMatchRow(int i) {
		return matchRow.get(i).booleanValue();
	}

	public synchronized void matchRow(int i) {
		matchRow.set(i, Boolean.valueOf(true));
	}

	public synchronized void clearMatch() {
		for (int i = 0; i < matchRow.size(); ++i) {
			matchRow.set(i, Boolean.valueOf(false));
		}
	}

	public int getMoney() {
		return money;
	}

	public synchronized void giveMoney(int amount) {
		int newAmount = money + amount;
		if (newAmount < 0)
			newAmount = 0;
		if (newAmount > Settings.PLAYER_MAX_MONEY)
			newAmount = Settings.PLAYER_MAX_MONEY;
		money = newAmount;
	}

	public synchronized void giveMana(double amount) {
		double newAmount = mana + amount;
		if (newAmount < 0)
			newAmount = 0;
		if (newAmount > Settings.PLAYER_MAX_MONEY)
			newAmount = Settings.PLAYER_MAX_MONEY;
		mana = newAmount;
	}

	public double getMana() {
		return mana;
	}

	public String getName() {
		return name;
	}

	public synchronized void setName(String name) {
		this.name = name;
	}

	public void setMoney(int money) {
		if (money > Settings.PLAYER_MAX_MONEY)
			this.money = Settings.PLAYER_MAX_MONEY;
		else
			this.money = money;
	}

	public boolean isCanPull() {
		return isCanPull;
	}

	public synchronized void setCanPull(boolean isCanPull) {
		this.isCanPull = isCanPull;
	}
}
