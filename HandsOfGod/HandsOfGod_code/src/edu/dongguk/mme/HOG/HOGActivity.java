package edu.dongguk.mme.HOG;

import FrameWork.AppManager;
import FrameWork.GameView;
import State.MenuState;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

public class HOGActivity extends Activity {
    /** Called when the activity is first created. */ 
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	// 제목줄 없애기
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	
    	// 상태줄 없애기
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        // 화면 고정
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
        
        // 액티비티 설정
        AppManager.getInstance().setActivity(this);
        
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
	}

    // 회전시 초기화 금지
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
    	super.onConfigurationChanged(newConfig);
    }
    
    // 화면 상태 변화
    static int state = 0;
	public void G_State(int st)
	{
		state = st;
	}
		
    // 뒤로가기 버튼 눌렀을시 종료(0) or 메뉴(1)
    @Override
    public void onBackPressed(){
    	if(state == 1){    		
    		AppManager.getInstance().getGameView().ChangeGameState(new MenuState());
    	}
    	else{    		
    		System.exit(0);
    	}
    }
    
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){		
	    // 뒤로가기 버튼 눌러서 종료한다면 종료할지 물어보기
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if(state == 0){
				new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Quit").setMessage("Do you want to quit?").setPositiveButton("Yes", new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick( DialogInterface dialog, int which ){
						System.exit(0);
					}
					}).setNegativeButton( "No", null ).show();
				return true;
			}
			if(state == 2){
				new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Game").setMessage("Go to the Menu?").setPositiveButton("Yes", new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick( DialogInterface dialog, int which ){
						AppManager.getInstance().getGameView().ChangeGameState(new MenuState()); 
					}
					}).setNegativeButton( "No", null ).show();
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}			

	// onStop상태(홈키 누르면) 종료
    @Override
    public void onStop(){
    	super.onStop();
    	System.exit(0);
    }
}