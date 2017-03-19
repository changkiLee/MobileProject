package Game;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import FrameWork.AppManager;
import edu.dongguk.mme.HOG.R;

// ��(������)
public class Wall_R extends Wall {
	static Bitmap w = AppManager.getInstance().getBitmap(R.drawable.bar_y0);
	int wall_color = 2; // ���
	
	// ������
	public Wall_R(){
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
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y0);
				if(gauge == 1)
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y1);
				if(gauge == 2)
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y2);
				if(gauge == 3)
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y3);
				if(gauge == 4)
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y4);
				if(gauge == 5)
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y5);
				if(gauge == 6)
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y6);
				if(gauge == 7)
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y7);
				if(gauge == 8)
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y8);
				if(gauge == 9)
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y9);				
			}else {
				// ��ġ
				w = AppManager.getInstance().getBitmap(R.drawable.bar_y10);
			}
		}
		else if(_turn == 1){
			//90�� ȸ��	
			matrix.postRotate(90);
			// �Ѱ�ġ�� 10
			if(gauge < 10){
				// �� 0���� 9������ �׸�
				if(gauge == 0){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y0);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 1){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y1);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 2){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y2);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 3){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y3);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 4){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y4);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 5){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y5);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 6){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y6);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}				
				if(gauge == 7){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y7);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 8){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y8);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 9){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y9);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
			}else {
				// ��ġ				
				w = AppManager.getInstance().getBitmap(R.drawable.bar_y10);
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
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y0);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 1){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y1);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 2){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y2);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 3){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y3);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 4){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y4);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 5){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y5);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 6){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y6);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}				
				if(gauge == 7){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y7);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 8){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y8);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 9){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y9);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
			}else {
				// ��ġ
				w = AppManager.getInstance().getBitmap(R.drawable.bar_y10);
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
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y0);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 1){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y1);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 2){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y2);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 3){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y3);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 4){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y4);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 5){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y5);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 6){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y6);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}				
				if(gauge == 7){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y7);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 8){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y8);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
				if(gauge == 9){
					w = AppManager.getInstance().getBitmap(R.drawable.bar_y9);
					w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
				}
			}else {
				// ��ġ
				w = AppManager.getInstance().getBitmap(R.drawable.bar_y10);
				w = Bitmap.createBitmap(w, 0, 0, w.getWidth(), w.getHeight(), matrix, true);
			}
		}
		
		return w;
	}
}