package com.ormgas.rokonpong;

import android.view.MotionEvent;

import com.stickycoding.rokon.Scene;
import com.stickycoding.rokon.Sprite;
import com.stickycoding.rokon.Time;

public class StartScene extends Scene
{
	private SceneHandler theSceneHandler;
	
	private boolean sceneDone = false;
	private float endTick = 0.0f;
	private Sprite peach;

	public StartScene(SceneHandler sceneHandler)
	{
		super(1, 3);
		theSceneHandler = sceneHandler;
	}
	
	@Override
	public void onGameLoop()
	{
		// After 5 seconds, or on touch a sprite, go to next scene.
		// This aint working
		if(Time.getTicks() > endTick)
			sceneDone = true;
		
		if(sceneDone)
			theSceneHandler.SetScene("GameScene");
	}

	@Override
	public void onReady()
	{
		peach = new Sprite(MainActivity.screenWidth / 2, MainActivity.screenHeight / 2, 28.0f * MainActivity.scaleFactor, 48.0f * MainActivity.scaleFactor);
		peach.setTexture(Textures.peach);
		this.add(peach);
		
		endTick = Time.getTicks() + 500;
	}

	@Override
	public void onPause()
	{
	}


	@Override
	public void onResume()
	{
	}
	
	@Override
	public void onTouchUp(float x, float y, MotionEvent event, int pointerCount, int pointerId)
	{
		sceneDone = true;
	}


}
