package Game;

import State.RankState;

public class Game_Save{
	public static int Check = 0;
	public static int Score;
	public static int MaxCombo;
	public static int Level;
	public static String Name;
		
	// ����, �޺�, ������ ����޴� �޼ҵ�	
	// Rank�� ����
	public void Game_sc(int Sc, int Ma, int Le, String Na){
		Score = Sc;
		MaxCombo = Ma;
		Level = Le;
		Name = Na;
	}
	
	// Rank�� �ҷ�����
	public void Game_re(RankState r){
		r.score = Score;
		r.combo = MaxCombo;
		r.level = Level;
		r.name = Name;
	}
	
	public void Check_save(int check){
		Check = check;
	}
	
	// ���� üũ(�ߺ����� ����)
	public int Save_check(){
		return Check;
	}
}
