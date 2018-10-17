package com.packetpub.libgdx.canyonbunny.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;
import com.packetpub.libgdx.canyonbunny.util.Constants;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * @author Kevin Rutter Handles texture loading using a texture atlas.
 */
public class Assets implements Disposable, AssetErrorListener
{
	public static final String TAG = Assets.class.getName();

	public static final Assets instance = new Assets();

	private AssetManager assetManager;

	public AssetSounds sounds;
	public AssetMusic music;
	public AssetFonts fonts;
	public AssetBunny bunny;
	public AssetRock rock;
	public AssetGoldCoin goldCoin;
	public AssetFeather feather;
	public AssetLevelDecoration levelDecoration;

	// singleton: prevent instantiation from other classes
	private Assets()
	{
	}

	/**
	 * Fonts for the game
	 * 
	 * @author Tyler
	 *
	 */

	public class AssetFonts
	{
		public final BitmapFont defaultSmall;
		public final BitmapFont defaultNormal;
		public final BitmapFont defaultBig;

		public AssetFonts()
		{
			// Create three fonts using Libgdx' 15px bitmap font
			defaultSmall = new BitmapFont(Gdx.files.internal("images/arial-15.fnt"), true);
			defaultNormal = new BitmapFont(Gdx.files.internal("images/arial-15.fnt"), true);
			defaultBig = new BitmapFont(Gdx.files.internal("images/arial-15.fnt"), true);
			// set font sizes
			defaultSmall.getData().setScale(0.75f);
			defaultNormal.getData().setScale(1.0f);
			defaultBig.getData().setScale(2.0f);
			// enable linear texture filtering for smooth fonts
			defaultSmall.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
			defaultNormal.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
			defaultBig.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

		}
	}

	/**
	 * Load up the texture atlas.
	 * 
	 * @param assetManager
	 *            The asset manager this class will use.
	 */
	public void init(AssetManager assetManager)
	{
		this.assetManager = assetManager;
		// set asset manager error handler
		assetManager.setErrorListener(this);
		// load texture atlas
		assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
		// load sounds
		assetManager.load("sounds/jump.wav", Sound.class);
		assetManager.load("sounds/jump_with_feather.wav", Sound.class);
		assetManager.load("sounds/pickup_coin.wav", Sound.class);
		assetManager.load("sounds/pickup_feather.wav", Sound.class);
		assetManager.load("sounds/live_lost.wav", Sound.class);
		// load music
		assetManager.load("music/keith303_-_brand_new_highscore.mp3", Music.class);
		// start loading assets and wait until finished
		assetManager.finishLoading();
		Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
		for (String a : assetManager.getAssetNames())
			Gdx.app.debug(TAG, "asset: " + a);

		TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);

		// enable texture filtering for pixel smoothing
		for (Texture t : atlas.getTextures())
		{
			t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}

