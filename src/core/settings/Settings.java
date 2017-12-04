package core.settings;

public class Settings {

	// Application settings
	public static final String WINDOW_TITLE = "slotmach";
	public static final double GAME_CANVAS_WIDTH = 1000, GAME_CANVAS_HEIGHT = 950;
	public static String GAME_FONT = "terminus.ttf";
	public static final double STATUS_CANVAS_WIDTH = 300, STATUS_CANVAS_HEIGHT = GAME_CANVAS_HEIGHT;
	public static final double WINDOW_WIDTH = GAME_CANVAS_WIDTH + STATUS_CANVAS_WIDTH, WINDOW_HEIGHT = GAME_CANVAS_HEIGHT;

	// Game logic options
	public static final int UPDATE_RATE = 25;
	public static final long UPDATE_LOOP_TIME = 1000000000 / UPDATE_RATE;
	
	// General in-game options
	public static final double SLOT_DEFAULT_VELOCITY = 2000.0f;
	public static final double SLOT_DEFAULT_COLUMN_HEIGHT = GAME_CANVAS_HEIGHT;
	public static final double SLOT_DEFAULT_WIDTH = 50.0f;
	public static final double SLOT_SLOWDOWN_ACCEL = -800.0f;
	public static final double SLOT_MIN_VELOCITY = 400.0f;
	public static final int SLOT_DEFAULT_COLUMNS = 3;

	public static final double PLAYER_MAX_MANA = 210.0f;
	public static final int PLAYER_MAX_MONEY = 999999;
	public static final int PLAYER_MAX_NAME_LENGTH = 12;

	public static final double SKILL_FREEZE_MPRATE_USE = 50.0f;
	public static final double SKILL_FREEZE_MPRATE_RECOVER = 25.0f;

}
