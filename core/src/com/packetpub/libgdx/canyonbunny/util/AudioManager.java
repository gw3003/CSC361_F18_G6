package com.packetpub.libgdx.canyonbunny.util;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Handles the playing of sound effects and music.
 * @author Kevin Rutter
 */
public class AudioManager
{
	public static final AudioManager instance = new AudioManager();
	
	private Music playingMusic;
	
	// singleton: prevent instantiation from other classes
	/**
	 * Constructor for AudioManager.
	 * Private so there can only be one instance.
	 */
	private AudioManager()
	{
	}
	
	/**
	 * Plays a given sound.
	 * @param sound		The sound that will be played.
	 */
	public void play(Sound sound)
	{
		play(sound, 1);
	}
	
	/**
	 * Plays a given sound at a specified volume.
	 * @param sound		The sound that will be played.
	 * @param volume	The volume that sound will be played at.
	 */
	public void play(Sound sound, float volume)
	{
		play(sound, volume, 1);
	}
	
	/**
	 * Plays a given sound at a specified volume and pitch.
	 * @param sound		The sound that will be played.
	 * @param volume	The volume that sound will be played at.
	 * @param pitch		The pitch the sound will be played at.
	 */
	public void play(Sound sound, float volume, float pitch)
	{
		play(sound, volume, pitch, 0);
	}
	
	/**
	 * Plays a given sound at a specified volume, pitch, and pan.
	 * @param sound		The sound that will be played.
	 * @param volume	The volume that sound will be played at.
	 * @param pitch		The pitch the sound will be played at.
	 * @param pan		The pan the sound will be played at.
	 */
	public void play(Sound sound, float volume, float pitch, float pan)
	{
		if (!GamePreferences.instance.sound)
			return;
		sound.play(GamePreferences.instance.volSound * volume, pitch, pan);
	}
}