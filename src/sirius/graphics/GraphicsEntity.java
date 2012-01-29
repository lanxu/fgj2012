package sirius.graphics;

import sirius.Entity;
import sirius.Point;
import sirius.Shape;

public class GraphicsEntity {
	private Texture texture_;
	private Entity entity_;
	private Material material_;
	private Shape polygonShape_;
	private Shape texShape_;
	//private boolean flipped_;
	
	public GraphicsEntity(Entity entity) {
		entity_ = entity;
	}
	
	public Entity getEntity() {
		return entity_;
	}
	
	public void setMaterial(Material material) {
		material_ = material;
	}
	
	public void addTexture(Texture tex) {
		texture_ = tex;
	}
	
	Texture getTexture() {
		//return texture_;
		return material_.getTexture();
	}
	public void setPolygon() {
		
	}
	public void setCircle(float radius, float dx, float dy) {
		polygonShape_ = new Shape(Shape.POLYGON);
		for (int i = 0; i < 360; i+=15) {
			float x = (float) (radius * Math.cos(i/(180/3.14f))* dx);
			float y = (float) (radius * Math.sin(i/(180/3.14f))* dy);
			polygonShape_.addPoint(new Point(x,y));
		}

	}
	public void setBox(float width, float height) {
		polygonShape_ = new Shape(Shape.POLYGON);
		
		polygonShape_.addPoint(new Point(-width,-height));
		polygonShape_.addPoint(new Point( width,-height));
		polygonShape_.addPoint(new Point( width, height));
		polygonShape_.addPoint(new Point(-width, height));
		
		texShape_ = new Shape(Shape.POLYGON);
		texShape_.addPoint(new Point(0,0));
		texShape_.addPoint(new Point(1,0));
		texShape_.addPoint(new Point(1,1));
		texShape_.addPoint(new Point(0,1));
	}
	public Material getMaterial() {
		return material_;
	}
	public Shape getPolygonShape() {
		return polygonShape_;
	}
	public Shape getTextureShape() {
		return texShape_;
	}
}
