package State;

import edu.dongguk.mme.HOG.R;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;
import FrameWork.AppManager;
import FrameWork.IState;

public class Logo0State implements IState {
	Bitmap logo;
	
	public void Init() {
		logo = AppManager.getInstance().getBitmap(R.drawable.logo0);		
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
		// ��ġ�� �ΰ� ���·� ��ȯ
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			AppManager.getInstance().getGameView().ChangeGameState(new LogoState());
		}
		return true;
	}

}
