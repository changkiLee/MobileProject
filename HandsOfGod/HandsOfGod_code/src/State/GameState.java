package State;

import java.util.ArrayList;
import java.util.Random;
import edu.dongguk.mme.HOG.HOGActivity;
import edu.dongguk.mme.HOG.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import FrameWork.AppManager;
import FrameWork.IState;
import FrameWork.SoundManager;
import Game.Game_Collision;
import Game.Game_Color;
import Game.Game_Penalty;
import Game.Game_Save;
import Game.Game_Touch;
import Game.Game_initialization;
import Game.Wall_D;
import Game.Wall_L;
import Game.Wall_R;
import Game.Wall_U;
import Game.Game_Block;

public class GameState implements IState{
	// �̹��� ����
	Bitmap game_base;
	Bitmap red_bt;
	Bitmap yellow_bt;
	Bitmap blue_bt;
	Bitmap green_bt;
	
	Bitmap red_bt_drag;
	Bitmap yellow_bt_drag;
	Bitmap blue_bt_drag;
	Bitmap green_bt_drag;
	
	// �ؽ�Ʈ ����z
	Paint p = new Paint();
	Paint s = new Paint();
	
	// ���� ���� ����
	private static final int LEVEL = 0;
	private static final int START = 1;
	private static final int OVER = 2;
	private static final int PAUSE = 3;
	public static int gs_state = LEVEL;
	
	// ����
	private int level = 1;
	private double speed = 1100;
	
	// ������ �޺�, ������ ��� ��, �̸�
	private int score = 0;
	private int combo = -1;
	private int max_combo = 0;
	private int count = 50;
	private String name;
	
	// �ð� ����
	long Time = System.currentTimeMillis();
	
	// ��ġ ��ǥ(x, y)
	private int _x;
	private int _y;
	
	// ��(�����¿�)
	private Wall_L wall_l = new Wall_L();
	private Wall_R wall_r = new Wall_R();
	private Wall_U wall_u = new Wall_U();
	private Wall_D wall_d = new Wall_D();
	
	// �� ����
	private Rect Wall_l = new Rect();
	private Rect Wall_r = new Rect();
	private Rect Wall_u = new Rect();
	private Rect Wall_d = new Rect();
	
	// �� ��ġ ����
	private Rect touch_b = new Rect();
	private Rect touch_y = new Rect();
	private Rect touch_r = new Rect();
	private Rect touch_g = new Rect();
	
	// �� ��ǥ ��Ʈ��
	private int wl_x = 0, wl_y = 252;
	private int wr_x = 440, wr_y = 252;
	private int wu_x = 43, wu_y = 210;
	private int wd_x = 43, wd_y = 651;
		
	// �� ��ǥ ȸ����
	private int b_x = 0, b_y = 253;
	private int y_x = 440, y_y = 253;
	private int r_x = 42, r_y = 210;
	private int g_x = 42, g_y = 651;

	// �� �̹��� ȸ���� ���
	private int t = 0;
	private int turn;

	// �� ������
	private int w_l_size = 42;
	private int w_b_size = 396;
	
	// �� ������
	private int gauge_l = 0;
	private int gauge_r = 0;
	private int gauge_u = 0;
	private int gauge_d = 0;
	
	// ���� Ȯ��
	private int wl_bc = 1;
	private int wr_yc = 2;
	private int wu_rc = 3;
	private int wd_gc = 4;
	
	// ���
	ArrayList <Game_Block> G_Block;
	private int block_Size = 92;	// ��� ������
	private int Start_X = -33;		// ��� ���� ��ǥ(x, y) 
	private int Start_Y = 179;
	private int block_num = 16;
	private int block_row = 4;
	private Random Rand = new Random();
	private int Random_pos;			//����� ��ġ�� �����ϰ� �޾��ش�
	private int Random_Col;			//����� ���� �����ϰ� �޾��ش�
	
	// ��� �ʱ�ȭ
	private Game_initialization ini = new Game_initialization(); 
	
	// ��ġ
	private Game_Touch Touch = new Game_Touch();
	
	// �浹
	private Game_Collision Col = new Game_Collision();
	
	// ��
	private Game_Color color = new Game_Color();
	
	// ���Ƽ
	private Game_Penalty Penalty = new Game_Penalty();
	
	// ����
	public Game_Save save = new Game_Save();
	
