package com.ormgas.rokonpong;

import com.stickycoding.rokon.DrawPriority;
import com.stickycoding.rokon.RokonActivity;
import android.view.Display;

public class MainActivity extends RokonActivity
{
	public static float screenWidth = 0.0f;
	public static float screenHeight = 0.0f;
	public static float scaleFactor = 0.0f;
	
	private static SceneHandler sceneHandler;

    /** Called when the activity is first created. */
    public void onCreate()
    { 
    	debugMode();
    	forceFullscreen();
    	forceLandscape();
    	    	
    	screenWidth = 16.0f;
    	screenHeight = 9.6f;
    	
       	Display display = getWindowManager().getDefaultDisplay();
    	scaleFactor = screenWidth / display.getWidth();
    	
    	this.setGameSize(screenWidth, screenHeight);
    	setDrawPriority(DrawPriority.PRIORITY_NORMAL);
    	setGraphicsPath("textures/");
    	createEngine();
    }
    
    public void onLoadComplete()
    {
    	Textures.load();
    	Sounds.load();
    	
    	CreateScenes();
    }
    
    private void CreateScenes()
    {
    	sceneHandler = new SceneHandler(this);

    	sceneHandler.AddScene("StartScene", new StartScene(sceneHandler));
        sceneHandler.AddScene("GameScene", new GameScene(sceneHandler));

        sceneHandler.SetScene("StartScene");
    }
}