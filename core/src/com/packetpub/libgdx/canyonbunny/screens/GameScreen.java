package com.packetpub.libgdx.canyonbunny.screens;

import com.badlogic.gdx.Gdx;
import com.packetpub.libgdx.canyonbunny.util.GamePreferences;
/**
 * Screen that handles the game
 * @author Tyler Forrester
 *
 */
public class GameScreen
{

	/**
	 * Shows the game screen after we enter
	 */
	@Override
	public void show() 
	{
		GamePreferences.instance.load();
	}
	
	
}
