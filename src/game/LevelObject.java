package game;

import sirius.Entity;
import sirius.graphics.GraphicsEntity;
import sirius.physics.PhysicsEntity;

public class LevelObject {
	private Entity entity_;
	private GraphicsEntity graphicsEntity_;
	private PhysicsEntity physicsEntity_;
	public LevelObject(Entity entity) {
		entity_ = entity;
	}
	public LevelObject(Entity entity, GraphicsEntity gentity) {
		entity_ = entity;
		graphicsEntity_ = gentity;
	}
	public void setGraphics(GraphicsEntity entity) {
		graphicsEntity_ = entity;
	}
	public void setPhysicsEntity(PhysicsEntity entity) {
		physicsEntity_ = entity;
	}
	
	public Entity getEntity() {
		return entity_;
	}
	
	public GraphicsEntity getGraphicsEntity() {
		return graphicsEntity_;
	}
	public PhysicsEntity getPhysicsEntity() {
		return physicsEntity_;
	}
}
