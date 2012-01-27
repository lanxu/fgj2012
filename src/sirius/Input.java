package sirius;
import org.lwjgl.input.Keyboard;

/* Specialize input for this game */

public class Input {

	public static final int KEY_QUIT = 0;
	public static final int KEY_LEFT = 1;
	public static final int KEY_RIGHT = 2;
	public static final int KEY_UP = 3;
	public static final int KEY_DOWN = 4;
	public static final int KEY_START = 5;
	public static final int KEY_SELECT = 6;
	public static final int KEY_A = 7;
	public static final int KEY_B = 8;
	
	
	public Input() {
		
	}
	
	public static boolean isDown(int key) {
		if(key == KEY_QUIT) return Keyboard.isKeyDown(Keyboard.KEY_ESCAPE);
		if(key == KEY_LEFT) return Keyboard.isKeyDown(Keyboard.KEY_A);
		if(key == KEY_RIGHT) return Keyboard.isKeyDown(Keyboard.KEY_D);
		if(key == KEY_UP) return Keyboard.isKeyDown(Keyboard.KEY_W);
		if(key == KEY_DOWN) return Keyboard.isKeyDown(Keyboard.KEY_S);
		if(key == KEY_A) return Keyboard.isKeyDown(Keyboard.KEY_E);
		if(key == KEY_B) return Keyboard.isKeyDown(Keyboard.KEY_SPACE);
		return false;
	}

}