	// ����
	public static final int BUTTON = 1;
	public static final int COLLISION_FALSE = 2;
	public static final int COLLISION_TRUE = 3;
	public static final int GAME_OVER = 4;
	public static final int LEVEL_UP = 5;
	public static final int BAR_TOUCH = 6;

	public void Init() {		
		gs_state = LEVEL;
		
		// ���� ����
		SoundManager.getInstatnce().addSound(BUTTON, R.raw.button);
		SoundManager.getInstatnce().addSound(COLLISION_FALSE, R.raw.collision_false);
		SoundManager.getInstatnce().addSound(COLLISION_TRUE, R.raw.collision_true);
		SoundManager.getInstatnce().addSound(GAME_OVER, R.raw.game_over);
		SoundManager.getInstatnce().addSound(LEVEL_UP, R.raw.level_up);
		SoundManager.getInstatnce().addSound(BAR_TOUCH, R.raw.bar_touch);
		
		// ���
		game_base = AppManager.getInstance().getBitmap(R.drawable.game_base);
		
		// �ؽ�Ʈ
		p.setTextSize(40);
		p.setAntiAlias(true);
		p.setColor(Color.BLACK);
		s.setTextSize(30);
		s.setAntiAlias(true);
		s.setColor(Color.WHITE);
		
		// ���
		red_bt = AppManager.getInstance().getBitmap(R.drawable.red_bt);
		yellow_bt = AppManager.getInstance().getBitmap(R.drawable.yellow_bt);
		blue_bt = AppManager.getInstance().getBitmap(R.drawable.blue_bt);
		green_bt = AppManager.getInstance().getBitmap(R.drawable.green_bt);
		
		red_bt_drag = AppManager.getInstance().getBitmap(R.drawable.red_bt_drag);
		yellow_bt_drag = AppManager.getInstance().getBitmap(R.drawable.yellow_bt_drag);
		blue_bt_drag = AppManager.getInstance().getBitmap(R.drawable.blue_bt_drag);
		green_bt_drag = AppManager.getInstance().getBitmap(R.drawable.green_bt_drag);
		
		G_Block = new ArrayList<Game_Block>();		
		
		// ����� ��ǥ�� �޾��� ����
		int l, r, t, b;
		
		// ��� 16�� ����
		for(int i = 0; i < block_num; i++) 
		{
			G_Block.add(new Game_Block());
		}
	
		// ��� 16�� ��ǥ ����
		int j = 0;
		int x = 0;		
		for(int i = 0; i < block_num; i++) 
		{
			if(i % block_row == 0)
			{
				j++;
				x = 0;
			}
			
			x++;
			
			l = Start_X + (block_Size * x);
			r = l + block_Size;
			t = Start_Y + (block_Size * j);
			b = t + block_Size;
			
			G_Block.get(i).block_position(l, t, r, b);
		}
		
		// �� ���� ����
		Wall_l = new Rect(wl_x, wl_y, wl_x + w_l_size, wl_y + w_b_size);
		Wall_r = new Rect(wr_x, wr_y, wr_x - w_l_size, wr_y + w_b_size);
		Wall_u = new Rect(wu_x, wu_y, wu_x + w_b_size, wu_y + w_l_size);
		Wall_d = new Rect(wd_x, wd_y, wd_x + w_b_size, wd_y - w_l_size);
	}

	public void Destroy() {
		
	}

