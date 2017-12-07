package core.game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private boolean isJackpot = false;
	private boolean isShowPriceTab = false;
	private Map<String, Integer> score;

	public GameState() {
		reset();
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
		if (money > highScore)
			highScore = money;
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

	public void givePayout(int amount) {
		this.payout += amount;
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

	public boolean isJackpot() {
		return isJackpot;
	}

	public void setJackpot(boolean isJackpot) {
		this.isJackpot = isJackpot;
	}

	public Map<String, Integer> getScore() {
		return score;
	}

	public void updateScore() {
		this.score.put(name, -money);
	}

	public boolean isShowPriceTab() {
		return isShowPriceTab;
	}

	public void setShowPriceTab(boolean isShowPriceTab) {
		this.isShowPriceTab = isShowPriceTab;
	}

	public void reset() {
		name = "";
		score = new HashMap<String, Integer>();
		money = Settings.PLAYER_START_MONEY;
		highScore = money;
		payout = 0;
		mana = Settings.PLAYER_MAX_MANA;
		rowMultiplier = 1.0f;
		colMultiplier = 1.0f;
		isCanPull = true;
		isJackpot = false;
		isShowPriceTab = false;
		matchRow = new ArrayList<Boolean>();
		int rowCount = (int) (Settings.SLOT_DEFAULT_COLUMN_HEIGHT / Settings.SLOT_DEFAULT_WIDTH);
		for (int i = 0; i < rowCount; ++i) {
			matchRow.add(false);
		}
		loadScore();
	}

	public void loadScore() {
		int rowCount = (int) (Settings.SLOT_DEFAULT_COLUMN_HEIGHT / Settings.SLOT_DEFAULT_WIDTH);
		for (int i = 0; i < rowCount; ++i) {
			matchRow.add(false);
		}
		try {
			BufferedReader in = new BufferedReader(new FileReader("assets/txt/score.txt"));
			String line;
			while ((line = in.readLine()) != null && line != "\n") {
				score.put(line.split(" ")[0], Integer.parseInt(line.split(" ")[1]) * -1);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeScore() {
		try {
			BufferedWriter in = new BufferedWriter(new FileWriter("assets/txt/score.txt"));
			for (String key : getScore().keySet()) {
				in.write(key + " " + getScore().get(key) * -1 + '\n');
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
