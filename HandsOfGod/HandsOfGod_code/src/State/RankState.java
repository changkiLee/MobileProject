package State;

import edu.dongguk.mme.HOG.R;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.MotionEvent;
import FrameWork.AppManager;
import FrameWork.DBHelper;
import FrameWork.IState;
import FrameWork.SoundManager;
import Game.Game_Save;

public class RankState implements IState{
	Bitmap rank;
	Paint p;
	
	// �޴� ����
	boolean tap;
	
	// ����
	public static final int BUTTON = 1;
	
	// ������ ��
	DBHelper m_helper;
	Game_Save save = new Game_Save();
	
	// ���尪
	public String name = "None";
	public int score = 0;
	public int combo = 0;
	public int level = 0;
	
	public void Init(){
		// ���
		rank = AppManager.getInstance().getBitmap(R.drawable.score1);
		tap = true;
		
		// ����
		p = new Paint();
		p.setTextSize(20);
		p.setAntiAlias(true);
		p.setColor(Color.WHITE);
		
		// ��ư�� ����
		SoundManager.getInstatnce().addSound(BUTTON, R.raw.button);
		
		// ������ ���̽� ����
		m_helper = new DBHelper(AppManager.getInstance().getActivity(), "rank.db", null, 1);
		
		// ���尪 �ҷ�����
		save.Game_re(this);
		
		if(save.Save_check() == 1){
			// DB ����
			SQLiteDatabase db = m_helper.getWritableDatabase();
			ContentValues row = new ContentValues();
			row.put("name", name);
			row.put("score", score);
			row.put("combo", combo);
			row.put("level", level);
			
			db.insert("RankBoard", null, row);
			
			db.close();
		}
	}

	public void Destroy() {

	}

	public void Update() {
		// ��� �ٲ��ֱ�
		if(tap == true){
			rank = AppManager.getInstance().getBitmap(R.drawable.score1);
		}
		else{
			rank = AppManager.getInstance().getBitmap(R.drawable.score2);
		}
	}

	public void Render(Canvas canvas) {		
		// DB �ҷ�����
		SQLiteDatabase db = m_helper.getReadableDatabase();
		
		if(tap == true){
			// ��� �׸���
			canvas.drawBitmap(rank, 0, 0, null);
			
			// DB���� ��������
			Cursor cursor = db.query("RankBoard", new String[]{"name","score","combo","level"}, null, null, null, null ,"score desc");		
			for(int i = 0; i < 18; i++){	// 18�� ���� ���尡��
				if(cursor.moveToNext() == false)
					break;
				
				// ��� ǥ��
				canvas.drawText("" + (i+1), 35, 160 + (25*i), p);
				canvas.drawText(cursor.getString(0), 100, 160 + (25*i), p);
				canvas.drawText(cursor.getString(3), 220, 160 + (25*i), p);
				canvas.drawText(cursor.getString(1), 290, 160 + (25*i), p);
			}		
			
			db.close();
		}
		else {			
			// ��� �׸���
			canvas.drawBitmap(rank, 0, 0, null);
			
			// DB���� ��������
			Cursor cursor = db.query("RankBoard", new String[]{"name","score","combo","level"}, null, null, null, null ,"combo desc");		
			for(int i = 0; i < 18; i++){
				if(cursor.moveToNext() == false)
					break;
				
				// ��� ǥ��
				canvas.drawText("" + (i+1), 35, 160 + (25*i), p);
				canvas.drawText(cursor.getString(0), 100, 160 + (25*i), p);
				canvas.drawText(cursor.getString(3), 220, 160 + (25*i), p);
				canvas.drawText(cursor.getString(2), 290, 160 + (25*i), p);
			}		
			
			db.close();
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {		
		return false;
	}

	public boolean onTouchEvent(MotionEvent event) {
		// ��ġ�� ���� ��ǥ �о����
		int _x = (int)event.getX();
		int _y = (int)event.getY();

		if(event.getAction() == MotionEvent.ACTION_DOWN){			
			// ���� ��ư ��ġ
			Rect button_score = new Rect(0,0,180,75);
			if(button_score.contains(_x, _y)){
				tap = true;
				SoundManager.getInstatnce().play(BUTTON);
			}
			// �޺� ��ư ��ġ
			Rect button_combo = new Rect(180,0,353,75);
			if(button_combo.contains(_x, _y)){
				tap = false;
				SoundManager.getInstatnce().play(BUTTON);
			}			
		}
		return true;
	}
}
