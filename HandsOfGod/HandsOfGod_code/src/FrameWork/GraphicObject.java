package FrameWork;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GraphicObject {

	protected Bitmap 	m_bitmap;
	protected int		m_x;
	protected int		m_y;
	
	// 이미지 생성자
	public GraphicObject(Bitmap bitmap){
		m_bitmap = bitmap;
		m_x = 0;
		m_y = 0;
	}

	// 위지 지정
	public void SetPosition(int x,int y){
		m_x = x;
		m_y = y;
	}

	// 그리기
	public void Draw(Canvas canvas){
		canvas.drawBitmap(m_bitmap,m_x,m_y,null);
	}
	
	// 그림의 위치(x,y)
	public int GetX(){
		return m_x;
	}	
	public int GetY(){
		return m_y;
	}
}
