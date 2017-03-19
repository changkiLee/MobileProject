package FrameWork;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

// 상태 인터페이스
public interface IState {
	public void Init();
	public void Destroy();
	public void Update();
	public void Render(Canvas canvas);
	public boolean onKeyDown(int keyCode, KeyEvent event);
	public boolean onTouchEvent(MotionEvent event);	
}
