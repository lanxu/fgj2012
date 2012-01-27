package sirius;

import java.util.Vector;

public class Shape {
	public final static int POINT = 0;
	public final static int CIRCLE = 1;
	public final static int POLYGON = 2;
	
	private int shape_;
	private Vector<Point> points_;
	
	public Shape(int shape) {
		shape_ = shape;
		points_ = new Vector<Point>();
	}
	
	public void addPoint(Point point) {
		points_.add(point);
	}
	
	public Vector<Point> getPoints() {
		return points_;
	}
	public int getShapeType()
	{
		return shape_;
	}
	/*
	public void setParameter(String parameter, float value) {
		
	}
	*/
}
