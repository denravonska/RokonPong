package com.ormgas.rokonpong;

import com.stickycoding.rokon.DrawPriority;
import com.stickycoding.rokon.RokonActivity;
import android.view.Display;

public class MainActivity extends RokonActivity {
	
	public static int screenWidth = 0;
	public static int screenHeight = 0;
	private GameScene gameScene;
	
    /** Called when the activity is first created. */
    public void onCreate() {
    	debugMode();
    	forceFullscreen();
    	forceLandscape();
    	    	
    	Display display = getWindowManager().getDefaultDisplay();
    	screenWidth = display.getWidth();
    	screenHeight = display.getHeight();
    	this.setGameSize(screenWidth, screenHeight);
    	setDrawPriority(DrawPriority.PRIORITY_NORMAL);
    	setGraphicsPath("textures/");
    	createEngine();
    }
    
    public void onLoadComplete() {
    	Textures.load();
    	gameScene = new GameScene();
    	setScene(gameScene);
    }
}