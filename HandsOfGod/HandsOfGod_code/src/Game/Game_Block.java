package Game;

import android.graphics.Rect;

public class Game_Block {
	
	public Rect Block;
	public boolean block_Check_view; //����� ��� �Ǹ�
	public boolean block_Check_touch; //����� ��ġ Ȯ��
	public int block_direction; // ����� ����
	public boolean block_Collision; // ����� �浹Ȯ��
	public boolean block_Move; //����� ������ Ȯ��
	public int block_Check_color; // ����� �� Ȯ��
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
	
	public void block_position(int left, int top, int right, int bottom) //����� ��ǥ�� �����ش�
	{	
		Block = new Rect(left, top, right, bottom);
	}

	public void block_Give() // ����� �׷����� �Ѵ�
	{
		if(!block_Check_view)
			block_Check_view = true;
	}
	
	public void block_ToN(boolean block_view) // � ��ġ�� ����� ��� ���ִ��� Ȯ��
	{
		block_Check_view = block_view;
	}
	
	public void block_Check_T(int x1, int y1) // ����� Touch �Ǿ����� Ȯ��
	{	
		if(Block.contains(x1, y1))
		{
			block_Check_touch = true;
		}
		
		else
			block_Check_touch = false;
	}
	
	public void block_Drag(int x1, int y1, int x2, int y2) // ����� �巡�׵� ������ Ȯ���Ѵ�
	{
		Drag.absolute_N(x1, y1, x2, y2);
		Drag.decide_Direction();
		
		block_direction = Drag.direction;
	}
	
	public void block_Move() // ����� �����̰� �Ѵ�
	{
		block_Speed += 2;
		
		switch(block_direction)
		{
		case 1:	// ��
			Block.offset(-15 - block_Speed, 0);
			break;
			
		case 2:	// ��
			Block.offset(15 + block_Speed, 0);
			break;
			
		case 3:	// ��
			Block.offset(0, -15 - block_Speed);
			break;
			
		case 4:	// ��
			Block.offset(0, 15 + block_Speed);
			break;
		
		default:
			Block.offset(0, 0);
			block_Speed = 0;
			break;
		}
	}
}
