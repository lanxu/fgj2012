package game;

import sirius.Entity;
import sirius.graphics.GraphicsEntity;
import sirius.graphics.Material;
import sirius.physics.PhysicsEntity;

public class Menu {
	Menu() {
		Entity entity;
		GraphicsEntity gEntity;
		PhysicsEntity pEntity;
		// Graphics
		entity = new Entity();
		entity.setPosition(15.0f, 13.0f);
		entity.setRotation(0);
		
		gEntity = new GraphicsEntity(entity);
		gEntity.setMaterial(new Material("resources/title.png"));
		gEntity.setBox(12.0f, -10.0f);
		
		Main.graphics_.addEntity(gEntity);
		
		drawButton(10,-2,"resources/level1.png");
		drawButton(25,-2,"");
		drawButton(10,-17,"");
		drawButton(25,-17,"");
		
		createBox(-31.0f,30.0f,60.0f, 3.0f, -90.0f);
		
		createBox(-22.0f,20.0f,12.0f, 1.0f, -45.0f);
		createBox(-11.0f,10.0f,7.0f, 1.0f, 45.0f);
		
		createBox(-25.0f, 0.0f, 8.0f, 1.0f, 45.0f);
		createBox(-8.0f, -10.0f, 11.0f, 1.0f, 45.0f);
		
		createBox(-25.0f, -11f, 11.0f, 1.0f, 90.0f);
		createBox(-15.0f, 0.0f, 20.0f, 1.0f, -90.0f);
		
		Main.physics_.addLiquid(-25.0f, 30, 100);
		Main.physics_.addLiquid(-20.0f, 30, 100);
		Main.physics_.addLiquid(-15.0f, 30, 100);
		Main.physics_.addLiquid(-10.0f, 30, 100);
		Main.physics_.addLiquid(-5.0f, 30, 100);
	
	}
	private void createBox(float sx, float sy, float length, float thickness, float angle) {
		Entity entity;
		GraphicsEntity gEntity;
		PhysicsEntity pEntity;
		
		
		float desx = (float) (sx + Math.cos(angle / 180.0f * 3.1415f)*(length/2.0f));
		float desy = (float) (sy + Math.sin(angle / 180.0f * 3.1415f)*(length/2.0f));
		//float desy = (float) (sy + (Math.sin(angle / 3.14f * 180.0f) * length));
		
		//length = (float) Math.sqrt(Math.pow(desy-sy,2) + Math.pow(length / 2.0f, 2));
		
		System.out.println("X: "+ desx + " Y: " + desy + " L: " + length);
		
		sx += desx-sx;
		sy += desy-sy;
		
		// left
		entity = new Entity();
		entity.setPosition(sx, sy);
		entity.setRotation(angle);
		
		gEntity = new GraphicsEntity(entity);
		gEntity.setMaterial(new Material(1.0f, 0.0f, 0.0f));
		gEntity.setBox(length/2.0f, thickness/2.0f);
		Main.graphics_.addEntity(gEntity);
		
		pEntity = new PhysicsEntity(entity, Main.physics_);
		pEntity.setBox(length/2.0f, thickness/2.0f, false);
		Main.physics_.addEntity(pEntity);

	}	
	void drawButton(float x, float y, String graphics) {
		Entity entity;
		GraphicsEntity gEntity;
		PhysicsEntity pEntity;
		
		entity = new Entity();
		entity.setPosition(x, y);
		entity.setRotation(0);
		
		gEntity = new GraphicsEntity(entity);
		gEntity.setMaterial(new Material("resources/button_level.png"));
		gEntity.setBox(5.0f, -10.0f);
		
		Main.graphics_.addEntity(gEntity);
		
		if(!graphics.equals("")) {
			entity = new Entity();
			entity.setPosition(x, y);
			entity.setRotation(0);
			
			gEntity = new GraphicsEntity(entity);
			gEntity.setMaterial(new Material(graphics));
			gEntity.setBox(5.0f, -10.0f);
			
			Main.graphics_.addEntity(gEntity);				
		}

	}
}
