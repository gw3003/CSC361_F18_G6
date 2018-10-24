package com.packetpub.libgdx.canyonbunny.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.packetpub.libgdx.canyonbunny.game.Assets;
import com.badlogic.gdx.math.MathUtils;

/**
 * Contains information for the gold coin object, which increases score and
 * disappears once it's collected.
 * 
 * @author Kevin Rutter
 */
public class GoldCoin extends AbstractGameObject
{
	//private TextureRegion regGoldCoin;

	public boolean collected;

	/**
	 * Constructor for gold coin object.
	 */
	public GoldCoin()
	{
		init();
	}

	/**
	 * Initialize the gold coin object by setting it's width/height, getting its
	 * asset image, setting its bound, and making it uncollected so it's obtainable.
	 */
	private void init()
	{
		dimension.set(0.5f, 0.5f);
		
		setAnimation(Assets.instance.goldCoin.animGoldCoin);
		stateTime = MathUtils.random(0.0f, 1.0f);

		// Set bounding box for collision detection
		bounds.set(0, 0, dimension.x, dimension.y);

		collected = false;
	}

	/**
	 * Renders the coin (if it hasn't been collected yet).
	 * 
	 * @param batch
	 *            SpriteBatch being used to draw the coin.
	 */
	public void render(SpriteBatch batch)
	{
		if (collected)
			return;

		TextureRegion reg = null;
		reg = animation.getKeyFrame(stateTime, true);
		batch.draw(reg.getTexture(), position.x, position.y, origin.x, origin.y, dimension.x, dimension.y, scale.x,
				scale.y, rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(),
				false, false);
	}

	/**
	 * Return the score value of a gold coin.
	 * 
	 * @return A gold coin is worth 100 score.
	 */
	public int getScore()
	{
		return 100;
	}
}