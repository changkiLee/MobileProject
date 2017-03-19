package Game;

import android.graphics.Bitmap;
import FrameWork.GraphicObject;

// 벽
public class Wall extends GraphicObject{
	int gauge = 0;
	int wall_color = 0;
	
	public Wall(Bitmap bitmap) {
		super(bitmap);
	}
	
	// 벽의 게이지가 차는 경우(올바른 블록이 들어온 경우)
	public Bitmap plus_gauge(int _gauge, int _turn){

		return m_bitmap;
	}	
}
