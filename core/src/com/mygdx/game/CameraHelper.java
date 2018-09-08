package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Gabe Werick Contains methods for manipulating the camera
 */
public class CameraHelper
{
	private static final String TAG = CameraHelper.class.getName();

	private final float MAX_ZOOM_IN = 0.25f;
	private final float MAX_ZOOM_OUT = 10.0f;

	private Vector2 position;
	private float zoom;
	private Sprite target;

	/**
	 * Constructor for the class, initializes positon and zoom
	 */
	public CameraHelper()
	{
		position = new Vector2();
		zoom = 1.0f;
	}

	/**
	 * Sets position to that of our target
	 * 
	 * @param deltaTime current deltaTime
	 */
	public void update(float deltaTime)
	{
		if (!hasTarget())
			return;

		position.x = target.getX() + target.getOriginX();
		position.y = target.getY() + target.getOriginY();
	}

	/**
	 * Allows us to directly set position
	 * 
	 * @param x the x-coordinate
	 * @param y the y-coordinate
	 */
	public void setPosition(float x, float y)
	{
		this.position.set(x, y);
	}

	/**
	 * Get current position
	 * 
	 * @return current position
	 */
	public Vector2 getPosition()
	{
		return position;
	}

	/**
	 * Allows us to increment zoom by some value
	 * 
	 * @param amount amount to add to zoom
	 */
	public void addzoom(float amount)
	{
		setZoom(zoom + amount);
	}

	/**
	 * Sets zoom to a number
	 * 
	 * @param zoom specifies desired zoom
	 */
	public void setZoom(float zoom)
	{
		this.zoom = MathUtils.clamp(zoom, MAX_ZOOM_IN, MAX_ZOOM_OUT);
	}

	/**
	 * Gets current zoom
	 * 
	 * @return current zoom
	 */
	public float getZoom()
	{
		return zoom;
	}

	/**
	 * Sets what Sprite object is our object
	 * 
	 * @param target desired target
	 */
	public void setTarget(Sprite target)
	{
		this.target = target;
	}

	/**
	 * Gets current target
	 * 
	 * @return current target
	 */
	public Sprite getTarget()
	{
		return target;
	}

	/**
	 * returns not null
	 * 
	 * @return not null
	 */
	public boolean hasTarget()
	{
		return target != null;
	}

	/**
	 * returns if a sprite is the object
	 * 
	 * @param target desired
	 * @return whether the sprite is the target
	 */
	public boolean hasTarget(Sprite target)
	{
		return hasTarget() && this.target.equals(target);
	}

	/**
	 * Updates camera position to desired position
	 * 
	 * @param camera
	 */
	public void applyTo(OrthographicCamera camera)
	{
		camera.position.x = position.x;
		camera.position.y = position.y;
		camera.zoom = zoom;
		camera.update();
	}
}
