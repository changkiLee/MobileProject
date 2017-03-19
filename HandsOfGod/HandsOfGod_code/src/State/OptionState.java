package State;

import edu.dongguk.mme.HOG.HOGActivity;
import edu.dongguk.mme.HOG.R;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.MotionEvent;
import FrameWork.AppManager;
import FrameWork.IState;
import FrameWork.SoundManager;

public class OptionState implements IState{
	Bitmap option;
	Bitmap lever;
	
	// �����, ȿ���� ũ�� ���� �߰�
	public static final int BUTTON = 1;
	HOGActivity st = new HOGActivity();	
	
	// ���� ������
	public static int Sound = 0;
	public static int Bgm = 0;
	
	// ���� ���� ��ġ
	public static float s_x = 240, s_y = 278;
	public static float b_x = 240, b_y = 428;
	
	SoundManager Check_sound;
	
	Paint p;
	
	public void Init() {
		option = AppManager.getInstance().getBitmap(R.drawable.setting);
		lever = AppManager.getInstance().getBitmap(R.drawable.setting_lever);
		Check_sound = new SoundManager();
	}

	public void Destroy() {
		
	}

	public void Update() {
		
	}

	public void Render(Canvas canvas) {
		 
		
		canvas.drawBitmap(option, 0, 0, null);
		canvas.drawBitmap(lever, s_x + 75 * Sound, s_y, null);
		canvas.drawBitmap(lever, b_x + 75 * Bgm, b_y, null);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return false;
	}

	public boolean onTouchEvent(MotionEvent event) {
		
		int _x = (int)event.getX();
		int _y = (int)event.getY();

		if(event.getAction() == MotionEvent.ACTION_DOWN){			
			// ��� ��ư ��ġ
			Rect button_Cancel = new Rect(322,510,443,548);			
			if(button_Cancel.contains(_x, _y)){
				// ���� �ʱ�ȭ
				SoundManager.getInstatnce().play(BUTTON);
				Sound = 0;
				Bgm = 0;
				Check_sound.BGM = (float)0.5;
				Check_sound.sound = (float)0.5;
				SoundManager.getInstatnce().PlayPause();
			}
			
			// ���� ��ư ��ġ
			Rect button_Select = new Rect(195,510,315,548);
			if(button_Select.contains(_x, _y)){
				SoundManager.getInstatnce().play(BUTTON);
				
				if(Bgm == -2)
					Check_sound.BGM = (float)0.0;
				
				else if(Bgm == -1)
					Check_sound.BGM = (float)0.3;
				
				else if(Bgm == 0)
					Check_sound.BGM = (float)0.5;
				
				else if(Bgm == 1)
					Check_sound.BGM = (float)0.8;
				
				else
					Check_sound.BGM = (float)1.0;
				
				if(Sound == -2)
					Check_sound.sound = (float)0.0;
				
				else if(Sound == -1)
					Check_sound.sound = (float)0.3;
				
				else if(Sound == 0)
					Check_sound.sound = (float)0.5;
				
				else if(Sound == 1)
					Check_sound.sound = (float)0.8;
				
				else
					Check_sound.sound = (float)1.0;
				
				AppManager.getInstance().getGameView().ChangeGameState(new MenuState());				
				st.G_State(1);
			}
			
			// SOUND ����
			// sound -��ư ��ġ
			Rect button_Sound_m = new Rect(45, 263, 75, 323);			
			if(button_Sound_m.contains(_x, _y)){
				
				if(Sound != -2)
					Sound --;
				
				if(Sound == -2)
					Check_sound.sound = (float)0.0;
				
				else if(Sound == -1)
					Check_sound.sound = (float)0.3;
				
				else if(Sound == 0)
					Check_sound.sound = (float)0.5;
				
				else if(Sound == 1)
					Check_sound.sound = (float)0.8;
				
				else
					Check_sound.sound = (float)1.0;
				
				SoundManager.getInstatnce().play(BUTTON);
				
				
			}
			
			// sound +��ư ��ġ
			Rect button_Sound_p = new Rect(405, 263, 435, 323);
			if(button_Sound_p.contains(_x, _y)){
				
				if(Sound != 2)
					Sound ++;
				
				if(Sound == -2)
					Check_sound.sound = (float)0.0;
				
				else if(Sound == -1)
					Check_sound.sound = (float)0.3;
				
				else if(Sound == 0)
					Check_sound.sound = (float)0.5;
				
				else if(Sound == 1)
					Check_sound.sound = (float)0.8;
				
				else
					Check_sound.sound = (float)1.0;
				
				SoundManager.getInstatnce().play(BUTTON);
				
			}
			
			// BGM ����
			// bgm -��ư ��ġ
			Rect button_BGM_m = new Rect(45, 420, 75, 480);
			if(button_BGM_m.contains(_x, _y)){
				
				if(Bgm != -2)
					Bgm --;
				
				if(Bgm == -2)
					Check_sound.BGM = (float)0.0;
				
				else if(Bgm == -1)
					Check_sound.BGM = (float)0.3;
				
				else if(Bgm == 0)
					Check_sound.BGM = (float)0.5;
				
				else if(Bgm == 1)
					Check_sound.BGM = (float)0.8;
				
				else
					Check_sound.BGM = (float)1.0;
				
				SoundManager.getInstatnce().play(BUTTON);
				SoundManager.getInstatnce().PlayPause();
				
			}
			
			// bgm +��ư ��ġ
			Rect button_BGM_p = new Rect(405, 420, 435, 480);
			if(button_BGM_p.contains(_x, _y)){
				
				if(Bgm != 2)
					Bgm ++;
				
				if(Bgm == -2)
					Check_sound.BGM = (float)0.0;
				
				else if(Bgm == -1)
					Check_sound.BGM = (float)0.3;
				
				else if(Bgm == 0)
					Check_sound.BGM = (float)0.5;
				
				else if(Bgm == 1)
					Check_sound.BGM = (float)0.8;
				
				else
					Check_sound.BGM = (float)1.0;
				
				SoundManager.getInstatnce().play(BUTTON);
				SoundManager.getInstatnce().PlayPause();
				
			}
		}
		return true;
	}
}
