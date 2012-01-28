package sirius.physics;

import java.util.Enumeration;
import java.util.Vector;

import org.jbox2d.collision.shapes.*;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import sirius.Entity;
import sirius.Point;
import sirius.Shape;


public class PhysicsEntity {
	public Body body_;
	public BodyDef bodydef_;
	public FixtureDef fixtureDef_;
	private MassData mass_;
	private PolygonShape polShape_;
	private CircleShape circleShape_;
	
	private Entity entity_;
	private Physics physics_;
	private Shape shape_;
	
	private int type_;
	
	public static int TYPE_STATIC = 0;
	public static int TYPE_DYNAMIC = 1;
	public static int TYPE_FLUID = 2;
	
	public PhysicsEntity(Entity entity, Physics physics) {
		entity_ = entity;
		physics_ = physics;
		bodydef_ = new BodyDef();
		
	}
	
	public Entity getEntity() {
		return entity_;
	}
	
	/*
	 * MAX 8 VERTICES!!!
	 */
	public void setPolygon(Vector<Point> points) {
		Vec2 vertices[] = new Vec2[points.size()];
		int i = 0;
		Enumeration<Point> e = points.elements();
		while(e.hasMoreElements()) {
			Point p = e.nextElement();
			vertices[i] = new Vec2(p.getX(),p.getY());
			i++;
		}
		polShape_ = new PolygonShape();
		polShape_.set(vertices, vertices.length);
		fixtureDef_ = new FixtureDef();
		fixtureDef_.shape = polShape_;
		fixtureDef_.density = 1.0f;
		fixtureDef_.friction = 0.5f;
		fixtureDef_.restitution = 0.0f;				
	}
	public void setCircle(float radius) {
		circleShape_ = new CircleShape();
		circleShape_.m_p.set(entity_.getX(), entity_.getY());
		circleShape_.m_radius = radius;
		fixtureDef_ = new FixtureDef();
		fixtureDef_.shape =circleShape_;
		fixtureDef_.density = 1.0f;
		fixtureDef_.friction = 0.5f;
		fixtureDef_.restitution = 0.0f;	
	}
	public void setBox(float width, float height, boolean dynamic) {
		shape_ = new Shape(Shape.POLYGON);
		
		shape_.addPoint(new Point(-width,-height));
		shape_.addPoint(new Point( width,-height));
		shape_.addPoint(new Point( width, height));
		shape_.addPoint(new Point(-width, height));
		
		bodydef_.position.set(entity_.getX(), entity_.getY());
		bodydef_.angle = entity_.getRotation()/(180/3.14f);
		if(dynamic) {
			bodydef_.type = BodyType.DYNAMIC;	
		} else {
			bodydef_.type = BodyType.STATIC;
		}
		
		polShape_ = new PolygonShape();
		polShape_.setAsBox(width,height);
	
		fixtureDef_ = new FixtureDef();
		fixtureDef_.shape = polShape_;
		fixtureDef_.density = 1.0f;
		fixtureDef_.friction = 0.5f;
		fixtureDef_.restitution = 0.0f;		
	}
	public void setFriction(float friction) {
		fixtureDef_.friction = friction;
	}
	public void setDensity(float density) {
		fixtureDef_.density = density;
	}
	public void setRestitution(float restitution) {
		fixtureDef_.restitution = restitution;
	}
	public void setType(int type) {
		type_ = type;
		if(type_ == TYPE_STATIC) {
			bodydef_.type = BodyType.STATIC;
		} else {
			bodydef_.type = BodyType.DYNAMIC;
		}
	}
	public Shape getShape() {
		return shape_;
	}
	
	public Body getBody() {
		return body_;
	}
	
	public void update() {
		entity_.setPosition(body_.getPosition().x, body_.getPosition().y);
		entity_.setRotation(body_.getAngle()*(180/3.14f));
	}
	public int getType() {
		return type_;
	}
	public void setVelocity(float dx, float dy) {
		body_.setLinearVelocity(new Vec2(dx,dy));
	}
	/*
	public setMass(float mass) {
		mass_.mass = mass;
		
	}*/
}
