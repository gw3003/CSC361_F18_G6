package com.packetpub.libgdx.canyonbunny.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Makes an abstract class for all game objects to structure themselves around
 * @author Tyler Forrester
 *
 */
public abstract class AbstractGameObject {

	public Vector2 position;
	public Vector2 dimension;
	public Vector2 origin;
	public Vector2 scale;
	public float rotation;
	
	/**
	 * Builds the game object
	 */
	public AbstractGameObject () {
		position = new Vector2();
		dimension = new Vector2(1,1);
		origin = new Vector2();
		scale = new Vector2(1,1);
		rotation = 0;
	}
	
	/**
	 * Updates the object based on the timer
	 * @param deltaTime
	 */
	public void update (float deltaTime) {
		
	}
	
	/**
	 * Gets the appearance of the game object
	 * @param batch is the sprite batch it uses
	 */
	public abstract void render (SpriteBatch batch);
}