	public void Update() {		
		// ���� ���� : ����
		if(gs_state == START){	
			// ��� ��ġ ����
			Random_pos = Rand.nextInt(16);
			
			// ��� �� ����
			if(!G_Block.get(Random_pos).block_Check_view)
			{
				Random_Col = Rand.nextInt(4) + 1;
				G_Block.get(Random_pos).block_Check_color = Random_Col;
			}		
			
			if(!G_Block.get(Random_pos).block_Check_view)
			{
				// ��� ���� �ð� ����
				if(System.currentTimeMillis() - Time > speed){
					Time = System.currentTimeMillis();				
					
					G_Block.get(Random_pos).block_Give();
				}
			}
						
			// ��� �浹 ����			
			for(int i = 0; i < block_num; i++)
			{
				// ����� ���⿡ ���� �Ǵ�
				switch(G_Block.get(i).block_direction)
				{
				// 1: ���� ���� �浹�� �Ͼ ���
				case 1:					
					G_Block.get(i).block_Collision = Col.check_Collision(G_Block.get(i).Block, Wall_l);
					// ���� ���� ���� �浹�� �Ͼ ���(�� ������ ����, �޺�, ���� ���)
					if(G_Block.get(i).block_Collision){
						if(color.Game_color_check(G_Block.get(i).block_Check_color, wl_bc))
						{
							gauge_l++;					
							
							combo++;
							score = score + 1 + combo;
							if(max_combo < combo){
								max_combo = combo;
							}
							
							count--;
							SoundManager.getInstatnce().play(COLLISION_TRUE);
						}
						else
						{ 							
							// �ٸ� ���� ���� �浹�� �Ͼ ���
							// �� �� ��ȣ ��ȯ, �޺� 0
							combo = 0;
							
							int tmp;
							tmp = wl_bc;
							wl_bc = wd_gc;
							wd_gc = wr_yc;
							wr_yc = wu_rc;
							wu_rc = tmp;							
							
							// �̹��� ��� ��ǥ��, �������� ��ȯ �� ȸ��
							int t_x, t_y;
							t_x = r_x;
							t_y = r_y;
							r_x = y_x;
							r_y = y_y;
							y_x = g_x;
							y_y = g_y;
							g_x = b_x;
							g_y = b_y;
							b_x = t_x;
							b_y = t_y;
							
							int gauge_t;
							gauge_t = gauge_l;
							gauge_l = gauge_d;
							gauge_d = gauge_r;
							gauge_r = gauge_u;
							gauge_u = gauge_t;
							
							t++;
							turn = t%4;
							
							Penalty.block_Penalty(G_Block);
							SoundManager.getInstatnce().play(COLLISION_FALSE);
							
						}
					}
					break;
				// 2: ������ ���� �浹�� �Ͼ ���
				case 2:
					G_Block.get(i).block_Collision = Col.check_Collision(G_Block.get(i).Block, Wall_r);
					// ���� ���� ���� �浹�� �Ͼ ���(�� ������ ����, �޺�, ���� ���)
					if(G_Block.get(i).block_Collision){
						if(color.Game_color_check(G_Block.get(i).block_Check_color, wr_yc))
						{
							gauge_r++;
							
							combo++;
							score = score + 1 + combo;						
							if(max_combo < combo){
								max_combo = combo;
							}
							
							count--;
							SoundManager.getInstatnce().play(COLLISION_TRUE);
						}
						else
						{ 
							// �ٸ� ���� ���� �浹�� �Ͼ ���
							// �� �� ��ȣ ��ȯ, �޺� 0
							combo = 0;	
							
							int tmp;
							tmp = wl_bc;
							wl_bc = wd_gc;
							wd_gc = wr_yc;
							wr_yc = wu_rc;
							wu_rc = tmp;													
							
							// �̹��� ��� ��ǥ��, �������� ��ȯ �� ȸ��
							int t_x, t_y;
							t_x = r_x;
							t_y = r_y;
							r_x = y_x;
							r_y = y_y;
							y_x = g_x;
							y_y = g_y;
							g_x = b_x;
							g_y = b_y;
							b_x = t_x;
							b_y = t_y;
							
							int gauge_t;
							gauge_t = gauge_l;
							gauge_l = gauge_d;
							gauge_d = gauge_r;
							gauge_r = gauge_u;
							gauge_u = gauge_t;
							
							t++;
							turn = t%4;
							
							Penalty.block_Penalty(G_Block);
							SoundManager.getInstatnce().play(COLLISION_FALSE);
						}
					}
					break;				
				// 3: ���� ���� �浹�� �Ͼ ���
				case 3:
					G_Block.get(i).block_Collision = Col.check_Collision(G_Block.get(i).Block, Wall_u);
					// ���� ���� ���� �浹�� �Ͼ ���(�� ������ ����, �޺�, ���� ���)			
					if(G_Block.get(i).block_Collision){
						if(color.Game_color_check(G_Block.get(i).block_Check_color, wu_rc))
						{
							gauge_u++;				
							
							combo++;
							score = score + 1 + combo;						
							if(max_combo < combo){
								max_combo = combo;
							}
							
							count--;
							SoundManager.getInstatnce().play(COLLISION_TRUE);
						}
						else
						{ 	
							// �ٸ� ���� ���� �浹�� �Ͼ ���
							// �� �� ��ȣ ��ȯ, �޺� 0
							combo = 0;
							
							int tmp;
							tmp = wl_bc;
							wl_bc = wd_gc;
							wd_gc = wr_yc;
							wr_yc = wu_rc;
							wu_rc = tmp;							
							
							// �̹��� ��� ��ǥ��, �������� ��ȯ �� ȸ��
							int t_x, t_y;
							t_x = r_x;
							t_y = r_y;
							r_x = y_x;
							r_y = y_y;
							y_x = g_x;
							y_y = g_y;
							g_x = b_x;
							g_y = b_y;
							b_x = t_x;
							b_y = t_y;
							
							int gauge_t;
							gauge_t = gauge_l;
							gauge_l = gauge_d;
							gauge_d = gauge_r;
							gauge_r = gauge_u;
							gauge_u = gauge_t;
																					
							t++;
							turn = t%4;
							
							Penalty.block_Penalty(G_Block);
							SoundManager.getInstatnce().play(COLLISION_FALSE);
						}
					}
					break;
				// 4: �Ʒ��� ���� �浹�� �Ͼ ���
				case 4:
					G_Block.get(i).block_Collision = Col.check_Collision(G_Block.get(i).Block, Wall_d);
					// ���� ���� ���� �浹�� �Ͼ ���(�� ������ ����, �޺�, ���� ���)
					if(G_Block.get(i).block_Collision){
						if(color.Game_color_check(G_Block.get(i).block_Check_color, wd_gc))
						{
							gauge_d++;
							
							combo++;
							score = score + 1 + combo;
							if(max_combo < combo){
								max_combo = combo;
							}
							
							count--;
							SoundManager.getInstatnce().play(COLLISION_TRUE);
						}
						else
						{ 
							// �ٸ� ���� ���� �浹�� �Ͼ ���
							// �� �� ��ȣ ��ȯ, �޺� 0
							combo = 0;
							
							int tmp;
							tmp = wl_bc;
							wl_bc = wd_gc;
							wd_gc = wr_yc;
							wr_yc = wu_rc;
							wu_rc = tmp;
							
							// �̹��� ��� ��ǥ��, �������� ��ȯ �� ȸ��
							int t_x, t_y;
							t_x = r_x;
							t_y = r_y;
							r_x = y_x;
							r_y = y_y;
							y_x = g_x;
							y_y = g_y;
							g_x = b_x;
							g_y = b_y;
							b_x = t_x;
							b_y = t_y;
							
							int gauge_t;
							gauge_t = gauge_l;
							gauge_l = gauge_d;
							gauge_d = gauge_r;
							gauge_r = gauge_u;
							gauge_u = gauge_t;
																					
							t++;
							turn = t%4;
							
							Penalty.block_Penalty(G_Block);
							SoundManager.getInstatnce().play(COLLISION_FALSE);
						}
					}
				break;
					
				default:
					break;
				}
			}
			
			// �浹�̸� ��ġ �� �������� �ʱ�ȭ ���ش�
			int j = 0;
			int x = 0;
			int l, r, t, b;
			for(int i = 0; i < block_num; i++)
			{
				if(i % block_row == 0)
				{
					j++;
					x = 0;
				}
				
				x++;
				
				if(G_Block.get(i).block_Collision)
				{	
					l = Start_X + (block_Size * x);
					r = l + block_Size;
					t = Start_Y + (block_Size * j);
					b = t + block_Size;
					
					G_Block.get(i).block_position(l, t, r, b);
					G_Block.get(i).block_Check_touch = false;
					G_Block.get(i).block_Check_view = false;
					G_Block.get(i).block_direction = 0;
					G_Block.get(i).block_Collision = false;
					G_Block.get(i).block_Move = false;
				}
			}
			
			// ��� ��ġ�� �̵�
			for(int i = 0; i < block_num; i++) //����� �������� ������ ��ġ�� Ȯ�� ������ �����ش�
			{
				if(!G_Block.get(i).block_Move && G_Block.get(i).block_Check_view)// ��� ������ Ȯ��
				{
					G_Block.get(i).block_Check_T(Touch.x1, Touch.y1); // ���� ��ġ�Ǿ����� Ȯ��
						
						if(G_Block.get(i).block_Check_touch && Touch.up)// ��� ��ġ Ȯ��
						{
							G_Block.get(i).block_Drag(Touch.x1, Touch.y1, Touch.x2, Touch.y2); //�� ������ ����
							
							Touch.x1 = 0; 
							Touch.y1 = 0;
							Touch.x2 = 0;
							Touch.y2 = 0;
							
							if(G_Block.get(i).block_direction != 0) // ��� ������ ����������
								G_Block.get(i).block_Move = true; // ����� �����δ�
						}
				}
			}
			
			// ��� �̵�
			for(int i = 0; i < block_num; i++){
				G_Block.get(i).block_Move();
			}
			
			// ���� ���� ����
			int check_over = 0;
			for(int i = 0; i < block_num; i++){
				if(G_Block.get(i).block_Check_view){
					check_over++;
				}
			}
			if(check_over >= block_num){
				gs_state = OVER;
				SoundManager.getInstatnce().play(GAME_OVER);
			}
				
			// count + 10*(���� ���� - 1)���� �����ϸ� ������
			if(count < 0){
				level++;
				speed = speed - (750/(2*(level-1)));
				count = 50 + 20*(level-1);
				
				// ȭ�� ����
				ini.block_Initial(G_Block);
				gs_state = LEVEL;
				
				SoundManager.getInstatnce().play(LEVEL_UP);
			}
		}
	}
	
