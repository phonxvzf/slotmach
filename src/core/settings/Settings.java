package core.settings;

public class Settings {

	// Application settings
	public static final String WINDOW_TITLE = "slotmach";
	public static final double GAME_CANVAS_WIDTH = 1000, GAME_CANVAS_HEIGHT = 950;
	public static String GAME_FONT = "terminus.ttf";
	public static final double STATUS_CANVAS_WIDTH = 290, STATUS_CANVAS_HEIGHT = GAME_CANVAS_HEIGHT;
	public static final double WINDOW_WIDTH = GAME_CANVAS_WIDTH + STATUS_CANVAS_WIDTH,
			WINDOW_HEIGHT = GAME_CANVAS_HEIGHT;

	// Game logic options
	public static final int UPDATE_RATE = 30;
	public static final long UPDATE_LOOP_TIME = 1000000000 / UPDATE_RATE;

	// General in-game options
	public static final double SLOT_DEFAULT_VELOCITY = 3000.0f;
	public static final double SLOT_DEFAULT_COLUMN_HEIGHT = GAME_CANVAS_HEIGHT;
	public static final double SLOT_DEFAULT_WIDTH = 50.0f;
	public static final double SLOT_SLOWDOWN_ACCEL = -800.0f;
	public static final double SLOT_MIN_VELOCITY = 400.0f;
	public static final int SLOT_DEFAULT_COLUMNS = 15;
	public static final int SLOT_DEFAULT_ROWS = 19;
	public static final int SLOT_DEFAULT_ADDLER = 2;
	public static final int SLOT_DEFAULT_BEGIN_COLUMNS = 3;
	public static final int SLOT_DEFAULT_BEGIN_ROWS = 3;

	public static final double PLAYER_MAX_MANA = 210.0f;
	public static final int PLAYER_START_MONEY = 10000;
	public static final int PLAYER_MAX_MONEY = 99999999;
	public static final int PLAYER_PAID_PULL = 50;
	public static final int PLAYER_PAID_BUYCOL = 100;
	public static final int PLAYER_PAID_EXCOL = 200;
	public static final int PLAYER_PAID_EXROW = 200;
	public static final int PLAYER_MAX_NAME_LENGTH = 12;

	public static final double SKILL_FREEZE_MPRATE_USE = 20.0f;
	public static final double SKILL_FREEZE_MPRATE_RECOVER = 10.0f;
	
	public static final long ANIMATION_MATCH_TIME = (long) 1.5e9;
	public static final long ANIMATION_MATCH_TIME_JP = (long) 4e9;

}