		// create game resource objects\
		fonts = new AssetFonts();
		bunny = new AssetBunny(atlas);
		rock = new AssetRock(atlas);
		goldCoin = new AssetGoldCoin(atlas);
		feather = new AssetFeather(atlas);
		levelDecoration = new AssetLevelDecoration(atlas);
		sounds = new AssetSounds(assetManager);
		music = new AssetMusic(assetManager);
	}

	/**
	 * Tell the asset manager to to unload assets.
	 */
	@Override
	public void dispose()
	{
		assetManager.dispose();
		fonts.defaultSmall.dispose();
		fonts.defaultNormal.dispose();
		fonts.defaultBig.dispose();
	}

	/**
	 * Called when the asset manager has an error with an asset, prints out an error
	 * log.
	 */
	@Override
	public void error(AssetDescriptor asset, Throwable throwable)
	{
		Gdx.app.error(TAG, "Couldn't load asset '" + asset.fileName + "'", (Exception) throwable);
	}

	/**
	 * @author Kevin Rutter Initializes and holds the game's decorative textures.
	 */
	public class AssetLevelDecoration 
	{
		public final AtlasRegion cloud01;
		public final AtlasRegion cloud02;
		public final AtlasRegion cloud03;
		public final AtlasRegion mountainLeft;
		public final AtlasRegion mountainRight;
		public final AtlasRegion waterOverlay;
		public final AtlasRegion carrot;
		public final AtlasRegion goal;

		/**
		 * Initialize atlas regions.
		 * 
		 * @param atlas
		 *            The texture atlas being used.
		 */
		public AssetLevelDecoration(TextureAtlas atlas)
		{
			cloud01 = atlas.findRegion("cloud01");
			cloud02 = atlas.findRegion("cloud02");
			cloud03 = atlas.findRegion("cloud03");
			mountainLeft = atlas.findRegion("mountain_left");
			mountainRight = atlas.findRegion("mountain_right");
			waterOverlay = atlas.findRegion("water_overlay");
			carrot = atlas.findRegion("carrot");
			goal = atlas.findRegion("goal");
		}
	}

	/**
	 * @author Gabe Werick This class holds info for the Bunny Head texture
	 */
	public class AssetBunny
	{
		public final AtlasRegion head;

		/**
		 * Sets head to hold the reference to the correct region for bunny_head
		 * 
		 * @param atlas
		 *            Texture atlas
		 */
		public AssetBunny(TextureAtlas atlas)
		{
			head = atlas.findRegion("bunny_head");
		}
	}

	/**
	 * @author Gabe Werick This class holds info for rock edge and middle texture
	 */
	public class AssetRock
	{
		public final AtlasRegion edge;
		public final AtlasRegion middle;

		/**
		 * sets edge and middle to hold references to the appropriate areas
		 * 
		 * @param atlas
		 *            Texture atlas
		 */
		public AssetRock(TextureAtlas atlas)
		{
			edge = atlas.findRegion("rock_edge");
			middle = atlas.findRegion("rock_middle");
		}
	}

	/**
	 * @author Gabe Werick This class holds info for the Gold Coin texture
	 */
	public class AssetGoldCoin
	{
		public final AtlasRegion goldCoin;

		/**
		 * Sets head to hold the reference to the correct region for Gold Coin
		 * 
		 * @param atlas
		 *            Texture atlas
		 */
		public AssetGoldCoin(TextureAtlas atlas)
		{
			goldCoin = atlas.findRegion("item_gold_coin");
		}
	}

	/**
	 * @author Gabe Werick This class holds info for the Feather texture
	 */
	public class AssetFeather
	{
		public final AtlasRegion feather;

		/**
		 * Sets head to hold the reference to the correct region for Gold Coin
		 * 
		 * @param atlas
		 *            Texture atlas
		 */
		public AssetFeather(TextureAtlas atlas)
		{
			feather = atlas.findRegion("item_feather");

		}

	}

	/**
	 * @Author Tyler Forrester This class holds info for the sounds
	 */
	public class AssetSounds
	{
		public final Sound jump;
		public final Sound jumpWithFeather;
		public final Sound pickupCoin;
		public final Sound pickupFeather;
		public final Sound liveLost;

		public AssetSounds(AssetManager am)
		{
			jump = am.get("sounds/jump.wav", Sound.class);
			jumpWithFeather = am.get("sounds/jump_with_feather.wav", Sound.class);
			pickupCoin = am.get("sounds/pickup_coin.wav", Sound.class);
			pickupFeather = am.get("sounds/pickup_feather.wav", Sound.class);
			liveLost = am.get("sounds/live_lost.wav", Sound.class);
		}
	}

	/**
	 * @Author Tyler Forrester This class holds info for the game music
	 */
	public class AssetMusic
	{
		public final Music song01;

		public AssetMusic(AssetManager am)
		{
			song01 = am.get("music/keith303_-_brand_new_highscore.mp3", Music.class);
		}
	}

}