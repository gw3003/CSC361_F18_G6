package com.packetpub.libgdx.canyonbunny.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.packetpub.libgdx.canyonbunny.util.GamePreferences;
import com.badlogic.gdx.graphics.GL20;
import com.packetpub.libgdx.canyonbunny.game.WorldController;
import com.packetpub.libgdx.canyonbunny.game.WorldRenderer;

/**
 * Screen that handles the game
 * 
 * @author Tyler Forrester & Gabe Werick
 *
 */
public class GameScreen extends AbstractGameScreen
{

	private static final String TAG = Gamescreen.class.getName();

	private WorldController worldController;
	private WorldRenderer worldRenderer;

	private boolean paused;

	/**
	 * Constructor for gamescreen, simply passes game to the constructor of the
	 * parent class
	 * 
	 * @param game game object to be passed
	 */
	public GameScreen(Game game)
	{
		super(game);
	}

	/**
	 * Will cause rendering functions to occur via worldController's update method
	 * It will not call the update method if the game is paused
	 */
	@Override
	public void render(float deltaTime)
	{
		// Do not update game world when paused
		if (!paused)
		{
			// Update game world by the time that has passed since last update
			worldController.update(deltaTime);
		}

		// Sets the clear screen color to Cornflower Blue
		Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f, 0xff / 255.0f);

		// Clears the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Render game world to screen
		worldRenderer.render();
	}

	/**
	 * Causes screen to be resized to the desired size
	 */
	@Override
	public void resize(int width, int height)
	{
		worldRenderer.resize(width, height);
	}

	/**
	 * Shows the game screen after we enter
	 */
	@Override
	public void show()
	{
		worldController = new WorldController(game);
		worldRenderer = new WorldRenderer(worldController);
		Gdx.input.setCatchBackKey(true);
	}

	/**
	 * Causes game screen to be hidden and summarily disposed of
	 */
	@Override
	public void hide()
	{
		worldRenderer.dispose();
		Gdx.input.setCatchBackKey(false);
	}

	/**
	 * Causes the paused state to be triggered
	 */
	@Override
	public void pause()
	{
		paused = true;
	}

	/**
	 * Unpauses the game, allowing it to continue
	 */
	@Override
	public void resume()
	{
		super.resume();
		// Only called on android
		paused = false;
	}

}
