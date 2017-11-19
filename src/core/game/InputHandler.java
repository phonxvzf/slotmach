package core.game;

import java.util.HashSet;
import java.util.Set;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

import javafx.scene.input.KeyCode;

public class InputHandler {
	
	private static Set<KeyCode> keyDown = new HashSet<>();
	private static Queue<KeyCode> triggeredKeyQueue = new ConcurrentLinkedDeque<>();
	
	public static void pressKey(KeyCode code) {
		if (!isKeyDown(code)) {
			keyDown.add(code);
			triggeredKeyQueue.add(code);
		}
	}
	
	public static void releaseKey(KeyCode code) {
		keyDown.remove(code);
	}
	
	public static boolean isKeyDown(KeyCode code) {
		return keyDown.contains(code);
	}
	
	public static KeyCode pollTriggeredKey() {
		if (triggeredKeyQueue.size() > 0) return triggeredKeyQueue.poll();
		return null;
	}
}
