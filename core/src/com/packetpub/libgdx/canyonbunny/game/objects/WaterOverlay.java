package com.packetpub.libgdx.canyonbunny.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.packetpub.libgdx.canyonbunny.game.Assets;

/**
 * Creates the water game object
 * 
 * @author Tyler Forrester
 *
 */
public class WaterOverlay extends AbstractGameObject
{

	private TextureRegion regWaterOverlay;
	private float length;

	/**
	 * Constructor for the water
	 * 
	 * @param length
	 *            of the object
	 */
	public WaterOverlay(float length)
	{
		this.length = length;
		init();
	}

	/**
	 * Sets dimension
	 */
	private void init()
	{
		dimension.set(length * 10, 3);
		regWaterOverlay = Assets.instance.levelDecoration.waterOverlay;

		origin.x = -dimension.x / 2;
	}

	/**
	 * Gets the sprite image and puts it on the level
	 */
	@Override
	public void render(SpriteBatch batch)
	{
		TextureRegion reg = null;
		reg = regWaterOverlay;
		batch.draw(reg.getTexture(), position.x + origin.x, position.y + origin.y, origin.x, origin.y, dimension.x,
				dimension.y, scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(),
				reg.getRegionHeight(), false, false);
	}

}
