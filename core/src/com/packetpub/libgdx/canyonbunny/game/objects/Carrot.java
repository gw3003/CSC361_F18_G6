package com.packetpub.libgdx.canyonbunny.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.packetpub.libgdx.canyonbunny.game.Assets;

/**
 * Carrot class that is the goal yeah
 * @author Tyler99c
 *
 */
public class Carrot extends AbstractGameObject 
{
	private TextureRegion regCarrot;

	/**
	 * Constructs a carrot
	 */
	public Carrot() 
	{
	init();
	}
	
	/**
	 * Gives carrot boundaries
	 */
	private void init () 
	{
		dimension.set(0.25f,0.5f);
		
		regCarrot = Assets.instance.levelDecoration.carrot;
		
		//Set bounding box for collision detection
		bounds.set(0,0,dimension.x, dimension.y);
		origin.set(dimension.x/2,dimension.y /2);
	}
	
	/**
	 * Draws carrot
	 */
	public void render (SpriteBatch batch)
	{
		TextureRegion reg = null;
		
		reg = regCarrot;
		batch.draw(reg.getTexture(), position.x-origin.x, position.y - origin.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
	}
}
