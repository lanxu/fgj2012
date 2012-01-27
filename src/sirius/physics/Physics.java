package sirius.physics;
import java.util.Enumeration;
import java.util.Vector;

import org.jbox2d.collision.*;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;

import sirius.Entity;
import sirius.Shape;
import sirius.graphics.GraphicsEntity;


public class Physics {
	private Vec2 gravity_;
	private boolean doSleep_;
	private World world_;
	//private AABB worldAABB_;
	
	private Body test_;

	private Vector<PhysicsEntity> entities_;

	//private Camera m_camera;

	public Physics() {
		/*worldAABB_ = new AABB();
		worldAABB_.lowerBound.set(new Vec2((float) -100.0, (float) -100.0));  
		worldAABB_.upperBound.set(new Vec2((float)  100.0, (float)  100.0));*/  
		doSleep_ = true;
		gravity_ = new Vec2(0.0f, -10.0f);
		
		world_ = new World(gravity_, doSleep_);
		world_.setContinuousPhysics(true);
		entities_ = new Vector<PhysicsEntity>();
	}
	/*
public PhysicsEntity createEntity(Entity entity)
{
PhysicsEntity physEntity = new PhysicsEntity(entity);
entities_.add(physEntity);
return physEntity;
}
	 */   
	public void init() {
		Enumeration<PhysicsEntity> e = entities_.elements();
		while(e.hasMoreElements()) {
			PhysicsEntity pe = e.nextElement();
			Shape shape = pe.getShape();
			Entity entity = pe.getEntity();

			// Create
			BodyDef bodydef = new BodyDef();
			bodydef.type = BodyType.DYNAMIC;
			bodydef.position.set(entity.getX(), entity.getY());
			test_ = world_.createBody(bodydef);

//			if(shape.getShapeType() == Shape.POLYGON)
	//		{
				PolygonShape polShape = new PolygonShape();
				polShape.setAsBox(0.5f, 0.5f);

				FixtureDef fixtureDef = new FixtureDef();
				fixtureDef.shape = polShape;
				fixtureDef.density = 1.0f;
				fixtureDef.friction = 0.8f;
				//fixtureDef.restitution = 0.3f;
				
				test_.createFixture(fixtureDef);
		
				/*Enumeration<Point> pointe = shape.getPoints().elements();
				while(pointe.hasMoreElements()) {
					Point point = pointe.nextElement();
					
					Vec2 vector;
					vector.set(point.getX(), point.geyY());
				}*/
		//	}
		}
	}	   

	public void update() {
		float timeStep = 1.0f/60.0f;
		world_.step(timeStep, 6, 2);
		System.out.println(test_.getPosition() + " " + world_.getGravity());
		PhysicsEntity pe = entities_.firstElement();
		pe.getEntity().setPosition(test_.getPosition().x,
				                   test_.getPosition().y);
	}

	public void addEntity(PhysicsEntity entity) {
		entities_.add(entity);
	}
}
