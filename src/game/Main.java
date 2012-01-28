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
	
	public static Physics physics_;
	public static Input input_;
	public static Graphics graphics_;
	
	private static boolean finished_;
	private static Texture characters_;
	private static Texture tiles_;
	private static boolean flip_;
	private static int tile = 1;
	
	private static int frame_ = 0;
	private static Level level_;
	private static float gravity_ = 10.0f;
	private static float rotation_ = 0;
	private static boolean mouseDown_ = false;
	private static int mouseStartX = 0;
	private static int mouseStartY = 0;
	private static float mouseStartAngle = 0;
	
	
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
		level_ = new Level();
	}
	
	private static void render() {
		graphics_.renderEntities();
		Display.update();
	}
	
	private static float getAngle(float x, float y) {
		float dx = Mouse.getX() - 400; // FIXME!!111111
		float dy = Mouse.getY() - 300;
		float angle;
		
		if(dy >= 0) {
			if(dx >= 0) {
				angle = (float) Math.atan(dy/dx);
			}
			else {
				angle = (float) ((3.14f / 2.0f) + Math.atan(-dx/dy));
			}
		}
		else {
			if(dx >= 0) {
				angle = (float) (3.0f/4.0f*3.1415926535897932384626433832795028841f + Math.atan(-dy/dx));
			}
			else {
				angle = (float) (3.1415926535897932384626433832795028841f + Math.atan(dy/dx));
			}
		}
		
		return angle;
	}
	
	private static void logic() {
		physics_.update();
		if(input_.isDown(Input.KEY_RIGHT)) {
			rotation_--;
		}
		if(input_.isDown(Input.KEY_LEFT)) {
			rotation_++;
		}        
        //float dx = 400 - Mouse.getX();
        //float dy = 300 - Mouse.getY();
        
        if (Mouse.isButtonDown(0)) {
        	if(mouseDown_ == false) {
            	
        		mouseStartAngle = getAngle(Mouse.getX(), Mouse.getY());
        		mouseDown_ = true;
        	} else {
            	float mouseEndAngle;
            	mouseEndAngle = getAngle(Mouse.getX(), Mouse.getY());
            	/*
            	if(dx == 0) {
            		//dx = 1;
            		if(dy < 0) {
            			mouseEndAngle = mouseStartAngle + 3.14f; 
            		} else {
            			mouseEndAngle = mouseStartAngle - 3.14f; 
            		}

            	} else {
            		mouseEndAngle = (float) Math.atan(dy / dx);
            	}*/
            	//if(mouseStartAngle <)
            	//mouseEndAngle = (float) Math.atan(Math.abs(dy / dx));
            	//if(mouseStartAngle < 0 && mouseEndAngle > 0) {
            		//mouseEndAngle;
            	//}
            	/*if(dx < 0) {
            		mouseEndAngle = (float) Math.atan(dy / dx);
            	} else {
            		mouseEndAngle = (float) Math.atan(dy / dx);
            	}*/
        		
        		float drot = (float) ((mouseEndAngle - mouseStartAngle)*(180.0f/3.14f));
        		System.out.println("Drot: "+drot);
        		System.out.println("start: "+mouseStartAngle);
        		System.out.println("stop : "+mouseEndAngle);
        		
        		//mouseDown_ = false;
        		//rotation_ += drot;
        		
        	}

        	//boolean leftButtonDown = Mouse.isButtonDown(0);
        	//boolean rightButtonDown = Mouse.isButtonDown(1);
        	
        	
        	
        } else {
        	mouseDown_ = false;
        }
        
        Vec2 vector = new Vec2((float)(gravity_*Math.sin(-rotation_/(180/3.14f))), (float)(-gravity_*Math.cos(-rotation_/(180/3.14f))));        
        physics_.world_.setGravity(vector);
        graphics_.globalRot_ = rotation_;
                
        //rotation_ += 0.1f;

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
