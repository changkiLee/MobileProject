package Game;

import android.graphics.Rect;

public class Game_Block {
	
	public Rect Block;
	public boolean block_Check_view; //블록의 출력 판명
	public boolean block_Check_touch; //블록의 터치 확인
	public int block_direction; // 블록의 방향
	public boolean block_Collision; // 블록의 충돌확인
	public boolean block_Move; //블록의 움직임 확인
	public int block_Check_color; // 블록의 색 확인
	public int block_Speed = 0;
	
	Game_Drag Drag = new Game_Drag();
	
	public Game_Block()
	{
		Block = new Rect();
		block_Check_view = false;
		block_Check_touch = false;
		block_Collision = false;
		block_Move = false;
		block_direction = 0;
	}
	
	public void block_position(int left, int top, int right, int bottom) //블록의 좌표를 정해준다
	{	
		Block = new Rect(left, top, right, bottom);
	}

	public void block_Give() // 블록이 그려지게 한다
	{
		if(!block_Check_view)
			block_Check_view = true;
	}
	
	public void block_ToN(boolean block_view) // 어떤 위치의 블록이 출력 되있는지 확인
	{
		block_Check_view = block_view;
	}
	
	public void block_Check_T(int x1, int y1) // 블록이 Touch 되었는지 확인
	{	
		if(Block.contains(x1, y1))
		{
			block_Check_touch = true;
		}
		
		else
			block_Check_touch = false;
	}
	
	public void block_Drag(int x1, int y1, int x2, int y2) // 블록의 드래그된 방향을 확인한다
	{
		Drag.absolute_N(x1, y1, x2, y2);
		Drag.decide_Direction();
		
		block_direction = Drag.direction;
	}
	
	public void block_Move() // 블록이 움직이게 한다
	{
		block_Speed += 2;
		
		switch(block_direction)
		{
		case 1:	// 좌
			Block.offset(-15 - block_Speed, 0);
			break;
			
		case 2:	// 우
			Block.offset(15 + block_Speed, 0);
			break;
			
		case 3:	// 상
			Block.offset(0, -15 - block_Speed);
			break;
			
		case 4:	// 하
			Block.offset(0, 15 + block_Speed);
			break;
		
		default:
			Block.offset(0, 0);
			block_Speed = 0;
			break;
		}
	}
}
