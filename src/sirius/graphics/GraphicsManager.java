package sirius.graphics;

import java.util.Vector;

public class GraphicsManager {

	/* Singleton stuff */
	private static GraphicsManager instance_;
	private static Object syncObject_;
	private GraphicsManager() {
		super();
		init();
	}
	public static GraphicsManager getInstance() {
		if (instance_ == null) {
			synchronized(syncObject_) {
				if (instance_ == null) {
				   instance_ = new GraphicsManager();
				}
			}
		}
		return instance_;
	}
	/* END */
	
	Vector<GraphicsEntity> entities_;
	public void init() {
		entities_.clear();
	}
	
}
