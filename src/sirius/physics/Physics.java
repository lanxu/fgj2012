package sirius.physics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Vector;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.*;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.MassData;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.Contact;

import sirius.Entity;
import sirius.Shape;
import sirius.graphics.Graphics;
import sirius.graphics.GraphicsEntity;
import sirius.graphics.Material;


public class Physics {
	private Vec2 gravity_;
	private boolean doSleep_;
	private Graphics graphics_;
	public World world_;
	private Material mat;
	private Vector<PhysicsEntity> entities_;

	// Liquid Physics
	private int nParticles = 500;
	private int curParticle = 0;
	private float totalMass = 10.0f;
	private float boxWidth = 4.0f;
	private float boxHeight = 20.0f;
	
	private float fluidMinX = -40.0f;
	private float fluidMaxX = 20.0f;
	private float fluidMinY = -25.0f;
	private float fluidMaxY = 25.0f;

	private float rad = 0.6f;
	private float visc = 0.006f;//0.005f;
	
	private ArrayList<Integer>[][] hash;
	private int hashWidth,hashHeight;
	public int transferredLiquid_ = 0;
	
	private Body[] liquid_;
	// Functions
	private int hashX(float x) {
		float f = MathUtils.map(x, fluidMinX, fluidMaxX, 0, hashWidth-.001f);
		return (int)f;
	}
	
	private int hashY(float y) {
		float f = MathUtils.map(y,fluidMinY,fluidMaxY,0,hashHeight-.001f);
		return (int)f;
	}

