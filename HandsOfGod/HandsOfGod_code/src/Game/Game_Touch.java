package Game;

import android.view.MotionEvent;

public class Game_Touch {

	public int x1, y1;
	public int x2, y2;
	public boolean up;
	
	// 블록의 터치를 판단하는 메소드
	public void onTouchEvent(MotionEvent event){
		if(event.getAction() == MotionEvent.ACTION_DOWN)
		{
			x1 = (int)event.getX();
			y1 = (int)event.getY();
			
			up = false;
		}
		
		if(event.getAction() == MotionEvent.ACTION_UP)
		{
			x2 = (int)event.getX();
			y2 = (int)event.getY();
			
			up = true;
		}
	}
	
}
