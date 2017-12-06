package core.game;

import java.util.ArrayList;
import java.util.List;

import core.settings.Settings;

public class GameState {

	String name = "";

	private int money = Settings.PLAYER_START_MONEY;
	private int highScore = money;
	private int payout = 0;
	private double mana = Settings.PLAYER_MAX_MANA;
	private double rowMultiplier = 1.0f;
	private double colMultiplier = 1.0f;
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
		if (money > highScore) highScore = money;
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

	public double getColMultiplier() {
		return colMultiplier;
	}

	public void incColMultiplier() {
		colMultiplier *= 4.0f;
	}

	public void decColMultiplier() {
		colMultiplier /= 4.0f;
	}

	public double getRowMultiplier() {
		return rowMultiplier;
	}

	public void incRowMultiplier() {
		rowMultiplier /= 1.5f;
	}

	public void decRowMultiplier() {
		rowMultiplier *= 1.5f;
	}

	public int getPayout() {
		return payout;
	}

	public void setPayout(int payout) {
		this.payout = payout;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
}
