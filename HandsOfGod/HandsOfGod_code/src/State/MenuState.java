package State;

import edu.dongguk.mme.HOG.HOGActivity;
import edu.dongguk.mme.HOG.R;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.MotionEvent;
import FrameWork.AppManager;
import FrameWork.IState;
import FrameWork.SoundManager;
import Game.Game_Save;


public class MenuState implements IState{
	Bitmap menu;
	HOGActivity st = new HOGActivity();	
	Game_Save save = new Game_Save();
	
	// 사운드
	public static final int BUTTON = 1;
	
	public void Init() {
		menu = AppManager.getInstance().getBitmap(R.drawable.menu);
		
		// 상태(스테이트, 저장값) 지정
		st.G_State(0);
		save.Check_save(0);
		
		// 버튼음 설정
		SoundManager.getInstatnce().addSound(BUTTON, R.raw.button);
	}

	public void Destroy() {
		
	}

	public void Update() {
		
	}

	public void Render(Canvas canvas) {
		canvas.drawBitmap(menu, 0, 0, null);		
	}

	 public boolean onKeyDown(int keyCode, KeyEvent event) {
		 return false;
	 }

	public boolean onTouchEvent(MotionEvent event) {
		// 터치된 곳의 좌표 읽어오기
		int _x = (int)event.getX();
		int _y = (int)event.getY();

		if(event.getAction() == MotionEvent.ACTION_DOWN){			
			// 게임보기 버튼 터치
			Rect button_game = new Rect(263,285,443,352);
			if(button_game.contains(_x, _y)){
				SoundManager.getInstatnce().play(BUTTON);
				AppManager.getInstance().getGameView().ChangeGameState(new GameState());				
				st.G_State(2);
			}
			// 랭킹보기 버튼 터치	
			Rect button_rank = new Rect(195,367,450,435);
			if(button_rank.contains(_x, _y)){	
				SoundManager.getInstatnce().play(BUTTON);
				AppManager.getInstance().getGameView().ChangeGameState(new RankState());				
				st.G_State(1);
			}
			// 설정하기 버튼 터치
			Rect button_option = new Rect(135,450,405,525);
			if(button_option.contains(_x, _y)){				
				SoundManager.getInstatnce().play(BUTTON);
				AppManager.getInstance().getGameView().ChangeGameState(new OptionState());			
				st.G_State(1);
			}
			// 메뉴얼 버튼 터치
			Rect button_menual = new Rect(75,540,308,607);
			if(button_menual.contains(_x, _y)){
				SoundManager.getInstatnce().play(BUTTON);
				AppManager.getInstance().getGameView().ChangeGameState(new ManualState());			
				st.G_State(1);
			}
		}
		return true;
	}

}
