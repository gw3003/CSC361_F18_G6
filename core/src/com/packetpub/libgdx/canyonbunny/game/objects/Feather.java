package com.packetpub.libgdx.canyonbunny.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.packetpub.libgdx.canyonbunny.game.Assets;

/**
 * @author Gabe Werick
 * Contains methods for the Feather game object
 */
public class Feather extends AbstractGameObject{
	
	private TextureRegion regFeather;
	
	public boolean collected;
	
	/**
	 * Constructor, just calls init
	 */
	public Feather()
	{
		init();
	}
	
	/**
	 * Sets its dimension, gets feather texture, and then sets its bounds
	 */
	private void init()
	{
		dimension.set(0.5f, 0.5f);
		
		regFeather = Assets.instance.feather.feather;
		
		//Set bounding box for collision detection
		
		bounds.set(0,0, dimension.x, dimension.y);
		
		collected = false;
	}
	
	
	/**
	 * This draws the feather so long as it hasn't been collected at desired location
	 */
	public void render(SpriteBatch batch)
	{
		if(collected) return;
		
		TextureRegion reg = null;
		reg = regFeather;
		batch.draw(reg.getTexture(), position.x, position.y, origin.x, origin.y, dimension.x, dimension.y,
				scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionWidth(), false, false);
	}
	
	/**
	 * Returns a score value when called
	 * @return score value
	 */
	public int getScore()
	{
		return 250;
	}
}
