package sirius.graphics;

import static org.lwjgl.opengl.GL11.*;

import game.Main;

import java.awt.Font;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Vector;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.util.ResourceLoader;

import sirius.Entity;
import sirius.Point;
import sirius.Shape;
import sirius.physics.PhysicsEntity;

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
	public float globalRot_;
	public float globalScale_;
	private TrueTypeFont font_;
	private TrueTypeFont font2_;
	private TrueTypeFont font3_;
	private float aspectRatio_;
	private Texture button_;
	public int score_;
	public int score1_;
	public int score2_;
	public int score3_;
	public int score4_;
	public int currentLevel_;
	public int state_;
	
	private Vector<GraphicsEntity> entities_;
	
	public Graphics() {
		width_ = 800;
		height_ = 600;
		entities_ = new Vector<GraphicsEntity>();
		globalRot_ = 0;
		globalScale_ = 0.04f;
		score_ = 0;
		score1_ = 0;
		score2_ = 0;
		score3_ = 0;
		score4_ = 0;
		currentLevel_ = 0;
		state_ = 0;
		
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
		glOrtho(-1.0f*aspect, 1.0f*aspect, -1.0f, 1.0f, -10.0f, 10.0f);
		glMatrixMode(GL_MODELVIEW);
		
	}

	public void init() {
		// load font from a .ttf file
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream("resources/Fresca-Regular.ttf");
			
			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(32f); // set font size
			font_ = new TrueTypeFont(awtFont2, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream("resources/Fresca-Regular.ttf");
			
			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(28f); // set font size
			font2_ = new TrueTypeFont(awtFont2, true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream("resources/Fresca-Regular.ttf");
			
			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(16f); // set font size
			font3_ = new TrueTypeFont(awtFont2, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("H; " + font_.getHeight() + " " + font_.getHeight("hello"));
	
		entities_.clear();
		globalRot_ = 0;
		globalScale_ = 0.04f;
		score_ = 0;
		
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
	    

	    if(state_ == Main.STATE_MENU) {
		    font_.drawString(425.0f, 130.0f, "SELECT LEVEL", Color.black);
		    font2_.drawString(380.0f, 288.0f, " "+score1_+" / 500", Color.black);
		    font2_.drawString(530.0f, 288.0f, " "+score2_+" / 500", Color.black);
		    font2_.drawString(380.0f, 440.0f, " "+score3_+" / 500", Color.black);
		    font2_.drawString(530.0f, 440.0f, " "+score4_+" / 500", Color.black);
	    } else if(state_ == Main.STATE_INGAME) {
	    	font3_.drawString(540.0f, 400.0f, "TO NEXT LEVEL", Color.black);
	    	//score_ = 500;
	    	Color color;
	    	if(score_ < 150) {
	    		color = Color.red;
	    	} else if(score_ > 150 && score_ < 300) {
	    		color = Color.orange;
	    	} else {
	    		color = Color.green;
	    		
	    		font_.drawString(550.0f, 450.0f, "NEXT", color);
	    	}
	    	
	    	font_.drawString(565.0f, 420.0f, " / 300", Color.black);
	    	font_.drawString(510.0f, 420.0f, " "+score_, color);
	    	font_.drawString(8.0f, 450.0f, "EXIT", color);
	    	
	    	if(currentLevel_ == 0) { if(score1_ < score_) score1_=score_; }
	    	if(currentLevel_ == 1) { if(score2_ < score_) score2_=score_; }
	    	if(currentLevel_ == 2) { if(score3_ < score_) score3_=score_; }
	    	if(currentLevel_ == 3) { if(score4_ < score_) score4_=score_; }
	    	
	    	
	    } else {
	    	font_.drawString(0.0f, 0.0f, "DEBUG", Color.black);
	    }
	    
	    
	    glColor3f(1.0f, 1.0f, 1.0f);
	    // Return
	    TextureImpl.unbind();
	    
	    glPopMatrix();
	    glPushMatrix();
	    
	    glScalef(globalScale_, globalScale_, globalScale_);
	    glRotatef(globalRot_, 0, 0, 1);
	    
	    glEnable(GL_DEPTH_TEST);
		Enumeration<GraphicsEntity> e = entities_.elements();
		while(e.hasMoreElements()) {
			GraphicsEntity gEntity = e.nextElement();
		
			Material m = gEntity.getMaterial();
			Texture tex = m.getTexture();
			
			if(m.isSolid()) {
				
				//TextureImpl.bindNone();
				glDisable(GL_TEXTURE_2D);
				glPushMatrix();
				float r = m.getRed();
				float g = m.getGreen();
				float b = m.getBlue();
				glColor3f(r,g,b);

				Shape shape = gEntity.getPolygonShape();
				if(shape.getShapeType() == Shape.POLYGON) {
					float x = gEntity.getEntity().getX();
					float y = gEntity.getEntity().getY();
					float rot = gEntity.getEntity().getRotation();

					glTranslatef(x, y, 0.0f);
					glRotatef(rot, 0.0f, 0.0f, 1.0f);

					glBegin(GL_POLYGON);
					Vector<Point> points = shape.getPoints();
					Enumeration<Point> pEnum = points.elements();
					while(pEnum.hasMoreElements()) {
						Point point = pEnum.nextElement();

						glVertex2f(point.getX(), point.getY());

					}
					glEnd();

				}
				glColor3f(1.0f, 1.0f, 1.0f);
				glPopMatrix();
				//TextureImpl.unbind();
				glEnable(GL_TEXTURE_2D);
				
			}
			if(m.isTextured()) {
				tex.bind();

				Shape shape = gEntity.getPolygonShape();
				Shape texShape = gEntity.getTextureShape();
				if(shape.getShapeType() == Shape.POLYGON) {
					float x = gEntity.getEntity().getX();
					float y = gEntity.getEntity().getY();
					float rot = gEntity.getEntity().getRotation();
					
					glPushMatrix();
				    	glTranslatef(x, y, 0.0f);
				    	if(gEntity.getEntity().getId() == Main.ID_GOAL) {
				    		rot = (frame_ % 360)*4;
				    	}
				    	glRotatef(rot, 0.0f, 0.0f, 1.0f);
				    	
				    	glBegin(GL_POLYGON);
				    	Vector<Point> points = shape.getPoints();
				    	Vector<Point> tpoints = texShape.getPoints();
				    	Enumeration<Point> pEnum = points.elements();
				    	int i = 0;
						while(pEnum.hasMoreElements()) {
							Point point = pEnum.nextElement();
							glTexCoord2f(tpoints.get(i).getX(), tpoints.get(i).getY());
							glVertex2f(point.getX(), point.getY());
							i++;
							
						}
				    	glEnd();
				    glPopMatrix();
				}
				
				
			}
			
			
			/*float x = ge.getEntity().getX();
			float y = ge.getEntity().getY();
			
			float sx = 0 / (float)tex.getImageWidth();
		    float sy = 16 / (float)tex.getImageHeight();
		    float tx = 16 / (float)tex.getImageWidth();
		    float ty = 16 / (float)tex.getImageHeight();
		 		*/    
		    
		    /*
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
			
		    glPopMatrix();*/
		}
		glPopMatrix();
		frame_++;
	}
	public void removeEntity(Entity entity) {
		Enumeration<GraphicsEntity> e = entities_.elements();
		int index = 0;
		while(e.hasMoreElements()) {
			GraphicsEntity pEntity = e.nextElement();
			if(pEntity.getEntity() == entity) {
				entities_.remove(index);
				return;
			}
			index++;
		}
	}
}
