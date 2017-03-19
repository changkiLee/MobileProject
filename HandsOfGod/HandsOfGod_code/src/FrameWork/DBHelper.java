package FrameWork;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DBHelper extends SQLiteOpenHelper {
	
	// 데이터 베이스
	public DBHelper(Context context, String name, CursorFactory factory,int version) {
		super(context, name, factory, version);

	}
	
	// 생성
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE RankBoard(name CHAR(16) NOT NULL, score INTEGER, combo INTEGER, level INTEGER);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
	}

}
