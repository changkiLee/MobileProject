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
    	// ������ ���ֱ�
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	
    	// ������ ���ֱ�
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        // ȭ�� ����
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
        
        // ��Ƽ��Ƽ ����
        AppManager.getInstance().setActivity(this);
        
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
	}

    // ȸ���� �ʱ�ȭ ����
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
    	super.onConfigurationChanged(newConfig);
    }
    
    // ȭ�� ���� ��ȭ
    static int state = 0;
	public void G_State(int st)
	{
		state = st;
	}
		
    // �ڷΰ��� ��ư �������� ����(0) or �޴�(1)
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
	    // �ڷΰ��� ��ư ������ �����Ѵٸ� �������� �����
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

	// onStop����(ȨŰ ������) ����
    @Override
    public void onStop(){
    	super.onStop();
    	System.exit(0);
    }
}