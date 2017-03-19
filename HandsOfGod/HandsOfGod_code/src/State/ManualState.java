package State;

import edu.dongguk.mme.HOG.R;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;
import FrameWork.AppManager;
import FrameWork.IState;
import FrameWork.SoundManager;

public class ManualState implements IState {
	Bitmap manual;
	int touch;
	
	// 사운드
	public static final int BUTTON = 1;
	
	@Override
	public void Init() {
		// 버튼음 설정
		SoundManager.getInstatnce().addSound(BUTTON, R.raw.button);
		
		// 화면 설정
		manual = AppManager.getInstance().getBitmap(R.drawable.manual1);
		touch = 0;
	}

	@Override
	public void Destroy() {

	}

	@Override
	public void Update() {

	}

	@Override
	public void Render(Canvas canvas) {		
		if(touch % 6 == 0){
			manual = AppManager.getInstance().getBitmap(R.drawable.manual1);
		}
		else if(touch % 6 == 1){
			manual = AppManager.getInstance().getBitmap(R.drawable.manual2);
		}
		else if(touch % 6 == 2){
			manual = AppManager.getInstance().getBitmap(R.drawable.manual3);
		}
		else if(touch % 6 == 3){
			manual = AppManager.getInstance().getBitmap(R.drawable.manual4);
		}
		else if(touch % 6 == 4){
			manual = AppManager.getInstance().getBitmap(R.drawable.manual5);
		}
		else if(touch % 6 == 5){
			manual = AppManager.getInstance().getBitmap(R.drawable.manual6);
		}
		
		// 메뉴얼 그리기
		canvas.drawBitmap(manual, 0, 0, null);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			touch++;			
		}
		return true;
	}

}
