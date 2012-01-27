package sirius.graphics;

import sirius.Entity;

public class GraphicsEntity {
	private Texture texture_;
	private Entity entity_;
	//private boolean flipped_;
	
	public GraphicsEntity(Entity entity) {
		entity_ = entity;
	}
	
	public Entity getEntity() {
		return entity_;
	}
	
	public void addTexture(Texture tex) {
		texture_ = tex;
	}
	
	Texture getTexture() {
		return texture_;
	}
}
