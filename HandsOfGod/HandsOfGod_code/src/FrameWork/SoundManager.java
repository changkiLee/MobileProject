package FrameWork;

import java.util.HashMap;

import edu.dongguk.mme.HOG.R;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class SoundManager {
	// �ɹ� ����
	private SoundPool g_SoundPool;
	private HashMap g_SoundPoolMap;
	private AudioManager g_AudioManager;
	private Context g_Activity;

	public static float sound = (float)0.5;
	public static float BGM = (float)0.5;
	
	public MediaPlayer B_sound = new MediaPlayer();
	
	public void Init(Context _context){
		// ��� ���� ���� �� �ʱ�ȭ
		g_SoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
		g_SoundPoolMap = new HashMap();
		g_AudioManager = (AudioManager)_context.getSystemService(Context.AUDIO_SERVICE);
		g_Activity = _context;
	}
	
	public void addSound(int _index, int _soundID){
		int id = g_SoundPool.load(g_Activity, _soundID, 1); // ���� �ε�
		g_SoundPoolMap.put(_index,id);
	}
	
	public void play(int _index){
		// ���� ���
		float streamVolume = g_AudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		streamVolume = streamVolume / g_AudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		g_SoundPool.play((Integer)g_SoundPoolMap.get(_index), sound, sound, 1, 0, 1f);
	}
	
	public void PlayBC(Context context)
	{
		B_sound = MediaPlayer.create(context, R.raw.bgm);
	}
	
	public void playLooped(){
		// ���� �ݺ� ���	
		B_sound.setVolume(BGM, BGM);
		B_sound.setLooping(true);
		B_sound.start();
		
	}
	
	public void PlayPause()
	{
		B_sound.pause();
		B_sound.setVolume(BGM, BGM);
		B_sound.setLooping(true);
		B_sound.start();
	}
	
	// �̱��� ���� ����
	private static SoundManager s_instance;
	
	public static SoundManager getInstatnce(){
		if(s_instance == null){
			s_instance = new SoundManager();
		}
		return s_instance;
	}
}
