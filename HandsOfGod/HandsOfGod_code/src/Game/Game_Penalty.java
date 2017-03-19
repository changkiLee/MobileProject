package Game;

import java.util.ArrayList;
import java.util.Random;

public class Game_Penalty {

	// 패널티(실수하면 블록 2개 생성)
	private int Random_pos1;
	private int Random_pos2;
	private Random Rand = new Random();
	private int check1, check2;
	
	public void block_Penalty(ArrayList<Game_Block> g_Block)
	{
		check1 = 0;
		check2 = 0;
			
			Random_pos1 = Rand.nextInt(8);
			Random_pos2 = Rand.nextInt(8) + Random_pos1;

			for(int i = 0; i < 16; i++)
			{
				if(!g_Block.get(i).block_Check_view)
				{
					if(i == Random_pos1)
					{
						g_Block.get(i).block_Check_view = true;
						check1++;
					}
					
					if(i == Random_pos2)
					{
						g_Block.get(i).block_Check_view = true;
						check2++;
					}
				}
			}
			
			if(check1 + check2 == 2)
			{
				return;
			}
			
			else
			{
				if(check1 != 1)
				{
					for(int i = 0; i < 8; i++)
					{
						if(!g_Block.get(i).block_Check_view)
						{
							g_Block.get(i).block_Check_view = true;
							break;
						}
					}
				}
				
				if(check2 != 1)
				{
					for(int i = 8; i < 16; i++)
					{
						if(!g_Block.get(i).block_Check_view)
						{
							g_Block.get(i).block_Check_view = true;
							break;
						}
					}	
				}
			}
	}
	
}
