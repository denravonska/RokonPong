package com.ormgas.rokonpong;

import com.stickycoding.rokon.Texture;
import com.stickycoding.rokon.TextureAtlas;

public class Textures {

	public static TextureAtlas atlas;
	public static Texture background;
	public static Texture peach;
	public static Texture mario;
	public static Texture bowser;
	public static Texture smallcloud;
	public static Texture bigcloud;
	
	public static void load()
	{
		atlas = new TextureAtlas();
		
		background = new Texture("background.png");
		atlas.insert(background);
		
		peach = new Texture("peach.png");
		atlas.insert(peach);
		
		mario = new Texture("mario.png");
		atlas.insert(mario);
		
		bowser = new Texture("bowser.png");
		atlas.insert(bowser);
		
		bigcloud = new Texture("bigcloud.png");
		atlas.insert(bigcloud);

		smallcloud = new Texture("smallcloud.png");
		atlas.insert(smallcloud);
		
		atlas.complete();
	}
}
