package com.packetpub.libgdx.canyonbunny.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.packetpub.libgdx.canyonbunny.game.Assets;

/**
 * Contains information for the goal object, which acts
 * as the end of a level.
 * 
 * @author Kevin Rutter
 */
public class Goal extends AbstractGameObject
{
	private TextureRegion regGoal;
	
	/**
	 * Constructor for the goal object.
	 */
	public Goal()
	{
		init();
	}
	
	/**
	 * Initializes the goal object by setting its dimensions and
	 * collision box. (which is set to be infinitely tall)
	 */
	private void init()
	{
		dimension.set(3.0f, 3.0f);
		regGoal = Assets.instance.levelDecoration.goal;
		
		// set bounding box for collision detection
		bounds.set(1, Float.MIN_VALUE, 10, Float.MAX_VALUE);
		origin.set(dimension.x / 2.0f, 0.0f);
	}
	
	/**
	 * Renders the goal object
	 * 
	 * @param batch		SpriteBatch being used to draw the goal.
	 */
	public void render(SpriteBatch batch)
	{
		TextureRegion reg = null;
		
		reg = regGoal;
		batch.draw(reg.getTexture(), position.x - origin.x, position.y - origin.y,
				origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y,
				rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(),
				reg.getRegionHeight(), false, false);
	}
}
