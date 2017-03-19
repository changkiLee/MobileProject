package Game;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import FrameWork.AppManager;
import edu.dongguk.mme.HOG.R;

// 벽(아래쪽)
public class Wall_D extends Wall {
	static Bitmap w = AppManager.getInstance().getBitmap(R.drawable.bar_g0);
	int wall_color = 4; // 초록
	
	// 생성자
	public Wall_D(){
		super(w);
		this.SetPosition(0, 0);
		gauge = 0;
	}	
	
	// 게이지 메소드
	public Bitmap plus_gauge(int gauge, int _turn){
		Matrix matrix = new Matrix();
		if(_turn == 0){
			// 한계치는 10
			if(gauge < 10){
				// 벽 0부터 9까지의 그림
				if(gauge == 0)
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g0);
				if(gauge == 1)
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g1);
				if(gauge == 2)
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g2);
				if(gauge == 3)
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g3);
				if(gauge == 4)
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g4);
				if(gauge == 5)
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g5);
				if(gauge == 6)
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g6);
				if(gauge == 7)
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g7);
				if(gauge == 8)
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g8);
				if(gauge == 9)
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g9);				
			}else {
				// 터치
				w = AppManager.getInstance().getBitmap(R.drawable.bar_g10);
			}
		}
		else if(_turn == 1){
			//90도 회전	
			matrix.postRotate(90);
			// 한계치는 10
			if(gauge < 10){
				// 벽 0부터 9까지의 그림
				if(gauge == 0){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g0);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 1){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g1);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 2){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g2);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 3){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g3);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 4){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g4);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 5){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g5);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 6){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g6);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}				
				if(gauge == 7){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g7);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 8){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g8);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 9){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g9);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
			}else {
				// 터치				
				w = AppManager.getInstance().getBitmap(R.drawable.bar_g10);
				w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
			}
		}
		else if(_turn == 2){
			//180도 회전
			matrix.postRotate(180);
			// 한계치는 10
			if(gauge < 10){
				// 벽 0부터 9까지의 그림
				if(gauge == 0){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g0);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 1){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g1);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 2){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g2);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 3){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g3);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 4){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g4);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 5){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g5);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 6){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g6);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}				
				if(gauge == 7){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g7);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 8){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g8);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 9){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g9);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
			}else {
				// 터치
				w = AppManager.getInstance().getBitmap(R.drawable.bar_g10);
				w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
			}
		}
		else if(_turn == 3){
			// 270도 회전
			matrix.postRotate(270);
			// 한계치는 10
			if(gauge < 10){
				// 벽 0부터 9까지의 그림
				if(gauge == 0){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g0);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 1){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g1);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 2){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g2);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 3){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g3);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 4){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g4);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 5){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g5);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 6){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g6);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}				
				if(gauge == 7){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g7);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 8){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g8);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 9){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_g9);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
			}else {
				// 터치
				w = AppManager.getInstance().getBitmap(R.drawable.bar_g10);
				w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
			}
		}
		
		return w;
	}
}