package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.Constants;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * @author Gabe Werick Contains methods for displaying the world
 */
public class WorldRenderer implements Disposable
{
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private WorldController worldController;

	/**
	 * Constructor for WorldRenderer, initializes worldController then calls init
	 * 
	 * @param worldController
	 */
	public WorldRenderer(WorldController worldController)
	{
		this.worldController = worldController;
		init();
	}

	/**
	 * Initializes the batch and camera variables Camera is set to the
	 * VIEWPORT_WIDTH and HEIGHT found in constants class, then its position is set
	 * to 0,0,0
	 */
	private void init()
	{
		batch = new SpriteBatch();
		
		//set up camera
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		camera.position.set(0, 0, 0);
		
		//update camera with changes
		camera.update();
	}

	/**
	 * Calls renderTestObjects()
	 */
	public void render()
	{
		renderTestObjects();
	}

	/**
	 * Method responsible for rendering objects contained in batch variable
	 */
	private void renderTestObjects()
	{
		//sets batch to use the object information from camera for rendering
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//renders
		for(Sprite sprite : worldController.testSprites)
		{
			sprite.draw(batch);
		}
		
		batch.end();
	}

	/**
	 * When called resizes the camera to the called height and width
	 * @param width Desired Height
	 * @param height Desired Width
	 */
	public void resize(int width, int height)
	{
		camera.viewportWidth = (Constants.VIEWPORT_HEIGHT/ height) * width;
		camera.update();
	}

	/**
	 * Calls for the batch to be disposed of
	 */
	@Override
	public void dispose()
	{
		batch.dispose();
	}
}