	public void Render(Canvas canvas) {		
		// ��� �׸���
		canvas.drawBitmap(game_base, 0, 0, null);		
				
		// �� �׸���(�ʱⰪ 0)
		if(turn == 0){
			canvas.drawBitmap(wall_l.plus_gauge(gauge_l, turn), b_x, b_y, null);
			canvas.drawBitmap(wall_r.plus_gauge(gauge_r, turn), y_x, y_y, null);
			canvas.drawBitmap(wall_u.plus_gauge(gauge_u, turn), r_x, r_y, null);
			canvas.drawBitmap(wall_d.plus_gauge(gauge_d, turn), g_x, g_y, null);
		}
		if(turn == 1){
			canvas.drawBitmap(wall_l.plus_gauge(gauge_u, turn), b_x, b_y, null);
			canvas.drawBitmap(wall_r.plus_gauge(gauge_d, turn), y_x, y_y, null);
			canvas.drawBitmap(wall_u.plus_gauge(gauge_r, turn), r_x, r_y, null);
			canvas.drawBitmap(wall_d.plus_gauge(gauge_l, turn), g_x, g_y, null);
		}
		if(turn == 2){
			canvas.drawBitmap(wall_l.plus_gauge(gauge_r, turn), b_x, b_y, null);
			canvas.drawBitmap(wall_r.plus_gauge(gauge_l, turn), y_x, y_y, null);
			canvas.drawBitmap(wall_u.plus_gauge(gauge_d, turn), r_x, r_y, null);
			canvas.drawBitmap(wall_d.plus_gauge(gauge_u, turn), g_x, g_y, null);
		}
		if(turn == 3){
			canvas.drawBitmap(wall_l.plus_gauge(gauge_d, turn), b_x, b_y, null);
			canvas.drawBitmap(wall_r.plus_gauge(gauge_u, turn), y_x, y_y, null);
			canvas.drawBitmap(wall_u.plus_gauge(gauge_l, turn), r_x, r_y, null);
			canvas.drawBitmap(wall_d.plus_gauge(gauge_r, turn), g_x, g_y, null);
		}
		
		// �Ͻ����� ����
		if(gs_state == PAUSE)
		{
			canvas.drawText("PAUSE", 180, 465, p);
		}
		
		// ���� ���� : ���� (�׸���)
		if(gs_state == LEVEL){
			canvas.drawText("LEVEL " + level, 170, 465, p);
		}
		
		// ���� ���� : ���� (�׸���)
		if(gs_state == START){
			// ����, ����, �޺�, �ִ� �޺� �׸���
			canvas.drawText("" + level, 367, 35, s);
			canvas.drawText("" + max_combo, 270, 95, s);
			canvas.drawText("" + score, 270, 189, s);
			if(combo == -1)
			{
				canvas.drawText("0", 270, 142, s);
			}
			else {
				canvas.drawText("" + combo, 270, 142, s);
			}
			
			// ��� �׸���
			for(int i = 0; i < block_num; i++)
			{
				if(G_Block.get(i).block_Check_view)
				{
					// (����)���� ���� ��� �׸���					
					switch(G_Block.get(i).block_Check_color)
					{
					case 1:
						if(G_Block.get(i).block_Move)
							canvas.drawBitmap(blue_bt_drag, G_Block.get(i).Block.left, G_Block.get(i).Block.top, null);
						else
							canvas.drawBitmap(blue_bt, G_Block.get(i).Block.left, G_Block.get(i).Block.top, null);
						break;
					
					case 2:
						if(G_Block.get(i).block_Move)
							canvas.drawBitmap(yellow_bt_drag, G_Block.get(i).Block.left, G_Block.get(i).Block.top, null);
						else
							canvas.drawBitmap(yellow_bt, G_Block.get(i).Block.left, G_Block.get(i).Block.top, null);
						break;
					
					case 3:
						if(G_Block.get(i).block_Move)
							canvas.drawBitmap(red_bt_drag, G_Block.get(i).Block.left, G_Block.get(i).Block.top, null);
						else
							canvas.drawBitmap(red_bt, G_Block.get(i).Block.left, G_Block.get(i).Block.top, null);
						break;
					
					case 4:
						if(G_Block.get(i).block_Move)
							canvas.drawBitmap(green_bt_drag, G_Block.get(i).Block.left, G_Block.get(i).Block.top, null);
						else
							canvas.drawBitmap(green_bt, G_Block.get(i).Block.left, G_Block.get(i).Block.top, null);
						break;
					}
					
				}
			}
		}
		
		// ���� ���� : ���� ���� (�׸���)
		if(gs_state == OVER){
			canvas.drawText("Game Over!", 135, 465, p);
		}	
		
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return false;
	}

