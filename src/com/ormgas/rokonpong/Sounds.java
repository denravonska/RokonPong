package com.ormgas.rokonpong;

import com.stickycoding.rokon.audio.RokonAudio;
import com.stickycoding.rokon.audio.SoundFile;

public class Sounds
{
	public static RokonAudio audio;
	public static SoundFile laser;

	public static void load()
	{
		audio = new RokonAudio();
		laser = audio.createSoundFile("laserweapon.mp3");
	}
}
