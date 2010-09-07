
package com.ormgas.rokonpong;

import java.util.HashMap;

import com.stickycoding.rokon.RokonActivity;
import com.stickycoding.rokon.Scene;

public class SceneHandler
{
	private RokonActivity mActivity;
	private HashMap<String, Scene> mHashScenes = new HashMap<String, Scene>();
	
	public SceneHandler(RokonActivity activity)
	{
		mActivity = activity;
	}
	
	public void AddScene(String key, Scene scene)
	{
		mHashScenes.put(key, scene);
	}
	
	public void NextScene()
	{
		// Fix this...
	}
	
	public void SetScene(String key)
	{
		mActivity.setScene(mHashScenes.get(key));
	}
}
