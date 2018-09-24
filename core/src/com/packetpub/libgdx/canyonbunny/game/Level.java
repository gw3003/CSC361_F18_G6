package com.packetpub.libgdx.canyonbunny.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.packetpub.libgdx.canyonbunny.game.objects.AbstractGameObject;
import com.packetpub.libgdx.canyonbunny.game.objects.Clouds;
import com.packetpub.libgdx.canyonbunny.game.objects.Mountains;
import com.packetpub.libgdx.canyonbunny.game.objects.Rock;
import com.packetpub.libgdx.canyonbunny.game.objects.WaterOverlay;
import com.packetpub.libgdx.canyonbunny.game.objects.BunnyHead;
import com.packetpub.libgdx.canyonbunny.game.objects.Feather;
import com.packetpub.libgdx.canyonbunny.game.objects.GoldCoin;

/**
 * @author Gabe Werick this method is responsible for loading levels
 */
public class Level
{
	public static final String TAG = Level.class.getName();

	/**
	 * Custom data type, used for storing level data
	 */

	public enum BLOCK_TYPE
	{
		EMPTY(0, 0, 0), // black
		ROCK(181, 230, 29), // green
		PLAYER_SPAWNPOINT(255, 255, 255), // white
		ITEM_FEATHER(255, 174, 201), // purple
		ITEM_GOLD_COIN(255, 242, 0); // yellow

		private int color;

		private BLOCK_TYPE(int r, int g, int b)
		{
			color = r << 24 | g << 16 | b << 8 | 0xff;
		}

		public boolean sameColor(int color)
		{
			return this.color == color;
		}

		public int getColor()
		{
			return color;
		}
	}

	// objects
	public Array<Rock> rocks;
	public BunnyHead bunnyHead;
	public Array<GoldCoin> goldcoins;
	public Array<Feather> feathers;

	// decoration
	public Clouds clouds;
	public Mountains mountains;
	public WaterOverlay waterOverlay;

	/**
	 * Constructor, calls init
	 * 
	 * @param filename filename to level layout
	 */
	public Level(String filename)
	{
		init(filename);
	}

	/**
	 * Takes in the filename of the level to be loaded then read the file
	 */
	private void init(String filename)
	{
		//player character
		bunnyHead = null;
		// objects
		rocks = new Array<Rock>();
		goldcoins = new Array<GoldCoin>();
		feathers = new Array<Feather>();

		// load image file that represents the level data
		Pixmap pixmap = new Pixmap(Gdx.files.internal(filename));
		// scan pixels from top-left to bottom-right
		int lastPixel = -1;
		for (int pixelY = 0; pixelY < pixmap.getHeight(); pixelY++)
		{
			for (int pixelX = 0; pixelX < pixmap.getWidth(); pixelX++)
			{
				AbstractGameObject obj = null;
				float offsetHeight = 0;
				// height grows from bottom to top
				float baseHeight = pixmap.getHeight() - pixelY;
				// get color of current pixel as 32-bit RGBA value
				int currentPixel = pixmap.getPixel(pixelX, pixelY);
				// find matching color value to identify block type (x,y)
				// point and create the corresponding game object if there is a match

				// empty space
				if (BLOCK_TYPE.EMPTY.sameColor(currentPixel))
				{
					// do nothing
				}

				// rock
				else if (BLOCK_TYPE.ROCK.sameColor(currentPixel))
				{
					if (lastPixel != currentPixel)
					{
						obj = new Rock();
						float heightIncreaseFactor = 0.25f;
						offsetHeight = -2.5f;
						obj.position.set(pixelX, baseHeight * obj.dimension.y * heightIncreaseFactor + offsetHeight);
						rocks.add((Rock) obj);
					} else
					{
						rocks.get(rocks.size - 1).increaseLength(1);
					}
				}

				// player spawn point
				else if (BLOCK_TYPE.PLAYER_SPAWNPOINT.sameColor(currentPixel))
				{
					obj = new BunnyHead();
					offsetHeight = -3.0f;
					obj.position.set(pixelX, baseHeight * obj.dimension.y + offsetHeight);
					bunnyHead = (BunnyHead) obj;
				}

				// feather
				else if (BLOCK_TYPE.ITEM_FEATHER.sameColor(currentPixel))
				{
					obj = new Feather();
					offsetHeight = -1.5f;
					obj.position.set(pixelX, baseHeight * obj.dimension.y + offsetHeight);
					feathers.add((Feather)obj);
				}
				// gold coin
				else if (BLOCK_TYPE.ITEM_GOLD_COIN.sameColor(currentPixel))
				{
					obj = new GoldCoin();
					offsetHeight = -1.5f;
					obj.position.set(pixelX, baseHeight * obj.dimension.y + offsetHeight);
					goldcoins.add((GoldCoin)obj);
				}

				// unknown object/pixel color
				else
				{
					int r = 0xff & (currentPixel >>> 24); // red color channel
					int g = 0xff & (currentPixel >>> 16); // green color channel
					int b = 0xff & (currentPixel >>> 8); // blue color channel
					int a = 0xff & currentPixel; // alpha channel
					Gdx.app.error(TAG, "Unknown object at x<" + pixelX + "> y<" + pixelY + ">: r<" + r + "> g<" + g
							+ "> b<" + b + "> a<" + a + ">");
				}
				lastPixel = currentPixel;
			}
		}
		// decorations
		clouds = new Clouds(pixmap.getWidth());
		clouds.position.set(0, 2);
		mountains = new Mountains(pixmap.getWidth());
		mountains.position.set(-1, -1);
		waterOverlay = new WaterOverlay(pixmap.getWidth());
		waterOverlay.position.set(0, -3.75f);

		// free memory
		pixmap.dispose();
		Gdx.app.debug(TAG, "level '" + filename + "' loaded");
	}

	/**
	 * Handles rendering of objects
	 * 
	 * @param batch Spritebatch object
	 */
	public void render(SpriteBatch batch)
	{
		// Draw Mountains
		mountains.render(batch);

		// Draw Rocks
		for (Rock rock : rocks)
			rock.render(batch);
		
		// Draw Gold Coins
		for (GoldCoin goldCoin : goldcoins)
			goldCoin.render(batch);
		
		// Draw Rocks
		for (Feather feather : feathers)
			feather.render(batch);
		
		//Draw player Character
		bunnyHead.render(batch);

		// Draw Water overlay
		waterOverlay.render(batch);

		// Draw Clouds
		clouds.render(batch);
	}
	
	/**
	 * Calls update method of each object in this class
	 * @param deltaTime current deltaTime
	 */
	public void update(float deltaTime)
	{
		bunnyHead.update(deltaTime);
		for(Rock rock : rocks)
			rock.update(deltaTime);
		for(GoldCoin goldCoin : goldcoins)
			goldCoin.update(deltaTime);
		for(Feather feather : feathers)
			feather.update(deltaTime);
		clouds.update(deltaTime);
	}
}