	private void initLiquid() {
	    hash = new ArrayList[80][80];
	    for (int i=0; i<80; ++i) {
	    	for (int j=0; j<80; ++j) {
	    		hash[i][j] = new ArrayList<Integer>();
	    	}
	    }
	    hashWidth = 80;
	    hashHeight = 80;
	}
	private void hashLocations() {
		for(int a = 0; a < hashWidth; a++)
        {
            for(int b = 0; b < hashHeight; b++){
            	hash[a][b].clear();
            }
        }

        for(int a = 0; a < curParticle; a++)
        {
			if(liquid_[a].m_userData != null)
			{
            int hcell = hashX(liquid_[a].m_sweep.c.x);
            int vcell = hashY(liquid_[a].m_sweep.c.y);
            if(hcell > -1 && hcell < hashWidth && vcell > -1 && vcell < hashHeight)
                hash[hcell][vcell].add(new Integer(a));
			}
        }
	}
	private void dampenLiquid() {
		for (int i=0; i< curParticle; ++i) {
			if(liquid_[i].m_userData != null)
			{
			Body b = liquid_[i];
			b.setLinearVelocity(b.getLinearVelocity().mul(0.99f));
			}
		}
	}
	private void checkBounds() {
		for (int i=0; i<curParticle; ++i) {
			//if (liquid_[i].getWorldCenter().y < -5.0f) {
			if(liquid_[i].getUserData() != null) {
				if(((PhysicsEntity)liquid_[i].getUserData()).setToDie_ == true)
				{
					world_.destroyBody(liquid_[i]);
					removeEntity(((PhysicsEntity)liquid_[i].getUserData()));
					graphics_.removeEntity(((PhysicsEntity)liquid_[i].getUserData()).getEntity());


					float massPerParticle = totalMass / nParticles;
					Entity entity = new Entity();
					GraphicsEntity gEntity = new GraphicsEntity(entity);
					gEntity.setCircle(0.6f, 1.0f,1.0f);
					gEntity.setMaterial(new Material(0.2f, 0.2f, 1.0f));

					PhysicsEntity pEntity = new PhysicsEntity(entity, this);

					CircleShape pd = new CircleShape();
					pd.m_radius = 0.04f;
					pEntity.fixtureDef_ = new FixtureDef();
					pEntity.fixtureDef_.shape = pd;
					pEntity.fixtureDef_.density = 1f;
					pEntity.fixtureDef_.filter.groupIndex = -10;	
					pEntity.fixtureDef_.restitution = 0.4f;
					pEntity.fixtureDef_.friction = 0.0f;
					//BodyDef bd = new BodyDef();
					BodyDef bd = pEntity.bodydef_;
					float cx = 0.0f;
					float cy = 0.0f;
					bd.position = new Vec2( MathUtils.randomFloat(cx-0.5f ,cx+0.5f),
							MathUtils.randomFloat(cy-0.5f,cy+0.5f));

					//bd.position = new Vec2(i*)

					//bd.fixedRotation = true;
					bd.type = BodyType.DYNAMIC;

					addEntity(pEntity);
					graphics_.addEntity(gEntity);
					pEntity.contactId_ = 666;
					pEntity.body_.setUserData(pEntity);

					//b.createFixture(fd).setUserData(666);

					MassData md = new MassData();
					md.mass = massPerParticle;
					md.I = 1.0f;
					pEntity.body_.setMassData(md);
					pEntity.body_.setSleepingAllowed(false);
					liquid_[i] = pEntity.body_;
					transferredLiquid_++;
				}
				if(((PhysicsEntity)liquid_[i].getUserData()).getEntity().getY() < -25.0f)
				{
					world_.destroyBody(liquid_[i]);
					removeEntity(((PhysicsEntity)liquid_[i].getUserData()));
					graphics_.removeEntity(((PhysicsEntity)liquid_[i].getUserData()).getEntity());


					float massPerParticle = totalMass / nParticles;
					Entity entity = new Entity();
					GraphicsEntity gEntity = new GraphicsEntity(entity);
					gEntity.setCircle(0.6f, 1.0f,1.0f);
					gEntity.setMaterial(new Material(0.2f, 0.2f, 1.0f));

					PhysicsEntity pEntity = new PhysicsEntity(entity, this);

					CircleShape pd = new CircleShape();
					pd.m_radius = 0.04f;
					pEntity.fixtureDef_ = new FixtureDef();
					pEntity.fixtureDef_.shape = pd;
					pEntity.fixtureDef_.density = 1f;
					pEntity.fixtureDef_.filter.groupIndex = -10;	
					pEntity.fixtureDef_.restitution = 0.4f;
					pEntity.fixtureDef_.friction = 0.0f;
					//BodyDef bd = new BodyDef();
					BodyDef bd = pEntity.bodydef_;
					float cx = -15.0f;
					float cy = 35.0f;
					bd.position = new Vec2( MathUtils.randomFloat(cx-10.5f ,cx+10.5f),
							MathUtils.randomFloat(cy-1.0f,cy+1.0f));

					//bd.position = new Vec2(i*)

					//bd.fixedRotation = true;
					bd.type = BodyType.DYNAMIC;

					addEntity(pEntity);
					graphics_.addEntity(gEntity);
					pEntity.contactId_ = 666;
					pEntity.body_.setUserData(pEntity);

					//b.createFixture(fd).setUserData(666);

					MassData md = new MassData();
					md.mass = massPerParticle;
					md.I = 1.0f;
					pEntity.body_.setMassData(md);
					pEntity.body_.setSleepingAllowed(false);
					liquid_[i] = pEntity.body_;
					transferredLiquid_++;
				}
			}
		}
		/*
		if (bod.getWorldCenter().y < -15.0f) {
			world_.destroyBody(bod);
			PolygonShape polyDef = new PolygonShape();
			polyDef.setAsBox(MathUtils.randomFloat(0.3f,0.7f), MathUtils.randomFloat(0.3f,0.7f));
			BodyDef bodyDef = new BodyDef();
			bodyDef.position = new Vec2(0.0f,25.0f);
			bodyDef.type = BodyType.DYNAMIC;
			bod = world_.createBody(bodyDef);
			bod.createFixture(polyDef, 1f);
		}
		*/
	}
	private void applyLiquidConstraint(float deltaT) {
		/*
		 * Unfortunately, this simulation method is not actually scale
		 * invariant, and it breaks down for rad < ~3 or so.  So we need
		 * to scale everything to an ideal rad and then scale it back after.
		 */
		final float idealRad = 50.0f;
		float multiplier = idealRad / rad;
		
		float[] xchange = new float[liquid_.length];
		float[] ychange = new float[liquid_.length];
		Arrays.fill(xchange,0.0f);
		Arrays.fill(ychange, 0.0f);
		
		float[] xs = new float[liquid_.length];
		float[] ys = new float[liquid_.length];
		float[] vxs = new float[liquid_.length];
		float[] vys = new float[liquid_.length];
		for (int i=0; i < curParticle; ++i) {
			if(liquid_[i].m_userData != null)
			{
			xs[i] = multiplier*liquid_[i].m_sweep.c.x;
			ys[i] = multiplier*liquid_[i].m_sweep.c.y;
			vxs[i] = multiplier*liquid_[i].m_linearVelocity.x;
			vys[i] = multiplier*liquid_[i].m_linearVelocity.y;
			}
		}
		
		for(int i = 0; i < curParticle; i++) {
			// Populate the neighbor list from the 9 proximate cells
			ArrayList<Integer> neighbors = new ArrayList<Integer>();
	        int hcell = hashX(liquid_[i].m_sweep.c.x);
	        int vcell = hashY(liquid_[i].m_sweep.c.y);
	        for(int nx = -1; nx < 2; nx++) {
	            for(int ny = -1; ny < 2; ny++) {
	                int xc = hcell + nx;
	                int yc = vcell + ny;
	                if(xc > -1 && xc < hashWidth && yc > -1 && yc < hashHeight && hash[xc][yc].size() > 0) {
	                    for(int a = 0; a < hash[xc][yc].size(); a++) {
	                        Integer ne = (Integer)hash[xc][yc].get(a);
	                        if(ne != null && ne.intValue() != i) neighbors.add(ne);
	                    }
	                }
	            }
	        }
	        
	        // Particle pressure calculated by particle proximity
            // Pressures = 0 iff all particles within range are idealRad distance away
            float[] vlen = new float[neighbors.size()];
            float p = 0.0f;
            float pnear = 0.0f;
            for(int a = 0; a < neighbors.size(); a++) {
                Integer n = (Integer)neighbors.get(a);
                int j = n.intValue();
                float vx = xs[j]-xs[i];//liquid_[j].m_sweep.c.x - liquid_[i].m_sweep.c.x;
                float vy = ys[j]-ys[i];//liquid_[j].m_sweep.c.y - liquid_[i].m_sweep.c.y;
                
                //early exit check
                if(vx > -idealRad && vx < idealRad && vy > -idealRad && vy < idealRad) {
                    float vlensqr = (vx * vx + vy * vy);
                    //within idealRad check
                    if(vlensqr < idealRad*idealRad) {
                    	vlen[a] = (float)Math.sqrt(vlensqr);
                    	if (vlen[a] < Settings.EPSILON) vlen[a] = idealRad-.01f;
                        float oneminusq = 1.0f-(vlen[a] / idealRad);
                        p = (p + oneminusq*oneminusq);
                        pnear = (pnear + oneminusq*oneminusq*oneminusq);
                    } else {
                    	vlen[a] = Float.MAX_VALUE;
                    }
                }
            }
            
            // Now actually apply the forces
            //System.out.println(p);
            float pressure = (p - 5F) / 2.0F; //normal pressure term
            float presnear = pnear / 2.0F; //near particles term
            float changex = 0.0F;
            float changey = 0.0F;
            for(int a = 0; a < neighbors.size(); a++) {
                Integer n = (Integer)neighbors.get(a);
                int j = n.intValue();
                float vx = xs[j]-xs[i];//liquid_[j].m_sweep.c.x - liquid_[i].m_sweep.c.x;
                float vy = ys[j]-ys[i];//liquid_[j].m_sweep.c.y - liquid_[i].m_sweep.c.y;
                if(vx > -idealRad && vx < idealRad && vy > -idealRad && vy < idealRad) {
                    if(vlen[a] < idealRad) {
                        float q = vlen[a] / idealRad;
                        float oneminusq = 1.0f-q;
                        float factor = oneminusq * (pressure + presnear * oneminusq) / (2.0F*vlen[a]);
                        float dx = vx * factor;
                        float dy = vy * factor;
                        float relvx = vxs[j] - vxs[i];
                        float relvy = vys[j] - vys[i];
                        factor = visc * oneminusq * deltaT;
                        dx -= relvx * factor;
                        dy -= relvy * factor;
                        //liquid_[j].m_xf.position.x += dx;//*deltaT*deltaT;
                        //liquid_[j].m_xf.position.y += dy;//*deltaT*deltaT;
                        //liquid_[j].m_linearVelocity.x += dx;///deltaT;//deltaT;
                        //liquid_[j].m_linearVelocity.y += dy;///deltaT;//deltaT;
                        xchange[j] += dx;
                        ychange[j] += dy;
                        changex -= dx;
                        changey -= dy;
                    }
                }
            }
	        //liquid_[i].m_xf.position.x += changex;//*deltaT*deltaT;
	        //liquid_[i].m_xf.position.y += changey;//*deltaT*deltaT;
	        //liquid_[i].m_linearVelocity.x += changex;///deltaT;//deltaT;
	        //liquid_[i].m_linearVelocity.y += changey;///deltaT;//deltaT;
	        xchange[i] += changex;
	        ychange[i] += changey;
        }
		//multiplier *= deltaT;
		for (int i=0; i< curParticle; ++i) {
			if(liquid_[i].m_userData != null)
			{
			liquid_[i].m_xf.position.x += xchange[i] / multiplier;
			liquid_[i].m_xf.position.y += ychange[i] / multiplier;
			liquid_[i].m_linearVelocity.x += xchange[i] / (multiplier*deltaT);
			liquid_[i].m_linearVelocity.y += ychange[i] / (multiplier*deltaT);
			}
		}
		
	}

	
	// END OF FLUID STUFF
	
