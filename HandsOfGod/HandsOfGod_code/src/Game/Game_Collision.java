package Game;

import android.graphics.Rect;

public class Game_Collision {
	
	// �浹 Ȯ��
	private boolean check_C;
 
	public boolean check_Collision(Rect Block, Rect Wall)
	{
		check_C = Block.intersect(Wall);
		
		return check_C;
	}
}