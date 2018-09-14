package com.packetpub.libgdx.canyonbunny;

import com.badlogic.gdx.ApplicationListener;

import com.packetpub.libgdx.canyonbunny.game.WorldController;
import com.packetpub.libgdx.canyonbunny.game.WorldRenderer;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.assets.AssetManager;
import com.packetpub.libgdx.canyonbunny.game.Assets;

/**
 * @Author Tyler Forrester
 * Main class we call upon to do things in the game like restarting, starting, and pausing
 */
public class CanyonBunnyMain implements ApplicationListener
{
	private static final String TAG = CanyonBunnyMain.class.getName();
	
	private WorldController worldController;
	private WorldRenderer worldRenderer;
	private boolean paused;
/**
 * Creates the game with a worldRenderer and worldController and unpauses it	
 */
@Override 
public void create() {
	// Set Libgdx log level to Debug
	Gdx.app.setLogLevel(Application.LOG_DEBUG);
	//Load assets
	Assets.instance.init(new AssetManager());
	//Initialize controller and renderer
	worldController = new WorldController();
	worldRenderer = new WorldRenderer(worldController);
	paused = false;
}
/**
 * Renders the world frame by frame, frezes the world if the pause variable is true
 */
@Override
public void render() {
	//Do not update game world when paused.
	if (!paused) {
		//Update game world by the time that has passed since last rendered frame.
		worldController.update(Gdx.graphics.getDeltaTime());
	}
	// Sets the clear screen color to: Cornflower Blue
	Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);
	//Clears the screen
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	//Render game world to screen
	worldRenderer.render();
}
/**
 * Changes the size of the game when we change the size of the screen
 * 
 */
@Override 
public void resize (int width, int height) { }
/**
 * Method used to change the pause variable to true when called upon
 */
@Override 
public void pause() {
	paused = true;
}
/**
 * Method used to change the pause variable to false when called upon
 */
@Override 
public void resume() {
	paused = false;
}
/**
 * Calls the world renderer and assets to dispose
 */
@Override 
public void dispose() {
	worldRenderer.dispose();
	Assets.instance.dispose();
}
}
