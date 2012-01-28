package sirius;

public class Entity {
	private float positionX_;
	private float positionY_;
	private float rotation_;
	
	public Entity() {
		positionX_ = 0;
		positionY_ = 0;
		rotation_ = 0;
	}
	public float getX() {
		return positionX_;
	}
	
	public float getY() {
		return positionY_;
	}
	
	public float getRotation() {
		return rotation_;
	}

	public void setPosition(float x, float y) {
		positionX_ = x;
		positionY_ = y;
	}
	public void setRotation(float rot) {
		rotation_ = rot;
	}
}
