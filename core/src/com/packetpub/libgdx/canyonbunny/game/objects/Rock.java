package com.packetpub.libgdx.canyonbunny.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.packetpub.libgdx.canyonbunny.game.Assets;


/**
 * Contains the information for creating and modifying the Rock object.
 * (the platforms the bunny jumps on and off)
 * @author Kevin Rutter
 */
public class Rock extends AbstractGameObject
{
	private TextureRegion regEdge;
	private TextureRegion regMiddle;
	
	private int length;
	
	/**
	 * Constructor for rock object.
	 */
	public Rock()
	{
		init();
	}
	
	/**
	 * Initialize the rock object by setting its dimensions, width, and height.
	 */
	private void init()
	{
		dimension.set(1, 1.5f);
		
		regEdge = Assets.instance.rock.edge;
		regMiddle = Assets.instance.rock.middle;
		
		// Start length of this rock
		setLength(1);		
	}
	
	/**
	 * Set the starting length of a rock.
	 * @param length	How wide (in meters) the rock will be.
	 */
	public void setLength(int length)
	{
		this.length = length;
	}
	
	/**
	 * Increase the length of a rock.
	 * @param amount	The amount of meters to increase the rocks length by.
	 */
	public void increaseLength(int amount)
	{
		setLength(length + amount);
	}
}