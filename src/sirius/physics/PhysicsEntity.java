package sirius.physics;

import org.jbox2d.collision.shapes.*;
import org.jbox2d.dynamics.*;

import sirius.Entity;
import sirius.Point;
import sirius.Shape;


public class PhysicsEntity {
	/*
	private BodyDef bodydef_;
	private MassData mass_;
	private PolygonShape shape_;
	*/
	private Entity entity_;
	private Physics physics_;
	private Shape shape_;
	
	public PhysicsEntity(Entity entity) {
		entity_ = entity;
	//	physics_ = physics;
	}
	
	public Entity getEntity() {
		return entity_;
	}
	
	public void setAsBox(float width, float height) {
		shape_ = new Shape(Shape.POLYGON);
		
		shape_.addPoint(new Point(-width,-height));
		shape_.addPoint(new Point( width,-height));
		shape_.addPoint(new Point( width, height));
		shape_.addPoint(new Point(-width, height));

/*		bodydef_ = new BodyDef();
		bodydef_.position.set(entity_.getX(), entity_.getY());
		
		shape_ = new PolygonShape();
		shape_.setAsBox(height, width);
		
		// Delayed creation (requires re-creation in physics init)
*/
	}
	public Shape getShape() {
		return shape_;
	}
	/*
	public setMass(float mass) {
		mass_.mass = mass;
		
	}*/
}
