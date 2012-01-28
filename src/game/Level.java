package game;

import java.util.Vector;

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
		entity.setPosition(0, 0);
		entity.setRotation(0);
		
		GraphicsEntity gEntity = new GraphicsEntity(entity);
		gEntity.setMaterial(new Material(0.1f,0.0f,0.0f));
		//gEntity.setBox(0.1f,0.1f);
		gEntity.setCircle(0.1f, 1.0f, 1.0f);
		Main.graphics_.addEntity(gEntity);
		
		PhysicsEntity pEntity = new PhysicsEntity(entity, Main.physics_);
		//pEntity.setBox(0.1f, 0.1f, true);
		pEntity.setCircle(0.1f); //(0.1f, 0.1f, true);
		//pEntity.setPolygon(gEntity.getPolygonShape().getPoints());
		pEntity.setType(PhysicsEntity.TYPE_DYNAMIC);
		Main.physics_.addEntity(pEntity);
		LevelObject side = new LevelObject(entity);
		side.setGraphics(gEntity);
		side.setPhysicsEntity(pEntity);
		
		objects_.add(side);
		}
		// 
		/*{
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
		}*/	
		//
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
		entity.setPosition(0.0f, -15.0f);
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
	}
}
