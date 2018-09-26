package com.packetpub.libgdx.canyonbunny.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 * This screen is for the main menu of the game.
 * Has play button, option button, etc.
 * @author Kevin Rutter
 */
public class MenuScreen extends AbstractGameScreen
{
	private static final String TAG = MenuScreen.class.getName();
	
	/**
	 * Constructor for the screen that holds the main menu of the game.
	 * @param game	The applicationListener for the game. (CanyonBunnyMain)
	 */
	public MenuScreen(Game game)
	{
		super(game);
	}
	
	/**
	 * Renders the current frame of the screen.
	 * Changes screen to the gamescreen when touched/clicked.
	 * @param deltaTime		Amount of time since last frame was rendered.
	 */
	@Override
	public void render(float deltaTime)
	{
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (Gdx.input.isTouched())
			game.setScreen(new GameScreen(game));
	}
	
	@Override public void resize(int width, int height)
	{
		
	}
	
	@Override public void show()
	{
		
	}
	
	@Override public void hide()
	{
		
	}
	
	@Override public void pause()
	{
		
	}
}