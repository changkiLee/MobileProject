package Game;

public class Game_Color {

	private boolean Game_st  = false; // ���� ���ھ� Ȯ��
	
	// ��, ��� ���� ��
	public boolean Game_color_check(int block_check_color, int wall_color)
	{
		if(block_check_color == wall_color)
		{
			Game_st = true;
		}
		
		else
		{
			Game_st = false;
		}
		
		return Game_st;
	}
}
