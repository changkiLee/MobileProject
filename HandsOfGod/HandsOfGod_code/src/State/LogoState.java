package State;

import edu.dongguk.mme.HOG.R;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;
import FrameWork.AppManager;
import FrameWork.IState;
import FrameWork.SoundManager;

public class LogoState implements IState {
	Bitmap logo;
	
	public void Init() {
		logo = AppManager.getInstance().getBitmap(R.drawable.logo);	
		SoundManager.getInstatnce().playLooped();
	}

	public void Destroy() {
		
	}

	public void Update() {
		
	}

	public void Render(Canvas canvas) {
		canvas.drawBitmap(logo, 0, 0, null);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return false;
	}

	public boolean onTouchEvent(MotionEvent event) {		
		// 터치시 메뉴 상태로 전환
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			AppManager.getInstance().getGameView().ChangeGameState(new MenuState());
		}
		return true;
	}

}
