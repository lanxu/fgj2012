package sirius;

public class Point {
	private float x_;
	private float y_;
	private float z_;
	
	Point(float x, float y, float z) {
		x_ = x;
		y_ = y;
		z_ = z;
	}
	
	public Point(float x, float y) {
		x_ = x;
		y_ = y;
	}
	
	public float getX() {
		return x_;
	}
	public float getY() {
		return y_;
	}
	public float getZ() {
		return z_;
	}
	
}
