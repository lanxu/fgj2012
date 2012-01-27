package sirius.graphics;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Vector;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.util.ResourceLoader;

public class Graphics {
	// Graphics Manager goes through a pipeline which has several steps
	// x. Define primitives (GraphicsEntities)
	//    - Entities might have several different steps within them.
	//      They are defined separately in Entities
	// 1. Send all vertex stuff to the graphics card (VBO)
	// 2. Send all graphics stuff to the graphics card (textures)
	// 3. Compile shaders
	// 4. Rendering
	//     - Vertex operations, Rasterization, Fragment operations... etc.
	// 5. Show result
	//
	// !!!This class should not have any window-management stuff!!!
	// It's done by the user in main
	
	private int width_;
	private int height_;
	private int frame_;
	private TrueTypeFont font_;
	private float aspectRatio_;
	
	private Vector<GraphicsEntity> entities_;
	
	public Graphics() {
		width_ = 800;
		height_ = 600;
		entities_ = new Vector<GraphicsEntity>();
	}

	public void init() {
		// Initialize OpenGL (using opengl 1.x stuff)
		glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
		
		// Enable OpenGL stuff
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_ALWAYS);
		
		/*
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		*/
		// Create a viewport
		glViewport(0, 0, width_, height_);
		
		// Setup projection matrix stuff
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
	
		float aspect = ((float)width_ / (float)height_);
		aspectRatio_ = aspect;
		glOrtho(-1.0f*aspect, 1.0f*aspect, -1.0f, 1.0f, -1.0f, 1.0f);
		glMatrixMode(GL_MODELVIEW);
				
		// load font from a .ttf file
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream("resources/graphics/Acme-Regular.ttf");
			
			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(32f); // set font size
			font_ = new TrueTypeFont(awtFont2, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("H; " + font_.getHeight() + " " + font_.getHeight("hello"));
		
		
	}
	
	public void addEntity(GraphicsEntity entity) {
		entities_.add(entity);
	}
	
	public Vector<GraphicsEntity> getEntities() {
		return entities_;
	}
	
	public void renderEntities() {
		glClear(GL_COLOR_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);
		glMatrixMode(GL_MODELVIEW);
		
		glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		glLoadIdentity();
	    glPushMatrix();
	    
	    TextureImpl.bindNone();
	    
	    // Slick has different coordinate system -> fix it
	    glTranslatef(-aspectRatio_+0.05f, 1.0f, 0.0f);
	    glScalef(0.004f, -0.004f, 0.004f);
	    font_.drawString(0.0f, 0.0f, "SCORE", Color.black);
	    font_.drawString(100.0f, 0.0f, ""+frame_, Color.red);
	    glColor3f(1.0f, 1.0f, 1.0f);
	    // Return
	    
	    glPopMatrix();
	    glPushMatrix();
	    glScalef(0.1f, 0.1f, 0.1f);
	    //glEnable(GL_DEPTH_TEST);
		Enumeration<GraphicsEntity> e = entities_.elements();
		while(e.hasMoreElements()) {
			GraphicsEntity ge = e.nextElement();
			
			Texture tex = ge.getTexture();

			//Quad quad(Point(), Point(), 
			//          Point(), Point());
			float x = ge.getEntity().getX();
			float y = ge.getEntity().getY();
			
			float sx = 0 / (float)tex.getImageWidth();
		    float sy = 16 / (float)tex.getImageHeight();
		    float tx = 16 / (float)tex.getImageWidth();
		    float ty = 16 / (float)tex.getImageHeight();
		 		    
		    glPushMatrix();
		    
		    tex.bind();
		    
		    glTranslatef(x, y, 0.0f);
		    glScalef(0.1f, 0.1f, 0.1f);
		    glRotatef(0.0f, 0.0f, 0.0f, 1.0f);
		    
			// Draw quad (entities can only be quads
		    glBegin(GL_QUADS);
		    glTexCoord2f(sx + ty, sy + ty);
		    glVertex2i(-1, -1);

		    glTexCoord2f(sx, sy + ty);
		    glVertex2i( 1, -1); 
		    
		    glTexCoord2f(sx, sy);
		    glVertex2i( 1,  1); 
		    
		    glTexCoord2f(sx + tx, sy);
		    glVertex2i(-1,  1); 
		    glEnd();
			
		    glPopMatrix();
		}
		glPopMatrix();
		frame_++;
	}	
}