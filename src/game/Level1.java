package game;

import java.util.Vector;

import sirius.Entity;
import sirius.graphics.GraphicsEntity;
import sirius.graphics.Material;
import sirius.physics.PhysicsEntity;

public class Level1 {
	public Vector<LevelObject> objects_;
	
	public Level1() {
		objects_ = new Vector<LevelObject>();
		
		createBottle(0,-2.5f);
		//createTrap(-10.0f, 0.0f, 0.0f, 1.0f);
		
		// walls
		createBox(-19.0f, -22.5f, 38.0f, 1.0f, 0);	
		createBox(-19.0f, +22.5f, 38.0f, 1.0f, 0);	
		createBox(-19.0f, +22.5f, 45.0f, 1.0f, -90);	
		createBox( 19.0f, +22.5f, 45.0f, 1.0f, -90);
		
		// bottom cup/bottle
		createBox(-4.0f,-20.5f, 5.0f, 1.0f, 90);
		createBox( 4.0f,-20.5f, 5.0f, 1.0f, 90);
		createBox(-4.0f,-20.5f, 8.0f, 1.0f, 0);
		
		Main.physics_.addLiquid(0, -19.0f, 500);
		
		// Bottom row
		createBox(-15.0f, -7.5f, 5.0f, 1.0f, -45);
		createBox(-15.0f, -7.5f, 5.0f, 1.0f, -90-45);

		createBox(-5.0f, -7.5f, 5.0f, 1.0f, -45);
		createBox(-5.0f, -7.5f, 5.0f, 1.0f, -90-45);

		createBox(15.0f, -7.5f, 5.0f, 1.0f, -45);
		createBox(15.0f, -7.5f, 5.0f, 1.0f, -90-45);

		createBox(5.0f, -7.5f, 5.0f, 1.0f, -45);
		createBox(5.0f, -7.5f, 5.0f, 1.0f, -90-45);
		createBox(-2f, -11.0f, 4.0f, 1.0f, 0);
		
		// Middle row
		createBox(10.0f,  2.5f, 5.0f, 1.0f, -45);
		createBox(10.0f,  2.5f, 5.0f, 1.0f, -90-45);
		
		createBox(-10.0f, 2.5f, 5.0f, 1.0f, -45);
		createBox(-10.0f, 2.5f, 5.0f, 1.0f, -90-45);
		
		createBox(0.0f, 7.5f, 7.0f, 1.0f, -45);
		createBox(0.0f, 7.5f, 7.0f, 1.0f, -90-45);		
		// Bottom row
		createBox(7.5f, 19.5f, 7.0f, 1.0f, -45);
		createBox(7.5f, 19.5f, 7.0f, 1.0f, -90-45);
		
		createBox(-7.5f,19.5f, 7.0f, 1.0f, -45);
		createBox(-7.5f,19.5f, 7.0f, 1.0f, -90-45);
		

		// Bottom box (goal)
		createBox(-5.0f,22.5f, 5.0f, 1.0f, -90);
		createBox( 5.0f,22.5f, 5.0f, 1.0f, -90);
		
		// Goal
		
		// Graphics
		Entity entity;
		GraphicsEntity gEntity;
		PhysicsEntity pEntity;
		entity = new Entity();
		entity.setPosition(0.0f, 19.0f);
		entity.setRotation(0);
		entity.setId(Main.ID_GOAL);
		
		gEntity = new GraphicsEntity(entity);
		gEntity.setMaterial(new Material("resources/target.png"));
		gEntity.setBox(3.0f, 3.0f);
		Main.graphics_.addEntity(gEntity);
		
		pEntity = new PhysicsEntity(entity, Main.physics_);
		pEntity.setBox(3.0f, 3.0f, false);
		pEntity.setType(PhysicsEntity.TYPE_SENSOR);
		Main.physics_.addEntity(pEntity);	
		
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
	private void createBox2(float sx, float sy, float length, float thickness, float angle) {
		Entity entity;
		GraphicsEntity gEntity;
		PhysicsEntity pEntity;
		float desx;
		float desy;
		
		
		desx = sx + (length / 2.0f);
		//float desy = (float) (sy + (Math.sin(angle / 3.14f * 180.0f) * length));
		//length = (float) Math.sqrt(Math.pow(desy-sy,2) + Math.pow(length, 2));
		length = (float) (length / Math.cos(angle / 180.0f * 3.1415f));
		desy = (float) Math.sin(angle / 180.0f * 3.1415f) * length / 2.0f;
		
		System.out.println("X: "+ desx + " Y: " + desy + " L: " + length);
		
		sx = desx;
		sy = desy;
		
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
	private void createBottle(float sx, float sy) {
			Entity entity;
			GraphicsEntity gEntity;
			PhysicsEntity pEntity;
			
			// Right
			entity = new Entity();
			entity.setPosition(sx+1.0f, sy+0.0f);
			entity.setRotation(90);
			
			/*gEntity = new GraphicsEntity(entity);
			gEntity.setMaterial(new Material(1.0f,0.0f,0.0f));
			gEntity.setBox(7.0f, 0.1f);
			Main.graphics_.addEntity(gEntity);*/
			
			pEntity = new PhysicsEntity(entity, Main.physics_);
			pEntity.setBox(7.0f, 0.1f, false);
			Main.physics_.addEntity(pEntity);
			
			// Left			
			entity = new Entity();
			entity.setPosition(sx-1.0f, sy+0.0f);
			entity.setRotation(90);
			
			/*gEntity = new GraphicsEntity(entity);
			gEntity.setMaterial(new Material(1.0f,0.0f,0.0f));
			gEntity.setBox(7.0f, 0.1f);
			Main.graphics_.addEntity(gEntity);*/
			
			pEntity = new PhysicsEntity(entity, Main.physics_);
			pEntity.setBox(7.0f, 0.1f, false);
			Main.physics_.addEntity(pEntity);

			// Top
			entity = new Entity();
			entity.setPosition(sx+0.0f, sy+7.0f);
			entity.setRotation(0);
			/*gEntity = new GraphicsEntity(entity);
			gEntity.setMaterial(new Material(1.0f,0.0f,0.0f));
			gEntity.setBox(1.0f, 0.1f);
			Main.graphics_.addEntity(gEntity);*/
			pEntity = new PhysicsEntity(entity, Main.physics_);
			pEntity.setBox(1.0f, 0.1f, false);
			Main.physics_.addEntity(pEntity);
		
			// Bottom
			entity = new Entity();
			entity.setPosition(sx-0.0f, sy-7.0f);
			entity.setRotation(0);
			
		/*	gEntity = new GraphicsEntity(entity);
			gEntity.setMaterial(new Material(1.0f,0.0f,0.0f));
			gEntity.setBox(1.0f, 0.1f);
			Main.graphics_.addEntity(gEntity);
			*/
			pEntity = new PhysicsEntity(entity, Main.physics_);
			pEntity.setBox(1.0f, 0.1f, false);
			Main.physics_.addEntity(pEntity);

			// Graphics
			entity = new Entity();
			entity.setPosition(sx+1.2f, sy+0.0f);
			entity.setRotation(0);
			
			gEntity = new GraphicsEntity(entity);
			gEntity.setMaterial(new Material("resources/bottle.png"));
			gEntity.setBox(2.5f, 8.0f);
			Main.graphics_.addEntity(gEntity);

	}
	private void createTrap(float sx, float sy, float rot, float size) {
		Entity entity;
		GraphicsEntity gEntity;
		PhysicsEntity pEntity;
		
		// left
		entity = new Entity();
		entity.setPosition(sx-3.5f, sy-0.0f);
		entity.setRotation(45);
		
		gEntity = new GraphicsEntity(entity);
		gEntity.setMaterial(new Material(1.0f, 0.0f, 0.0f));
		gEntity.setBox(5.0f, 0.3f);
		Main.graphics_.addEntity(gEntity);
		
		pEntity = new PhysicsEntity(entity, Main.physics_);
		pEntity.setBox(5.0f, 0.3f, false);
		Main.physics_.addEntity(pEntity);
		
		// right
		entity = new Entity();
		entity.setPosition(sx+3.5f, sy-0.0f);
		entity.setRotation(-45);
		
		gEntity = new GraphicsEntity(entity);
		gEntity.setMaterial(new Material(1.0f, 0.0f, 0.0f));
		gEntity.setBox(5.0f, 0.3f);
		Main.graphics_.addEntity(gEntity);
		
		pEntity = new PhysicsEntity(entity, Main.physics_);
		pEntity.setBox(5.0f, 0.3f, false);
		Main.physics_.addEntity(pEntity);		
	}
	
}
