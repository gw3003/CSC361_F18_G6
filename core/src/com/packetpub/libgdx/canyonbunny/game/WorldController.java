package com.packetpub.libgdx.canyonbunny.game;

import com.badlogic.gdx.graphics.Pixmap;

import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.packetpub.libgdx.canyonbunny.util.CameraHelper;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.packetpub.libgdx.canyonbunny.game.objects.Rock;
import com.packetpub.libgdx.canyonbunny.util.Constants;
import com.badlogic.gdx.math.Rectangle;
import com.packetpub.libgdx.canyonbunny.game.objects.BunnyHead;
import com.packetpub.libgdx.canyonbunny.game.objects.BunnyHead.JUMP_STATE;
import com.packetpub.libgdx.canyonbunny.game.objects.Feather;
import com.packetpub.libgdx.canyonbunny.game.objects.GoldCoin;
import com.packetpub.libgdx.canyonbunny.game.objects.Rock;

/**
 * @author Kevin Rutter
 * Contains controls for the game, such as for the camera, movement, etc.
 */
public class WorldController extends InputAdapter
{
	// Tag used for logging purposes
	private static final String TAG =
			WorldController.class.getName();
	
	public CameraHelper cameraHelper;
	
	public Level level;
	public int lives;
	public int score;
	
	/**
	 * Constructor for WorldController.
	 */
	public WorldController()
	{
		init();
	}
	
	/**
	 * Initialization code for WorldController.
	 * Useful to call when resetting objects.
	 */
	public void init()
	{
		Gdx.input.setInputProcessor(this);
		cameraHelper = new CameraHelper();
		lives = Constants.LIVES_START;
		initLevel();
	}
	
	public void initLevel()
	{
		score = 0;
		level = new Level(Constants.LEVEL_01);
	}
	
	/**
	 * Resets game if R is pressed, changes selected sprite if space is pressed.
	 * @param keycode	The key that was released.
	 * @return			false
	 */
	@Override
	public boolean keyUp(int keycode)
	{
		// Reset game world
		if (keycode == Keys.R)
		{
			init();
			Gdx.app.debug(TAG, "Game world resetted");
		}
		return false;
	}
	
	/**
	 * Generates a test pixmap that has a partially transparent red fill, yellow X, and cyan border.
	 * @param width		width of the pixmap to generate.
	 * @param height	height of the pixmap to generate.
	 * @return			The generated pixmap.
	 */
	private Pixmap createProceduralPixmap(int width, int height)
	{
		Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
		// Fill square with red color at 50% opacity
		pixmap.setColor(1, 0, 0, 0.5f);
		pixmap.fill();
		// Draw a yellow-colored X shape on square
		pixmap.setColor(1, 1, 0, 1);
		pixmap.drawLine(0, 0, width, height);
		pixmap.drawLine(width, 0, 0, height);
		// Draw a cyan-colored border around square
		pixmap.setColor(0, 1, 1, 1);
		pixmap.drawRectangle(0, 0, width, height);
		return pixmap;
	}
	
	/**
	 * Applies updates to the game world many times a second.
	 * @param deltaTime		How much time has passed since last frame.
	 */
	public void update(float deltaTime)
	{
		handleDebugInput(deltaTime);
		level.update(deltaTime);
		cameraHelper.update(deltaTime);
	}
	
	/**
	 * Tests out movement of sprites and camera.
	 * @param deltaTime		How much time since last frame.
	 */
	private void handleDebugInput(float deltaTime)
	{
		if (Gdx.app.getType() != ApplicationType.Desktop)
			return;
		
		// Camera Controls (move)
		float camMoveSpeed = 5 * deltaTime;
		float camMoveSpeedAccelerationFactor = 5;
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))
			camMoveSpeed *= camMoveSpeedAccelerationFactor;
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			moveCamera(-camMoveSpeed, 0);
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			moveCamera(camMoveSpeed, 0);
		if (Gdx.input.isKeyPressed(Keys.UP))
			moveCamera(0, camMoveSpeed);
		if (Gdx.input.isKeyPressed(Keys.DOWN))
			moveCamera(0, -camMoveSpeed);
		if (Gdx.input.isKeyPressed(Keys.BACKSPACE))
			cameraHelper.setPosition(0, 0);
		
		// Camera Controls (zoom)
		float camZoomSpeed = 1 * deltaTime;
		float camZoomSpeedAccelerationFactor = 5;
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))
			camZoomSpeed *= camZoomSpeedAccelerationFactor;
		if (Gdx.input.isKeyPressed(Keys.COMMA))
			cameraHelper.addzoom(camZoomSpeed);
		if (Gdx.input.isKeyPressed(Keys.PERIOD))
			cameraHelper.addzoom(-camZoomSpeed);
		if (Gdx.input.isKeyPressed(Keys.SLASH))
			cameraHelper.setZoom(1);
	}
	
	/**
	 * Moves the Camera in a given direction
	 * @param x		Horizontal distance.
	 * @param y		Vertical distance.
	 */
	private void moveCamera(float x, float y)
	{
		x += cameraHelper.getPosition().x;
		y += cameraHelper.getPosition().y;
		cameraHelper.setPosition(x, y);
	}
}