	public boolean onTouchEvent(MotionEvent event) {
		// ��ġ�� ���� ��ǥ �о����
		_x = (int)event.getX();
		_y = (int)event.getY();
				
		// ���� ȭ�� ��ġ
		if(gs_state == LEVEL){
			if(System.currentTimeMillis() - Time >= 1500){
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					gs_state = START;
				}
			}			
		}
		
		// �Ͻ� ���� ȭ�� ��ġ��
		if(gs_state == PAUSE){
			if(event.getAction() == MotionEvent.ACTION_DOWN){
				gs_state = START;
				SoundManager.getInstatnce().play(BUTTON);
			}
		}
		
		// ���� ����
		if(gs_state == START)
		{
			// �Ͻ� ���� ��ư ��ġ��
			Rect button_back = new Rect(0,0,157,45);
			if(button_back.contains(_x, _y)){
				gs_state = PAUSE;
				SoundManager.getInstatnce().play(BUTTON);
			}
			
			// ���Ӻ���� ��ġ�� ����Ѵ�
			Touch.onTouchEvent(event); 
			
			// ����ġ	
			if(event.getAction() == MotionEvent.ACTION_DOWN){				
				// ����ġ ���� �ʱ�ȭ
				if(turn == 0 || turn == 2){
					// ��, ��
					touch_b = new Rect(b_x, b_y, b_x + 42, b_y + 396);
					touch_y = new Rect(y_x, y_y, y_x + 42, y_y + 396);
					// ��, ��
					touch_r = new Rect(r_x, r_y, r_x + 396, r_y + 42);
					touch_g = new Rect(g_x, g_y, g_x + 396, g_y + 42);	
				}
				if(turn == 1 || turn == 3){
					// ��, ��
					touch_b = new Rect(b_x, b_y, b_x + 396, b_y + 42);
					touch_y = new Rect(y_x, y_y, y_x + 396, y_y + 42);
					// ��, ��
					touch_r = new Rect(r_x, r_y, r_x + 42, r_y + 396);
					touch_g = new Rect(g_x, g_y, g_x + 42, g_y + 396);
				}			
				// �������� 10 ���� Ŭ �� ����ġ �� ���(4����) ~ �������
				if(turn == 0){
					if(gauge_l >= 10 && touch_b.contains(_x, _y)){
						SoundManager.getInstatnce().play(BAR_TOUCH);
						gauge_l = 0;
						score += 100;						
					}
					else if(gauge_r >= 10 && touch_y.contains(_x, _y)){
						SoundManager.getInstatnce().play(BAR_TOUCH);
						gauge_r = 0;
						score += 100;
					}			
					else if(gauge_u >= 10 && touch_r.contains(_x, _y)){
						SoundManager.getInstatnce().play(BAR_TOUCH);
						gauge_u = 0;
						score += 100;
					}
					else if(gauge_d >= 10 && touch_g.contains(_x, _y)){
						SoundManager.getInstatnce().play(BAR_TOUCH);
						gauge_d = 0;
						score += 100;
					}
				}
				if(turn == 1){
					if(gauge_u >= 10 && touch_b.contains(_x, _y)){
						SoundManager.getInstatnce().play(BAR_TOUCH);
						gauge_u = 0;
						score += 100;
					}			
					else if(gauge_d >= 10 && touch_y.contains(_x, _y)){
						SoundManager.getInstatnce().play(BAR_TOUCH);
						gauge_d = 0;
						score += 100;
					}			
					else if(gauge_r >= 10 && touch_r.contains(_x, _y)){
						SoundManager.getInstatnce().play(BAR_TOUCH);
						gauge_r = 0;
						score += 100;
					}
					else if(gauge_l >= 10 && touch_g.contains(_x, _y)){
						SoundManager.getInstatnce().play(BAR_TOUCH);
						gauge_l = 0;
						score += 100;
					}
				}
				if(turn == 2){
					if(gauge_r >= 10 && touch_b.contains(_x, _y)){
						SoundManager.getInstatnce().play(BAR_TOUCH);
						gauge_r = 0;
						score += 100;
					}			
					else if(gauge_l >= 10 && touch_y.contains(_x, _y)){
						SoundManager.getInstatnce().play(BAR_TOUCH);
						gauge_l = 0;
						score += 100;
					}			
					else if(gauge_d >= 10 && touch_r.contains(_x, _y)){
						SoundManager.getInstatnce().play(BAR_TOUCH);
						gauge_d = 0;
						score += 100;
					}
					else if(gauge_u >= 10 && touch_g.contains(_x, _y)){
						SoundManager.getInstatnce().play(BAR_TOUCH);
						gauge_u = 0;
						score += 100;
					}
				}
				if(turn == 3){
					if(gauge_d >= 10 && touch_b.contains(_x, _y)){
						SoundManager.getInstatnce().play(BAR_TOUCH);
						gauge_d = 0;
						score += 100;
					}			
					else if(gauge_u >= 10 && touch_y.contains(_x, _y)){
						SoundManager.getInstatnce().play(BAR_TOUCH);
						gauge_u = 0;
						score += 100;
					}			
					else if(gauge_l >= 10 && touch_r.contains(_x, _y)){
						SoundManager.getInstatnce().play(BAR_TOUCH);
						gauge_l = 0;
						score += 100;
					}
					else if(gauge_r >= 10 && touch_g.contains(_x, _y)){
						SoundManager.getInstatnce().play(BAR_TOUCH);
						gauge_r = 0;
						score += 100;
					}
				}
			}
		}
		
