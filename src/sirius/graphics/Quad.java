package sirius.graphics;

import java.util.Vector;
import org.lwjgl.util.Point;

public class Quad {
	private Vector<Point> points_;
	Quad(Point p1, Point p2, Point p3, Point p4) {
		points_.add(p1);
		points_.add(p2);
		points_.add(p3);
		points_.add(p4);
	}
	Vector<Point> getPoints() {
		return points_;
	}
}
