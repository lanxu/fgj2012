package game;

import java.util.HashSet;
import java.util.Vector;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.contacts.ContactSolver;

import sirius.Entity;
import sirius.graphics.GraphicsEntity;
import sirius.graphics.Material;
import sirius.physics.PhysicsEntity;

public class Level {
	
	public Vector<LevelObject> objects_;
	
	public Level() {
		objects_ = new Vector<LevelObject>();
		// Create the default setup
			
		// Walls 
		{

		Entity entity = new Entity();
		entity.setPosition(-0.5f, -1.0f);
		entity.setRotation(0);
		
		GraphicsEntity gEntity = new GraphicsEntity(entity);
		gEntity.setMaterial(new Material(1.0f,0.0f,0.0f));
		//gEntity.setBox(0.1f,0.1f);
		gEntity.setCircle(0.3f, 1.0f, 1.0f);
		Main.graphics_.addEntity(gEntity);
		
		PhysicsEntity pEntity = new PhysicsEntity(entity, Main.physics_);
		//pEntity.setBox(0.1f, 0.1f, true);
		pEntity.setCircle(0.3f); //(0.1f, 0.1f, true);
		//pEntity.setPolygon(gEntity.getPolygonShape().getPoints());
		pEntity.setType(PhysicsEntity.TYPE_SENSOR);
		Main.physics_.addEntity(pEntity);
				
		LevelObject side = new LevelObject(entity);
		side.setGraphics(gEntity);
		side.setPhysicsEntity(pEntity);
		
		objects_.add(side);
		}
	
		/*
		{
		Entity entity = new Entity();
		entity.setPosition(0.0f, -0.5f);
		entity.setRotation(0);
		
		GraphicsEntity gEntity = new GraphicsEntity(entity);
		gEntity.setMaterial(new Material(1.0f,0.0f,0.0f));
		gEntity.setBox(1.0f, 0.1f);
		Main.graphics_.addEntity(gEntity);
		
		PhysicsEntity pEntity = new PhysicsEntity(entity, Main.physics_);
		pEntity.setBox(1.0f, 0.1f, false);
		Main.physics_.addEntity(pEntity);
		
		LevelObject side = new LevelObject(entity);
		side.setGraphics(gEntity);
		side.setPhysicsEntity(pEntity);
		
		objects_.add(side);
		}
		*/
		{
		Entity entity = new Entity();
		entity.setPosition(3.0f, 3.0f);
		entity.setRotation(45);
		
		GraphicsEntity gEntity = new GraphicsEntity(entity);
		gEntity.setMaterial(new Material(1.0f,0.0f,0.0f));
		gEntity.setBox(4.5f, 0.3f);
		Main.graphics_.addEntity(gEntity);
		
		PhysicsEntity pEntity = new PhysicsEntity(entity, Main.physics_);
		pEntity.setBox(4.5f, 0.3f, false);
		Main.physics_.addEntity(pEntity);
		
		LevelObject side = new LevelObject(entity);
		side.setGraphics(gEntity);
		side.setPhysicsEntity(pEntity);
		
		objects_.add(side);
		}
		//
		{
		Entity entity = new Entity();
		entity.setPosition(-4.0f, 3.0f);
		entity.setRotation(-45);
		
		GraphicsEntity gEntity = new GraphicsEntity(entity);
		gEntity.setMaterial(new Material(1.0f,0.0f,0.0f));
		gEntity.setBox(4.5f, 0.3f);
		Main.graphics_.addEntity(gEntity);
		
		PhysicsEntity pEntity = new PhysicsEntity(entity, Main.physics_);
		pEntity.setBox(4.5f, 0.3f, false);
		Main.physics_.addEntity(pEntity);
		
		LevelObject side = new LevelObject(entity);
		side.setGraphics(gEntity);
		side.setPhysicsEntity(pEntity);
		
		objects_.add(side);
		}
		
		//
		{
		Entity entity = new Entity();
		entity.setPosition(-10.0f, 3.0f);
		entity.setRotation(90);
		
		GraphicsEntity gEntity = new GraphicsEntity(entity);
		gEntity.setMaterial(new Material(1.0f,0.0f,0.0f));
		gEntity.setBox(20.f, 0.3f);
		Main.graphics_.addEntity(gEntity);
		
		PhysicsEntity pEntity = new PhysicsEntity(entity, Main.physics_);
		pEntity.setBox(20.0f, 0.3f, false);
		Main.physics_.addEntity(pEntity);
		
		LevelObject side = new LevelObject(entity);
		side.setGraphics(gEntity);
		side.setPhysicsEntity(pEntity);
		
		objects_.add(side);
		}
		//
		{
		Entity entity = new Entity();
		entity.setPosition(10.0f, 3.0f);
		entity.setRotation(90);
		
		GraphicsEntity gEntity = new GraphicsEntity(entity);
		gEntity.setMaterial(new Material(1.0f,0.0f,0.0f));
		gEntity.setBox(20.0f, 0.3f);
		Main.graphics_.addEntity(gEntity);
		
		PhysicsEntity pEntity = new PhysicsEntity(entity, Main.physics_);
		pEntity.setBox(20.0f, 0.3f, false);
		Main.physics_.addEntity(pEntity);
		
		LevelObject side = new LevelObject(entity);
		side.setGraphics(gEntity);
		side.setPhysicsEntity(pEntity);
		
		objects_.add(side);
		}
	
		//
		{
		Entity entity = new Entity();
		entity.setPosition(0.0f, -10.0f);
		entity.setRotation(0);
		
		GraphicsEntity gEntity = new GraphicsEntity(entity);
		gEntity.setMaterial(new Material(1.0f,0.0f,0.0f));
		gEntity.setBox(10.0f, 0.3f);
		Main.graphics_.addEntity(gEntity);
		
		PhysicsEntity pEntity = new PhysicsEntity(entity, Main.physics_);
		pEntity.setBox(10.0f, 0.3f, false);
		Main.physics_.addEntity(pEntity);
		
		LevelObject side = new LevelObject(entity);
		side.setGraphics(gEntity);
		side.setPhysicsEntity(pEntity);
		
		objects_.add(side);
		}	
		
		
		
		/*
		 * 
		
		**
		*
		*
		
		 */
		
		
		
		
		{
		Entity entity = new Entity();
		entity.setPosition(15.3f, 0.0f);
		entity.setRotation(90);
		/*
		GraphicsEntity gEntity = new GraphicsEntity(entity);
		gEntity.setMaterial(new Material(1.0f,0.0f,0.0f));
		gEntity.setBox(7.0f, 0.1f);
		Main.graphics_.addEntity(gEntity);
		*/
		PhysicsEntity pEntity = new PhysicsEntity(entity, Main.physics_);
		pEntity.setBox(7.0f, 0.1f, false);
		Main.physics_.addEntity(pEntity);
		
		LevelObject side = new LevelObject(entity);
		//side.setGraphics(gEntity);
		side.setPhysicsEntity(pEntity);
		
		objects_.add(side);
		}
		{
		Entity entity = new Entity();
		entity.setPosition(16.7f, 0.0f);
		entity.setRotation(90);
		/*
		GraphicsEntity gEntity = new GraphicsEntity(entity);
		gEntity.setMaterial(new Material(1.0f,0.0f,0.0f));
		gEntity.setBox(7.0f, 0.1f);
		Main.graphics_.addEntity(gEntity);
		*/
		PhysicsEntity pEntity = new PhysicsEntity(entity, Main.physics_);
		pEntity.setBox(7.0f, 0.1f, false);
		Main.physics_.addEntity(pEntity);
		
		LevelObject side = new LevelObject(entity);
	//	side.setGraphics(gEntity);
		side.setPhysicsEntity(pEntity);
		
		objects_.add(side);
		}		
		{
		Entity entity = new Entity();
		entity.setPosition(16.0f, 7.0f);
		entity.setRotation(0);
		/*
		GraphicsEntity gEntity = new GraphicsEntity(entity);
		gEntity.setMaterial(new Material(1.0f,0.0f,0.0f));
		gEntity.setBox(1.0f, 0.1f);
		Main.graphics_.addEntity(gEntity);
		*/
		PhysicsEntity pEntity = new PhysicsEntity(entity, Main.physics_);
		pEntity.setBox(3.0f, 0.1f, false);
		Main.physics_.addEntity(pEntity);
		
		LevelObject side = new LevelObject(entity);
		//side.setGraphics(gEntity);
		side.setPhysicsEntity(pEntity);
		
		objects_.add(side);
		}			
		{
		Entity entity = new Entity();
		entity.setPosition(16.0f, -7.0f);
		entity.setRotation(0);
		
		/*GraphicsEntity gEntity = new GraphicsEntity(entity);
		gEntity.setMaterial(new Material(1.0f,0.0f,0.0f));
		gEntity.setBox(1.0f, 0.1f);
		Main.graphics_.addEntity(gEntity);
		*/
		PhysicsEntity pEntity = new PhysicsEntity(entity, Main.physics_);
		pEntity.setBox(3.0f, 0.1f, false);
		Main.physics_.addEntity(pEntity);
		
		LevelObject side = new LevelObject(entity);
		//side.setGraphics(gEntity);
		side.setPhysicsEntity(pEntity);
		
		
		
		
		objects_.add(side);
		}			
		{
		Entity entity = new Entity();
		entity.setPosition(17.2f, 0.0f);
		entity.setRotation(0);
		
		GraphicsEntity gEntity = new GraphicsEntity(entity);
		gEntity.setMaterial(new Material("resources/bottle.png"));
		gEntity.setBox(2.5f, 8.0f);
		Main.graphics_.addEntity(gEntity);
				
		LevelObject side = new LevelObject(entity);
		side.setGraphics(gEntity);
		
		objects_.add(side);
		}			
	}
	void logic()
	{
		
		
		
	}
}
