package Game;

import android.graphics.Bitmap;
import FrameWork.GraphicObject;

// ��
public class Wall extends GraphicObject{
	int gauge = 0;
	int wall_color = 0;
	
	public Wall(Bitmap bitmap) {
		super(bitmap);
	}
	
	// ���� �������� ���� ���(�ùٸ� ����� ���� ���)
	public Bitmap plus_gauge(int _gauge, int _turn){

		return m_bitmap;
	}	
}
