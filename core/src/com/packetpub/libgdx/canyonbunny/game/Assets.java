package com.packetpub.libgdx.canyonbunny.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;
import com.packetpub.libgdx.canyonbunny.util.Constants;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

/**
 * @author Kevin Rutter Handles texture loading using a texture atlas.
 */
public class Assets implements Disposable, AssetErrorListener {
	public static final String TAG = Assets.class.getName();

	public static final Assets instance = new Assets();

	private AssetManager assetManager;

	public AssetBunny bunny;
	public AssetRock rock;
	public AssetGoldCoin goldCoin;
	public AssetFeather feather;
	public AssetLevelDecoration levelDecoration;

	// singleton: prevent instantiation from other classes
	private Assets() {
	}

	/**
	 * Load up the texture atlas.
	 * 
	 * @param assetManager
	 *            The asset manager this class will use.
	 */
	public void init(AssetManager assetManager) {
		this.assetManager = assetManager;
		// set asset manager error handler
		assetManager.setErrorListener(this);
		// load texture atlas
		assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
		// start loading assets and wait until finished
		assetManager.finishLoading();
		Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
		for (String a : assetManager.getAssetNames())
			Gdx.app.debug(TAG, "asset: " + a);

		TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);

		// enable texture filtering for pixel smoothing
		for (Texture t : atlas.getTextures()) {
			t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}

		// create game resource objects
		bunny = new AssetBunny(atlas);
		rock = new AssetRock(atlas);
		goldCoin = new AssetGoldCoin(atlas);
		feather = new AssetFeather(atlas);
		levelDecoration = new AssetLevelDecoration(atlas);
	}

	/**
	 * Tell the asset manager to to unload assets.
	 */
	@Override
	public void dispose() {
		assetManager.dispose();
	}

	/**
	 * Called when the asset manager has an error with an asset, prints out an error
	 * log.
	 */
	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.error(TAG, "Couldn't load asset '" + asset.fileName + "'", (Exception) throwable);
	}

	/**
	 * @author Kevin Rutter Initializes and holds the game's decorative textures.
	 */
	public class AssetLevelDecoration {
		public final AtlasRegion cloud01;
		public final AtlasRegion cloud02;
		public final AtlasRegion cloud03;
		public final AtlasRegion mountainLeft;
		public final AtlasRegion mountainRight;
		public final AtlasRegion waterOverlay;

		/**
		 * Initialize atlas regions.
		 * 
		 * @param atlas
		 *            The texture atlas being used.
		 */
		public AssetLevelDecoration(TextureAtlas atlas) {
			cloud01 = atlas.findRegion("cloud01");
			cloud02 = atlas.findRegion("cloud02");
			cloud03 = atlas.findRegion("cloud03");
			mountainLeft = atlas.findRegion("mountain_left");
			mountainRight = atlas.findRegion("mountain_right");
			waterOverlay = atlas.findRegion("water_overlay");
		}
	}

	/**
	 * @author Gabe Werick This class holds info for the Bunny Head texture
	 */
	public class AssetBunny {
		public final AtlasRegion head;

		/**
		 * Sets head to hold the reference to the correct region for bunny_head
		 * 
		 * @param atlas
		 *            Texture atlas
		 */
		public AssetBunny(TextureAtlas atlas) {
			head = atlas.findRegion("bunny_head");
		}
	}

	/**
	 * @author Gabe Werick This class holds info for rock edge and middle texture
	 */
	public class AssetRock {
		public final AtlasRegion edge;
		public final AtlasRegion middle;

		/**
		 * sets edge and middle to hold references to the appropriate areas
		 * 
		 * @param atlas
		 *            Texture atlas
		 */
		public AssetRock(TextureAtlas atlas) {
			edge = atlas.findRegion("rock_edge");
			middle = atlas.findRegion("rock_middle");
		}
	}

	/**
	 * @author Gabe Werick This class holds info for the Gold Coin texture
	 */
	public class AssetGoldCoin {
		public final AtlasRegion goldCoin;

		/**
		 * Sets head to hold the reference to the correct region for Gold Coin
		 * 
		 * @param atlas
		 *            Texture atlas
		 */
		public AssetGoldCoin(TextureAtlas atlas) {
			goldCoin = atlas.findRegion("item_gold_coin");
		}
	}

	/**
	 * @author Gabe Werick This class holds info for the Feather texture
	 */
	public class AssetFeather {
		public final AtlasRegion feather;

		/**
		 * Sets head to hold the reference to the correct region for Gold Coin
		 * 
		 * @param atlas
		 *            Texture atlas
		 */
		public AssetFeather(TextureAtlas atlas) {
			feather = atlas.findRegion("item_feather");
		}
	}

}