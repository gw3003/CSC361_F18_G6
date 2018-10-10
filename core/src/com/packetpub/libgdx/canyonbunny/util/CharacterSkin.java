package com.packetpub.libgdx.canyonbunny.util;

import com.badlogic.gdx.graphics.Color;

/**
 * Creates a enumeration of the character skin
 * 
 * @author Tyler Forrester
 *
 */
public enum CharacterSkin
{
	WHITE("White", 1.0f, 1.0f, 1.0f), GRAY("Gray", 0.7f, 0.7f, 0.7f), BROWN("Brown", 0.7f, 0.5f, 0.3f);

	private String name;
	private Color color = new Color();

	/**
	 * Creates the character skin instance
	 * 
	 * @param name
	 *            name of the skin
	 * @param r
	 *            red value
	 * @param g
	 *            green value
	 * @param b
	 *            blue value
	 */
	private CharacterSkin(String name, float r, float g, float b)
	{
		this.name = name;
		color.set(r, g, b, 1.0f);
	}

	/**
	 * Gets the name of the character skin
	 */
	@Override
	public String toString()
	{
		return name;
	}

	/**
	 * Gets teh character skin color
	 * 
	 * @return the character skin's color
	 */
	public Color getColor()
	{
		return color;
	}

}
