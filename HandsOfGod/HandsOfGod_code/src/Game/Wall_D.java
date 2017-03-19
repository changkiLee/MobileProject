package Game;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import FrameWork.AppManager;
import edu.dongguk.mme.HOG.R;

// ��(�Ʒ���)
public class Wall_D extends Wall {
	static Bitmap w = AppManager.getInstance().getBitmap(R.drawable.bar_g0);
	int wall_color = 4; // �ʷ�
	
	// ������
	public Wall_D(){
		super(w);
		this.SetPosition(0, 0);
		gauge = 0;
	}	
	
	// ������ �޼ҵ�
	public Bitmap plus_gauge(int gauge, int _turn){
		Matrix matrix = new Matrix();
		if(_turn == 0){
			// �Ѱ�ġ�� 10
			if(gauge < 10){
				// �� 0���� 9������ �׸�
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
				// ��ġ
				w = AppManager.getInstance().getBitmap(R.drawable.bar_g10);
			}
		}
		else if(_turn == 1){
			//90�� ȸ��	
			matrix.postRotate(90);
			// �Ѱ�ġ�� 10
			if(gauge < 10){
				// �� 0���� 9������ �׸�
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
				// ��ġ				
				w = AppManager.getInstance().getBitmap(R.drawable.bar_g10);
				w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
			}
		}
		else if(_turn == 2){
			//180�� ȸ��
			matrix.postRotate(180);
			// �Ѱ�ġ�� 10
			if(gauge < 10){
				// �� 0���� 9������ �׸�
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
				// ��ġ
				w = AppManager.getInstance().getBitmap(R.drawable.bar_g10);
				w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
			}
		}
		else if(_turn == 3){
			// 270�� ȸ��
			matrix.postRotate(270);
			// �Ѱ�ġ�� 10
			if(gauge < 10){
				// �� 0���� 9������ �׸�
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
				// ��ġ
				w = AppManager.getInstance().getBitmap(R.drawable.bar_g10);
				w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
			}
		}
		
		return w;
	}
}