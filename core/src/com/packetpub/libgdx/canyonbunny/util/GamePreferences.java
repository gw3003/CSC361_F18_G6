package com.packetpub.libgdx.canyonbunny.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogc.gdx.math.MathUtils;

/**
 * @author Gabe Werick
 * 
 * Holds preferences for the game
 *
 */
public class GamePreferences
{
	public static final String TAG = GamePreferences.class.getName();
	
	public static final GamePreferences instance = new GamePreferences();
	
	public boolean sound, music;
	public float volSound, volMusic;
	public int charSkin;
	public boolean showFpsCounter;
	
	private Preferences prefs;
	
	//singleton: prevents instantiation from other classes
	private GamePreferences()
	{
		prefs = Gdx.app.getPreferences(Constants.PREFERENCES);
	}
	
	/**
	 * Loads game settings as preserved by user
	 */
	public void load() 
	{
		sound = prefs.getBoolean("sound", true);
		music = prefs.getBoolean("music", true);

		volMusic = com.badlogic.gdx.math.MathUtils.clamp(prefs.getFloat("volMusic", 0.5f), 0.0f, 1.0f);
		volSound = com.badlogic.gdx.math.MathUtils.clamp(prefs.getFloat("volSound", 0.5f), 0.0f, 1.0f);
		
		charSkin = com.badlogic.gdx.math.MathUtils.clamp(prefs.getInteger("charSkin", 0), 0, 2);
		
		showFpsCounter = prefs.getBoolean("showFpsCounter", false);
	}
	
	/**
	 * Save settings of the game as set by user
	 */
	public void save() 
	{
		prefs.putBoolean("sound", sound);
		prefs.putBoolean("music", music);
		prefs.putFloat("volSound", volSound);
		prefs.putFloat("charSkin", charSkin);
		prefs.putBoolean("showFpsCounter", showFpsCounter);
	}
}
