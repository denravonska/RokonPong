package com.ormgas.rokonpong;

import com.stickycoding.rokon.Texture;
import com.stickycoding.rokon.TextureAtlas;

public class Textures {

	public static TextureAtlas atlas;
	public static Texture mBackground;
	public static Texture peach;
	public static Texture mario;
	public static Texture bowser;
	
	public static void load()
	{
		atlas = new TextureAtlas();
		
		peach = new Texture("peach.png");
		atlas.insert(peach);
		
		mario = new Texture("mario.png");
		atlas.insert(mario);
		
		bowser = new Texture("bowser.png");
		atlas.insert(bowser);
		
		atlas.complete();
	}
}
