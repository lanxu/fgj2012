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
	
	private static void logic() {
		physics_.update();
		if(input_.isDown(Input.KEY_LEFT)) {
			//System.out.println("JKE");
			//level_.objects_.get(0).getPhysicsEntity().setVelocity(1.0f, 0.0f);
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