		// ���� ����
		if(gs_state == OVER){
			if(System.currentTimeMillis() - Time >= 1500){
				if(event.getAction() == MotionEvent.ACTION_DOWN){	           
					// �̸� �����ϴ� �ؽ�Ʈ �ڽ� ����
					LayoutInflater inflater = (LayoutInflater) AppManager.getInstance().getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					View layout = inflater.inflate(R.layout.custom_dialog,(ViewGroup)AppManager.getInstance().getActivity().findViewById(R.id.layout_root));											

					AlertDialog.Builder aDialog = new AlertDialog.Builder(AppManager.getInstance().getActivity());
					aDialog.setTitle("GameOver");
					aDialog.setView(layout);

					final TextView tName = (TextView)layout.findViewById(R.id.output);
					final EditText eName = (EditText)layout.findViewById(R.id.input);
					
					// ���ڼ� �Է� ����(5�� ����)
					InputFilter[] inputFilter = new InputFilter[1];
			        inputFilter[0] = new InputFilter.LengthFilter(5);
			        eName.setFilters(inputFilter);
					
					// OK ��ư�� ������
					aDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// �̸� ����
							tName.setText(eName.getText().toString());
							name = tName.getText().toString();
							
							// ����, �޺�, ����, �̸� ��� �� ����Ȯ��
							save.Game_sc(score, max_combo, level, name);
							save.Check_save(1);
							
							HOGActivity st = new HOGActivity();
							st.G_State(1);
							
							// ��� ������Ʈ�� �̵�
							AppManager.getInstance().getGameView().ChangeGameState(new RankState());
						}
						});
					AlertDialog ad = aDialog.create();
					ad.show();
				}
			}			
		}
		
		return true;
	}
}
