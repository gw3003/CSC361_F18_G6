package com.packetpub.libgdx.canyonbunny.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.packetpub.libgdx.canyonbunny.game.Assets;

/**
 * Clouds acts as a container for the cloud objects.
 * @author Kevin Rutter
 */
public class Clouds extends AbstractGameObject
{
	private float length;
	
	private Array<TextureRegion> regClouds;
	private Array<Cloud> clouds;
	
	/**
	 * A single cloud object, which can be one of 3 images.
	 * @author Kevin Rutter
	 */
	private class Cloud extends AbstractGameObject
	{
		private TextureRegion regCloud;
		
		/**
		 * Clouds are created with spawnCloud() instead.
		 */
		public Cloud()
		{	
		}
		
		/**
		 * Used to set the cloud image.
		 * @param region	The random cloud image this cloud is being set to.
		 */
		public void setRegion(TextureRegion region)
		{
			regCloud = region;
		}
		
		/**
		 * Renders the cloud object.
		 * @param batch		SpriteBatch being used to draw the cloud.
		 */
		@Override
		public void render (SpriteBatch batch)
		{
			TextureRegion reg = regCloud;
			batch.draw(reg.getTexture(), position.x + origin.x, position.y + origin.y,
					origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation,
					reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(),
					false, false);
		}
	}
	
	/**
	 * Constructor for the clouds container.
	 * @param length	The length of the screen.
	 */
	public Clouds(float length)
	{
		this.length = length;
		init();
	}
	
	/**
	 * Initialize the clouds container, spawn an amount of clouds based
	 * on the length and distFac.
	 */
	private void init()
	{
		dimension.set(3.0f, 1.5f);
		regClouds = new Array<TextureRegion>();
		regClouds.add(Assets.instance.levelDecoration.cloud01);
		regClouds.add(Assets.instance.levelDecoration.cloud02);
		regClouds.add(Assets.instance.levelDecoration.cloud03);
		
		int distFac = 5;
		int numClouds = (int)(length / distFac);
		clouds= new Array<Cloud>(2 * numClouds);
		for (int i = 0; i < numClouds; i++)
		{
			Cloud cloud = spawnCloud();
			cloud.position.x = i * distFac;
			clouds.add(cloud);
		}
	}

	/**
	 * Used to create a cloud.
	 * @return	The clouds created.
	 */
	private Cloud spawnCloud()
	{
		Cloud cloud = new Cloud();
		cloud.dimension.set(dimension);
		// select random cloud image
		cloud.setRegion(regClouds.random());
		// position
		Vector2 pos = new Vector2();
		pos.x = length + 10; // position after end of level
		pos.y += 1.75; // base position
		pos.y += MathUtils.random(0.0f, 0.2f) * (MathUtils.randomBoolean() ? 1 : -1); // random additional position
		cloud.position.set(pos);
		return cloud;
	}
	
	/**
	 * Used to render each cloud in the clouds contianer.
	 */
	@Override
	public void render(SpriteBatch batch)
	{
		for (Cloud cloud : clouds)
			cloud.render(batch);
	}
}