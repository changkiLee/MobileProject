package Game;

import State.RankState;

public class Game_Save{
	public static int Check = 0;
	public static int Score;
	public static int MaxCombo;
	public static int Level;
	public static String Name;
		
	// 점수, 콤보, 레벨을 저장받는 메소드	
	// Rank로 저장
	public void Game_sc(int Sc, int Ma, int Le, String Na){
		Score = Sc;
		MaxCombo = Ma;
		Level = Le;
		Name = Na;
	}
	
	// Rank로 불러오기
	public void Game_re(RankState r){
		r.score = Score;
		r.combo = MaxCombo;
		r.level = Level;
		r.name = Name;
	}
	
	public void Check_save(int check){
		Check = check;
	}
	
	// 저장 체크(중복저장 방지)
	public int Save_check(){
		return Check;
	}
}
