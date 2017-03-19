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
	// 이미지 변수
	Bitmap game_base;
	Bitmap red_bt;
	Bitmap yellow_bt;
	Bitmap blue_bt;
	Bitmap green_bt;
	
	Bitmap red_bt_drag;
	Bitmap yellow_bt_drag;
	Bitmap blue_bt_drag;
	Bitmap green_bt_drag;
	
	// 텍스트 변수z
	Paint p = new Paint();
	Paint s = new Paint();
	
	// 게임 상태 변수
	private static final int LEVEL = 0;
	private static final int START = 1;
	private static final int OVER = 2;
	private static final int PAUSE = 3;
	public static int gs_state = LEVEL;
	
	// 레벨
	private int level = 1;
	private double speed = 1100;
	
	// 점수와 콤보, 제거한 블록 수, 이름
	private int score = 0;
	private int combo = -1;
	private int max_combo = 0;
	private int count = 50;
	private String name;
	
	// 시간 조절
	long Time = System.currentTimeMillis();
	
	// 터치 좌표(x, y)
	private int _x;
	private int _y;
	
	// 벽(상하좌우)
	private Wall_L wall_l = new Wall_L();
	private Wall_R wall_r = new Wall_R();
	private Wall_U wall_u = new Wall_U();
	private Wall_D wall_d = new Wall_D();
	
	// 벽 범위
	private Rect Wall_l = new Rect();
	private Rect Wall_r = new Rect();
	private Rect Wall_u = new Rect();
	private Rect Wall_d = new Rect();
	
	// 벽 터치 범위
	private Rect touch_b = new Rect();
	private Rect touch_y = new Rect();
	private Rect touch_r = new Rect();
	private Rect touch_g = new Rect();
	
	// 벽 좌표 렉트용
	private int wl_x = 0, wl_y = 252;
	private int wr_x = 440, wr_y = 252;
	private int wu_x = 43, wu_y = 210;
	private int wd_x = 43, wd_y = 651;
		
	// 벽 좌표 회전용
	private int b_x = 0, b_y = 253;
	private int y_x = 440, y_y = 253;
	private int r_x = 42, r_y = 210;
	private int g_x = 42, g_y = 651;

	// 벽 이미지 회전의 경우
	private int t = 0;
	private int turn;

	// 벽 사이즈
	private int w_l_size = 42;
	private int w_b_size = 396;
	
	// 벽 게이지
	private int gauge_l = 0;
	private int gauge_r = 0;
	private int gauge_u = 0;
	private int gauge_d = 0;
	
	// 벽색 확인
	private int wl_bc = 1;
	private int wr_yc = 2;
	private int wu_rc = 3;
	private int wd_gc = 4;
	
	// 블록
	ArrayList <Game_Block> G_Block;
	private int block_Size = 92;	// 블록 사이즈
	private int Start_X = -33;		// 블록 시작 좌표(x, y) 
	private int Start_Y = 179;
	private int block_num = 16;
	private int block_row = 4;
	private Random Rand = new Random();
	private int Random_pos;			//블록의 위치를 랜덤하게 받아준다
	private int Random_Col;			//블록의 색을 랜덤하게 받아준다
	
	// 블록 초기화
	private Game_initialization ini = new Game_initialization(); 
	
	// 터치
	private Game_Touch Touch = new Game_Touch();
	
	// 충돌
	private Game_Collision Col = new Game_Collision();
	
	// 색
	private Game_Color color = new Game_Color();
	
	// 페널티
	private Game_Penalty Penalty = new Game_Penalty();
	
	// 점수
	public Game_Save save = new Game_Save();
	
	// 사운드
	public static final int BUTTON = 1;
	public static final int COLLISION_FALSE = 2;
	public static final int COLLISION_TRUE = 3;
	public static final int GAME_OVER = 4;
	public static final int LEVEL_UP = 5;
	public static final int BAR_TOUCH = 6;

	public void Init() {		
		gs_state = LEVEL;
		
		// 사운드 설정
		SoundManager.getInstatnce().addSound(BUTTON, R.raw.button);
		SoundManager.getInstatnce().addSound(COLLISION_FALSE, R.raw.collision_false);
		SoundManager.getInstatnce().addSound(COLLISION_TRUE, R.raw.collision_true);
		SoundManager.getInstatnce().addSound(GAME_OVER, R.raw.game_over);
		SoundManager.getInstatnce().addSound(LEVEL_UP, R.raw.level_up);
		SoundManager.getInstatnce().addSound(BAR_TOUCH, R.raw.bar_touch);
		
		// 배경
		game_base = AppManager.getInstance().getBitmap(R.drawable.game_base);
		
		// 텍스트
		p.setTextSize(40);
		p.setAntiAlias(true);
		p.setColor(Color.BLACK);
		s.setTextSize(30);
		s.setAntiAlias(true);
		s.setColor(Color.WHITE);
		
		// 블록
		red_bt = AppManager.getInstance().getBitmap(R.drawable.red_bt);
		yellow_bt = AppManager.getInstance().getBitmap(R.drawable.yellow_bt);
		blue_bt = AppManager.getInstance().getBitmap(R.drawable.blue_bt);
		green_bt = AppManager.getInstance().getBitmap(R.drawable.green_bt);
		
		red_bt_drag = AppManager.getInstance().getBitmap(R.drawable.red_bt_drag);
		yellow_bt_drag = AppManager.getInstance().getBitmap(R.drawable.yellow_bt_drag);
		blue_bt_drag = AppManager.getInstance().getBitmap(R.drawable.blue_bt_drag);
		green_bt_drag = AppManager.getInstance().getBitmap(R.drawable.green_bt_drag);
		
		G_Block = new ArrayList<Game_Block>();		
		
		// 블록의 좌표를 받아줄 변수
		int l, r, t, b;
		
		// 블록 16개 생성
		for(int i = 0; i < block_num; i++) 
		{
			G_Block.add(new Game_Block());
		}
	
		// 블록 16개 좌표 설정
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
		
		// 벽 범위 설정
		Wall_l = new Rect(wl_x, wl_y, wl_x + w_l_size, wl_y + w_b_size);
		Wall_r = new Rect(wr_x, wr_y, wr_x - w_l_size, wr_y + w_b_size);
		Wall_u = new Rect(wu_x, wu_y, wu_x + w_b_size, wu_y + w_l_size);
		Wall_d = new Rect(wd_x, wd_y, wd_x + w_b_size, wd_y - w_l_size);
	}

	public void Destroy() {
		
	}

	public void Update() {		
		// 게임 상태 : 시작
		if(gs_state == START){	
			// 블록 위치 랜덤
			Random_pos = Rand.nextInt(16);
			
			// 블록 색 랜덤
			if(!G_Block.get(Random_pos).block_Check_view)
			{
				Random_Col = Rand.nextInt(4) + 1;
				G_Block.get(Random_pos).block_Check_color = Random_Col;
			}		
			
			if(!G_Block.get(Random_pos).block_Check_view)
			{
				// 블록 생성 시간 조절
				if(System.currentTimeMillis() - Time > speed){
					Time = System.currentTimeMillis();				
					
					G_Block.get(Random_pos).block_Give();
				}
			}
						
			// 블록 충돌 판정			
			for(int i = 0; i < block_num; i++)
			{
				// 블록의 방향에 따라서 판단
				switch(G_Block.get(i).block_direction)
				{
				// 1: 왼쪽 벽에 충돌이 일어난 경우
				case 1:					
					G_Block.get(i).block_Collision = Col.check_Collision(G_Block.get(i).Block, Wall_l);
					// 같은 색깔 벽에 충돌이 일어난 경우(벽 게이지 증가, 콤보, 점수 계산)
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
							// 다른 색깔 벽에 충돌이 일어난 경우
							// 벽 색 번호 교환, 콤보 0
							combo = 0;
							
							int tmp;
							tmp = wl_bc;
							wl_bc = wd_gc;
							wd_gc = wr_yc;
							wr_yc = wu_rc;
							wu_rc = tmp;							
							
							// 이미지 출력 좌표값, 게이지값 교환 및 회전
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
				// 2: 오른쪽 벽에 충돌이 일어난 경우
				case 2:
					G_Block.get(i).block_Collision = Col.check_Collision(G_Block.get(i).Block, Wall_r);
					// 같은 색깔 벽에 충돌이 일어난 경우(벽 게이지 증가, 콤보, 점수 계산)
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
							// 다른 색깔 벽에 충돌이 일어난 경우
							// 벽 색 번호 교환, 콤보 0
							combo = 0;	
							
							int tmp;
							tmp = wl_bc;
							wl_bc = wd_gc;
							wd_gc = wr_yc;
							wr_yc = wu_rc;
							wu_rc = tmp;													
							
							// 이미지 출력 좌표값, 게이지값 교환 및 회전
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
				// 3: 위쪽 벽에 충돌이 일어난 경우
				case 3:
					G_Block.get(i).block_Collision = Col.check_Collision(G_Block.get(i).Block, Wall_u);
					// 같은 색깔 벽에 충돌이 일어난 경우(벽 게이지 증가, 콤보, 점수 계산)			
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
							// 다른 색깔 벽에 충돌이 일어난 경우
							// 벽 색 번호 교환, 콤보 0
							combo = 0;
							
							int tmp;
							tmp = wl_bc;
							wl_bc = wd_gc;
							wd_gc = wr_yc;
							wr_yc = wu_rc;
							wu_rc = tmp;							
							
							// 이미지 출력 좌표값, 게이지값 교환 및 회전
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
				// 4: 아래쪽 벽에 충돌이 일어난 경우
				case 4:
					G_Block.get(i).block_Collision = Col.check_Collision(G_Block.get(i).Block, Wall_d);
					// 같은 색깔 벽에 충돌이 일어난 경우(벽 게이지 증가, 콤보, 점수 계산)
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
							// 다른 색깔 벽에 충돌이 일어난 경우
							// 벽 색 번호 교환, 콤보 0
							combo = 0;
							
							int tmp;
							tmp = wl_bc;
							wl_bc = wd_gc;
							wd_gc = wr_yc;
							wr_yc = wu_rc;
							wu_rc = tmp;
							
							// 이미지 출력 좌표값, 게이지값 교환 및 회전
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
			
			// 충돌이면 위치 및 정보등을 초기화 해준다
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
			
			// 블록 터치와 이동
			for(int i = 0; i < block_num; i++) //블록이 움직이지 않을때 터치를 확인 방향을 정해준다
			{
				if(!G_Block.get(i).block_Move && G_Block.get(i).block_Check_view)// 블록 움직임 확인
				{
					G_Block.get(i).block_Check_T(Touch.x1, Touch.y1); // 블럭이 터치되었는지 확인
						
						if(G_Block.get(i).block_Check_touch && Touch.up)// 블록 터치 확인
						{
							G_Block.get(i).block_Drag(Touch.x1, Touch.y1, Touch.x2, Touch.y2); //블럭 방향을 지정
							
							Touch.x1 = 0; 
							Touch.y1 = 0;
							Touch.x2 = 0;
							Touch.y2 = 0;
							
							if(G_Block.get(i).block_direction != 0) // 블록 방향이 정해졌을시
								G_Block.get(i).block_Move = true; // 블록은 움직인다
						}
				}
			}
			
			// 블록 이동
			for(int i = 0; i < block_num; i++){
				G_Block.get(i).block_Move();
			}
			
			// 게임 오버 판정
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
				
			// count + 10*(현재 레벨 - 1)개를 제거하면 레벨업
			if(count < 0){
				level++;
				speed = speed - (750/(2*(level-1)));
				count = 50 + 20*(level-1);
				
				// 화면 정리
				ini.block_Initial(G_Block);
				gs_state = LEVEL;
				
				SoundManager.getInstatnce().play(LEVEL_UP);
			}
		}
	}
	
	public void Render(Canvas canvas) {		
		// 배경 그리기
		canvas.drawBitmap(game_base, 0, 0, null);		
				
		// 벽 그리기(초기값 0)
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
		
		// 일시정지 상태
		if(gs_state == PAUSE)
		{
			canvas.drawText("PAUSE", 180, 465, p);
		}
		
		// 레벨 상태 : 레벨 (그리기)
		if(gs_state == LEVEL){
			canvas.drawText("LEVEL " + level, 170, 465, p);
		}
		
		// 게임 상태 : 시작 (그리기)
		if(gs_state == START){
			// 레벨, 점수, 콤보, 최대 콤보 그리기
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
			
			// 블록 그리기
			for(int i = 0; i < block_num; i++)
			{
				if(G_Block.get(i).block_Check_view)
				{
					// (랜덤)색깔에 따라 블록 그리기					
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
		
		// 게임 상태 : 게임 오버 (그리기)
		if(gs_state == OVER){
			canvas.drawText("Game Over!", 135, 465, p);
		}	
		
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return false;
	}

	public boolean onTouchEvent(MotionEvent event) {
		// 터치된 곳의 좌표 읽어오기
		_x = (int)event.getX();
		_y = (int)event.getY();
				
		// 레벨 화면 터치
		if(gs_state == LEVEL){
			if(System.currentTimeMillis() - Time >= 1500){
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					gs_state = START;
				}
			}			
		}
		
		// 일시 정지 화면 터치시
		if(gs_state == PAUSE){
			if(event.getAction() == MotionEvent.ACTION_DOWN){
				gs_state = START;
				SoundManager.getInstatnce().play(BUTTON);
			}
		}
		
		// 게임 시작
		if(gs_state == START)
		{
			// 일시 정지 버튼 터치시
			Rect button_back = new Rect(0,0,157,45);
			if(button_back.contains(_x, _y)){
				gs_state = PAUSE;
				SoundManager.getInstatnce().play(BUTTON);
			}
			
			// 게임블록의 터치를 담당한다
			Touch.onTouchEvent(event); 
			
			// 벽터치	
			if(event.getAction() == MotionEvent.ACTION_DOWN){				
				// 벽터치 범위 초기화
				if(turn == 0 || turn == 2){
					// 좌, 우
					touch_b = new Rect(b_x, b_y, b_x + 42, b_y + 396);
					touch_y = new Rect(y_x, y_y, y_x + 42, y_y + 396);
					// 상, 하
					touch_r = new Rect(r_x, r_y, r_x + 396, r_y + 42);
					touch_g = new Rect(g_x, g_y, g_x + 396, g_y + 42);	
				}
				if(turn == 1 || turn == 3){
					// 상, 하
					touch_b = new Rect(b_x, b_y, b_x + 396, b_y + 42);
					touch_y = new Rect(y_x, y_y, y_x + 396, y_y + 42);
					// 좌, 우
					touch_r = new Rect(r_x, r_y, r_x + 42, r_y + 396);
					touch_g = new Rect(g_x, g_y, g_x + 42, g_y + 396);
				}			
				// 게이지가 10 보다 클 때 벽터치 한 경우(4가지) ~ 점수계산
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
		
		// 게임 오버
		if(gs_state == OVER){
			if(System.currentTimeMillis() - Time >= 1500){
				if(event.getAction() == MotionEvent.ACTION_DOWN){	           
					// 이름 저장하는 텍스트 박스 띄우기
					LayoutInflater inflater = (LayoutInflater) AppManager.getInstance().getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					View layout = inflater.inflate(R.layout.custom_dialog,(ViewGroup)AppManager.getInstance().getActivity().findViewById(R.id.layout_root));											

					AlertDialog.Builder aDialog = new AlertDialog.Builder(AppManager.getInstance().getActivity());
					aDialog.setTitle("GameOver");
					aDialog.setView(layout);

					final TextView tName = (TextView)layout.findViewById(R.id.output);
					final EditText eName = (EditText)layout.findViewById(R.id.input);
					
					// 글자수 입력 제한(5개 까지)
					InputFilter[] inputFilter = new InputFilter[1];
			        inputFilter[0] = new InputFilter.LengthFilter(5);
			        eName.setFilters(inputFilter);
					
					// OK 버튼을 누르면
					aDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// 이름 저장
							tName.setText(eName.getText().toString());
							name = tName.getText().toString();
							
							// 점수, 콤보, 레벨, 이름 기록 및 저장확인
							save.Game_sc(score, max_combo, level, name);
							save.Check_save(1);
							
							HOGActivity st = new HOGActivity();
							st.G_State(1);
							
							// 기록 스테이트로 이동
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
