/* Quick game
 *
 * @author lanxu <jukka.lankinen@gmail.com>
 * @version 1.0
 */

package game;

import org.jbox2d.common.Vec2;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.Rectangle;

import sirius.Entity;
import sirius.Input;
import sirius.graphics.Graphics;
import sirius.graphics.GraphicsEntity;
import sirius.graphics.Texture;
import sirius.graphics.TextureLoader;
import sirius.physics.Physics;
import sirius.physics.PhysicsEntity;


import static org.lwjgl.opengl.GL11.*;

public class Main {
	private static final int FRAMERATE = 60;

	public static int ID_GOAL = 1;
	
	public static Physics physics_;
	public static Input input_;
	public static Graphics graphics_;

	private static boolean finished_;
	private static Texture characters_;
	private static Texture tiles_;
	private static boolean flip_;
	private static int tile = 1;
	private static Menu menu;
	private static int frame_ = 0;
	private static Level1 level1_;
	private static Level2 level2_;
	private static Level3 level3_;
	private static Level4 level4_;
	private static float gravity_ = 10.0f;
	private static float rotation_ = 0;
	private static boolean mouseDown_ = false;
	private static int mouseStartX = 0;
	private static int mouseStartY = 0;
	private static float mouseStartAngle = 0;
	private static boolean clickedExit = false;
	private static boolean mouseHeld = false;
	
	// Game states
	public static final int STATE_MENU = 0;
	public static final int STATE_OUTGAME = 1;
	public static final int STATE_INGAME = 2; 


	public static int state_ = 0;
	public static void main(String[] args) {
		try {
			// Init the game
			init(false);
			// Run the game
			run();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Free stuff
			cleanup();

		}
	}

	private static void init(boolean fullscreen) throws Exception {
		// Create display
		Display.setTitle("Liquid Panic");
		Display.setFullscreen(false);
		Display.setVSyncEnabled(false);
		Display.setDisplayMode(new DisplayMode(800, 600));
		Display.create();

		// Create managers
		graphics_ = new Graphics();
		physics_ = new Physics(graphics_); // BROKEN
		input_ = new Input();

		// Init managers
		graphics_.init();
		physics_.init();
	
	//	level_ = new Level1();
		menu = new Menu();
		
	}

	private static void render() {
		//if(state_ == STATE_INGAME) {		
			graphics_.renderEntities();
		//}
		
		Display.update();
	}

	private static float getAngle(float x, float y) {
		float dx = x - 400; // FIXME!!111111
		float dy = y - 300;
		float angle;

		if(dy < 0) {
			if(dx >= 0) {
				angle = (float) Math.atan(dy/dx);
			}
			else {
				angle = (float) ((-3.14f / 2.0f) - Math.atan(dx/dy));
			}
		}
		else {
			if(dx >= 0) {
				angle = (float) ((-3.0f/2.0f)*3.1415926535897932384626433832795028841f - Math.atan(dx/dy));
			}
			else {
				angle = (float) (-3.1415926535897932384626433832795028841f - Math.atan(-dy/dx));
			}
		}

		return 2.0f * angle;
	}

	private static float getAngleSlider(float x, float x_max) {
		float dx = Mouse.getX() - x_max;
		float angle;

		angle = (dx / 400) * 1.0f * 3.14159f;

		return angle;
	}

