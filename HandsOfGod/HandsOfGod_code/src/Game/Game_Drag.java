package Game;

public class Game_Drag {
	
	float ab_x, ab_y; // ������ ���밪
	int direction = 0;
	float dx1, dy1, dx2, dy2;
	
	public void absolute_N(int x1,int y1,int x2,int y2) //X�� Y�� ���̵��� ������ ���밪�� ���Ѵ�
	{
		ab_x = x1 - x2;
		ab_y = y1 - y2;
		
		dx1 = x1;
		dy1 = y1;
		dx2 = x2;
		dy2 = y2;
		
		if(ab_x < 0)
		{
			ab_x = -ab_x;
		}
		
		if(ab_y < 0)
		{
			ab_y = - ab_y;
		}
	}
	
	public void decide_Direction() //��� ������ �����ش�
	{
		if(ab_x > ab_y)
		{
			if((dx1 - dx2) > 0) // �������� �̵�
			{
				direction = 1;
			}
			
			else if((dx1 - dx2) < 0) // ���������� �̵�
			{
				direction = 2;
			}
		}
		
		else if(ab_x < ab_y)
		{
			if((dy1 - dy2) > 0) //�������� �̵�
			{
				direction = 3;
			}
			
			else if((dy1 - dy2) < 0) //�Ʒ������� �̵�
			{
				direction = 4;
			}
		}
		
		else
			direction = 0;
		
	}
}
