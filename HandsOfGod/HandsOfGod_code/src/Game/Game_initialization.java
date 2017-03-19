package Game;

import java.util.ArrayList;

public class Game_initialization {
	
	private int block_Size = 92;	// ��� ������
	private int Start_X = -33;		// ��� ���� ��ǥ(x, y) 
	private int Start_Y = 179;
	private int block_num = 16;
	
	int l,r,t,b;
	
	public void block_Initial(ArrayList<Game_Block> g_Block)
	{
		for(int i = 0; i < block_num; i++)
		{
			g_Block.get(i).block_Check_view = false; //����� ��� �Ǹ�
			g_Block.get(i).block_Check_touch = false; //����� ��ġ Ȯ��
			g_Block.get(i).block_direction = 0; // ����� ����
			g_Block.get(i).block_Collision = false; // ����� �浹Ȯ��
			g_Block.get(i).block_Move = false; //����� ������ Ȯ��
			g_Block.get(i).block_Check_color = 0; // ����� �� Ȯ��
		}
		
		int j = 0;
		int x = 0;		
		
		for(int i = 0; i < block_num; i++) 
		{
			if(i % 4 == 0)
			{
				j++;
				x = 0;
			}
			
			x++;
			
			l = Start_X + (block_Size * x);
			r = l + block_Size;
			t = Start_Y + (block_Size * j);
			b = t + block_Size;
			
			g_Block.get(i).block_position(l, t, r, b);
		}
	}
}