	private static void logic() {
		// INGAME
		if(state_ == STATE_INGAME) {
			physics_.update();
			if(input_.isDown(Input.KEY_RIGHT)) {
				rotation_--;
			}
			if(input_.isDown(Input.KEY_LEFT)) {
				rotation_++;
			}
			if(input_.isDown(Input.KEY_A)) {
				physics_.init();
				graphics_.init();
				//level_ = new Level1();
				graphics_.state_ = STATE_INGAME;
			}
			Mouse.getEventButtonState();
			
			float z = Mouse.getDWheel();
			//System.out.println("H "+z);
			if(z < 0) graphics_.globalScale_-=0.01f;
			if(z > 0) graphics_.globalScale_+=0.01f;
			int x = Mouse.getX();
			int y = Mouse.getY();

			//System.out.println("X: "+x+"  y: "+y);

			if (Mouse.isButtonDown(0)) {
			
				if(mouseDown_ == false) {

					mouseStartAngle = getAngle(Mouse.getX(), Mouse.getY());
					//mouseStartAngle = getAngleSlider(Mouse.getX(), 400);
					mouseDown_ = true;
				} else {
					float mouseEndAngle;
					mouseEndAngle = getAngle(Mouse.getX(), Mouse.getY());
					//mouseEndAngle = getAngleSlider(Mouse.getX(), 400);

					float drot = (float) ((mouseEndAngle - mouseStartAngle)*(180.0f/3.14f));
					//System.out.println("Drot: "+drot);
					//System.out.println("x: " + Mouse.getX());
					//System.out.println("y: " + Mouse.getY());
					//System.out.println("start: "+mouseStartAngle);
					//System.out.println("stop : "+mouseEndAngle);

					mouseDown_ = false;
					rotation_ += drot;

				}

				//boolean leftButtonDown = Mouse.isButtonDown(0);
				//boolean rightButtonDown = Mouse.isButtonDown(1);
				// NEXT (if score > 300)
				if(x > 650 && x < 790 && y > 7 && y < 44) {
					//System.out.println("Next");
					if(graphics_.currentLevel_ == 0) {
						physics_.init();
						graphics_.init();
						level2_ = new Level2();
						state_ = STATE_INGAME;
						graphics_.state_ = STATE_INGAME;
						
						graphics_.currentLevel_ = 1;						
					} else if(graphics_.currentLevel_ == 1) {
						physics_.init();
						graphics_.init();
						level3_ = new Level3();
						state_ = STATE_INGAME;
						graphics_.state_ = STATE_INGAME;
						
						graphics_.currentLevel_ = 2;							
					} else if(graphics_.currentLevel_ == 2) {
						physics_.init();
						graphics_.init();
						level4_ = new Level4();
						state_ = STATE_INGAME;
						graphics_.state_ = STATE_INGAME;
						
						graphics_.currentLevel_ = 3;							
					} else {
						graphics_.init();
						physics_.init();
						graphics_.state_ = STATE_MENU;
						state_ = STATE_MENU;
						rotation_ = 0;
					//	level_ = new Level1();
						menu = new Menu();						
					}
					
				}
				if(mouseHeld == false) {
					if(x > 9 && x < 139 && y > 7 && y < 44) {
						clickedExit = true;
					}
					mouseHeld = true;
				}
				// Back (if score > 300)

			} else {
				mouseDown_ = false;
				mouseHeld = false;
				if(clickedExit) {
					if(x > 9 && x < 139 && y > 7 && y < 44) {
					//System.out.println("Back");
					graphics_.init();
					physics_.init();
					graphics_.state_ = STATE_MENU;
					state_ = STATE_MENU;
					rotation_ = 0;
				//	level_ = new Level1();
					menu = new Menu();
					clickedExit = false;
					}
				}
			}

			Vec2 vector = new Vec2((float)(gravity_*Math.sin(-rotation_/(180/3.14f))), (float)(-gravity_*Math.cos(-rotation_/(180/3.14f))));        
			physics_.world_.setGravity(vector);
			graphics_.globalRot_ = rotation_;

			graphics_.score_ = physics_.transferredLiquid_;
			//rotation_ += 0.1f;
		// MENU
		} else if(state_ == STATE_MENU) {
			physics_.update();
			int x = Mouse.getX();
			int y = Mouse.getY();

			if (Mouse.isButtonDown(0)) {
				// Level 1
				if(x > 470 && x < 570 && y > 265 && y < 380) {
					physics_.init();
					graphics_.init();
					level1_ = new Level1();
					state_ = STATE_INGAME;
					graphics_.state_ = STATE_INGAME;
					graphics_.currentLevel_ = 0;
				}
				// Level 2
				if(x > 650 && x < 750 && y > 265 && y < 380) {
					System.out.println("Level2");
					physics_.init();
					graphics_.init();
					level2_ = new Level2();
					state_ = STATE_INGAME;
					graphics_.state_ = STATE_INGAME;
					
					graphics_.currentLevel_ = 1;
				}				
				// Level 3
				if(x > 470 && x < 570 && y < 200 && y > 85) {
					System.out.println("Level3");
					physics_.init();
					graphics_.init();
					level3_ = new Level3();
					state_ = STATE_INGAME;
					graphics_.state_ = STATE_INGAME;
					
					graphics_.currentLevel_ = 2;
				}
				// Level 4
				if(x > 650 && x < 750 && y < 200 && y > 85) {
					System.out.println("Level4");
					physics_.init();
					graphics_.init();
					level4_ = new Level4();
					state_ = STATE_INGAME;
					graphics_.state_ = STATE_INGAME;
					
					graphics_.currentLevel_ = 3;
				}	
			}
		// OUTGAME
		} else if(state_ == STATE_OUTGAME) {
			
		} else {
			// FFFUU
		}
		
       



	}

	private static void run() {
		while(!finished_) {
			if(Display.isCloseRequested()) {
				finished_ = true;		
			} else if(Display.isActive()) {
				logic();
				render();
				Display.sync(FRAMERATE);
			} else {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(Display.isVisible() || Display.isDirty()) {
					render();
				}
			}
			frame_++;
		} // while
	}

	private static void cleanup() {
		Display.destroy();
		System.out.println("Cleanup");
	}
}
