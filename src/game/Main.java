/* Quick game
*
* @author lanxu <jukka.lankinen@gmail.com>
* @version 1.0
*/

package game;

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
	
	private static Physics physics_;
	private static Input input_;
	private static Graphics graphics_;
	
	private static boolean finished_;
	private static Texture characters_;
	private static Texture tiles_;
	private static boolean flip_;
	private static int tile = 1;
	
	private static int frame_ = 0;
	
	
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
		physics_ = new Physics();
		input_ = new Input();
		
		// Init managers
		graphics_.init();
/*
		entity_ = new Entity();
		entity_.setPosition(0.5f, 0.5f);
		
		GraphicsEntity gentity = new GraphicsEntity(entity_);
		TextureLoader textureLoader = new TextureLoader();
		Texture tex = textureLoader.getTexture("resources/graphics/game.png");
		
		gentity.addTexture(tex);
		graphics_.addEntity(gentity);
		
		PhysicsEntity pentity = new PhysicsEntity(entity_);
		pentity.setAsBox(0.3f, 0.3f);
		physics_.addEntity(pentity);
		physics_.init();
*/
	}
	
	private static void render() {
		graphics_.renderEntities();
		Display.update();
	}
	
	private static void logic() {
		physics_.update();
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
