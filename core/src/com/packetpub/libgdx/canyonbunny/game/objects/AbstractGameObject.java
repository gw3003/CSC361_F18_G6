package com.packetpub.libgdx.canyonbunny.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;

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
	public Vector2 velocity;
	public Vector2 terminalVelocity;
	public Vector2 friction;
	
	public Vector2 acceleration;
	public Rectangle bounds;
	
	/**
	 * Builds the game object
	 */
	public AbstractGameObject () {
		position = new Vector2();
		dimension = new Vector2(1,1);
		origin = new Vector2();
		scale = new Vector2(1,1);
		rotation = 0;
		velocity = new Vector2();
		terminalVelocity = new Vector2(1,1);
		friction = new Vector2();
		acceleration = new Vector2();
		bounds = new Rectangle();
	}
	
	/**
	 * Updates moveing along the X axis
	 */
	protected void updateMotionX (float deltaTime) {
		if (velocity.x != 0) {
			//Apply friction
			if (velocity.x > 0) {
				velocity.x =
						Math.max(velocity.x - friction.x * deltaTime, 0);
			}else {
				velocity.x =
						Math.min(velocity.x + friction.x * deltaTime, 0);
			}
		}
		//Apply acceleration
		velocity.x += acceleration.x * deltaTime;
		//Make sure the object's velocity does not exceed the
		//Positive or negative terminal velocity
		velocity.x = MathUtils.clamp(velocity.x, -terminalVelocity.x, terminalVelocity.x);
	}
	
	/**
	 * Updates and handles movement on the Y axis
	 */
	protected void updateMotionY (float deltaTime) {
		if (velocity.y != 0){
			//Apply friction
			if (velocity.y > 0) {
				velocity.y = Math.max(velocity.y - friction.y * deltaTime, 0);				
			} else {
				velocity.y = Math.min(velocity.y + velocity.y * deltaTime, 0);
			}
		}
		//Apply acceleration
		velocity.y += acceleration.y * deltaTime;
		//Make sure the object's velcotiy does not exceed the 
		//Positive or negative terminal velocity
		velocity.y = MathUtils.clamp(velocity.y, -terminalVelocity.y, terminalVelocity.y);
	}
	
	/**
	 * Updates the object based on the timer
	 * @param deltaTime
	 */
	public void update (float deltaTime) {
		updateMotionX(deltaTime);
		updateMotionY(deltaTime);
		//Move to new position
		position.x += velocity.x * deltaTime;
		position.y += velocity.y * deltaTime;
	}
	
	/**
	 * Gets the appearance of the game object and puts it on the level
	 * @param batch is the sprite batch it uses
	 */
	public abstract void render (SpriteBatch batch);
}