	public Physics(Graphics g) {
		doSleep_ = true;
		gravity_ = new Vec2(0.0f, -10.0f);
		
		world_ = new World(gravity_, doSleep_);
		world_.setContinuousPhysics(true);
		world_.setWarmStarting(true);
		world_.setContactListener(new ContactListener() {

			@Override
			public void beginContact(Contact contact) {
				Fixture fixtureA = contact.getFixtureA();
				Fixture fixtureB = contact.getFixtureB();
				Object objA = fixtureA.getBody().getUserData();
				Object objB = fixtureB.getBody().getUserData();
				if(objA != null) {
					if(((PhysicsEntity)objA).getType() == PhysicsEntity.TYPE_SENSOR)
					{
						if(((PhysicsEntity)objB).contactId_ == 666) {
							((PhysicsEntity)objB).colliding_ = true;
						}
					}
				}
				if(objB != null) {
					if(((PhysicsEntity)objB).getType() == PhysicsEntity.TYPE_SENSOR)
					{
						if(((PhysicsEntity)objA).contactId_ == 666) {
							((PhysicsEntity)objA).colliding_ = true;
						}
					}
				}
			}

			@Override
			public void endContact(Contact contact) {
				Fixture fixtureA = contact.getFixtureA();
				Fixture fixtureB = contact.getFixtureB();
				Object objA = fixtureA.getBody().getUserData();
				Object objB = fixtureB.getBody().getUserData();
				if(objA != null) {
					if(((PhysicsEntity)objA).getType() == PhysicsEntity.TYPE_SENSOR)
					{
						
						if(((PhysicsEntity)objB).contactId_ == 666) {
							((PhysicsEntity)objB).colliding_ = false;
						}
					}
					if(((PhysicsEntity)objB).getType() == PhysicsEntity.TYPE_SENSOR)
					{
						if(((PhysicsEntity)objA).contactId_ == 666) {
							((PhysicsEntity)objA).colliding_ = false;
						}
					///	((PhysicsEntity)objB).colliding_ = false;
					}
				}
				
			}

			@Override
			public void postSolve(Contact arg0, ContactImpulse arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void preSolve(Contact arg0, Manifold arg1) {
				// TODO Auto-generated method stub
				
			}
		    
		});

		entities_ = new Vector<PhysicsEntity>();
		graphics_ = g;
	}
	public void init() {
		initLiquid();
		
		// Delete everything
		Enumeration<PhysicsEntity> e = entities_.elements();
		while(e.hasMoreElements()) {
			PhysicsEntity pEntity = e.nextElement();			
			removeEntity(pEntity);
			world_.destroyBody(pEntity.body_);
		}
		//for(int i = 0; i < curParticle; i++) {
			//world_.destroyBody(liquid_[i]);
		//}
		
		liquid_ = new Body[nParticles];
		transferredLiquid_ = 0;
		curParticle = 0;
		

	}	   

	public void update() {
		float timeStep = 1.0f/60.0f;
		world_.step(timeStep, 8, 3);
		//System.out.println(test_.getPosition() + " " + world_.getGravity());
		//PhysicsEntity pe = entities_.firstElement();
		/*pe.getEntity().setPosition(test_.getPosition().x,
				                   test_.getPosition().y);
				                   */
		Enumeration<PhysicsEntity> e = entities_.elements();
		while(e.hasMoreElements()) {
			PhysicsEntity pEntity = e.nextElement();
			pEntity.update();
			
			if(pEntity.colliding_)
			{
				//System.out.println("f");
				removeEntity(pEntity);
			}
			/*pEntity.getEntity().setPosition(pEntity.getBody().getPosition().x, 
					                        pEntity.getBody().getPosition().y);*/
		}
		// Update Liquids
		//int n = 3;
		//for (int i=0; i<n; ++i) {
			hashLocations();
			applyLiquidConstraint(timeStep);
		//}
		dampenLiquid();
		
		checkBounds();
	}
	public void addLiquid(float x, float y, int amount) {
		float massPerParticle = totalMass / nParticles;	
		float cx = x-1.5f;
		float cy = y;
		int row = 0;
		mat = new Material("resources/glow.png");
		for (int i=curParticle; i < (curParticle+amount); ++i) {
			Entity entity = new Entity();
			GraphicsEntity gEntity = new GraphicsEntity(entity);
			//gEntity.setCircle(0.2f, 1.0f, 1.0f);
			gEntity.setBox(0.6f, 0.6f);
			gEntity.setMaterial(mat);
			
			PhysicsEntity pEntity = new PhysicsEntity(entity, this);
			
			CircleShape pd = new CircleShape();
			pd.m_radius = 0.04f;
			pEntity.fixtureDef_ = new FixtureDef();
			pEntity.fixtureDef_.shape = pd;
			pEntity.fixtureDef_.density = 1f;
			pEntity.fixtureDef_.filter.groupIndex = -10;	
			pEntity.fixtureDef_.restitution = 0.4f;
			pEntity.fixtureDef_.friction = 0.0f;
			//BodyDef bd = new BodyDef();
			BodyDef bd = pEntity.bodydef_;
			
			int dy = (int) Math.floor(((float)i / 15.0f));
			int dx = i % 15;
			bd.position = new Vec2(cx+dx*0.2f, cy+dy*0.2f);
			
			//bd.position = new Vec2(i*)
			
			//bd.fixedRotation = true;
			bd.type = BodyType.DYNAMIC;
			
			addEntity(pEntity);
			graphics_.addEntity(gEntity);
			pEntity.contactId_ = 666;
			pEntity.body_.setUserData(pEntity);
			
			//b.createFixture(fd).setUserData(666);
			
			MassData md = new MassData();
			md.mass = massPerParticle;
			md.I = 1.0f;
			pEntity.body_.setMassData(md);
			pEntity.body_.setSleepingAllowed(false);
			liquid_[i] = pEntity.body_;
		}
		curParticle += amount;
	}
	public void addEntity(PhysicsEntity entity) {
		entities_.add(entity);
		entity.body_ = world_.createBody(entity.bodydef_);
		entity.body_.createFixture(entity.fixtureDef_);
	}

	public void removeEntity(PhysicsEntity entity) {
		Enumeration<PhysicsEntity> e = entities_.elements();
		int index = 0;
		while(e.hasMoreElements()) {
			PhysicsEntity pEntity = e.nextElement();
			if(pEntity.equals(entity)) {
				pEntity.setToDie_ = true;

				return;
			}
			index++;
		}
	}
}
