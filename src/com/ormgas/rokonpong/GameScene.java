package com.ormgas.rokonpong;

import android.view.MotionEvent;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.stickycoding.rokon.PhysicalSprite;
import com.stickycoding.rokon.Scene;
import com.badlogic.gdx.math.Vector2;
public class GameScene extends Scene
{
	private World mWorld;
	private PhysicalSprite mPeach;
	private PhysicalSprite mMario;
	private PhysicalSprite mBowser;

	public GameScene()
	{
		super(1, 3);
	}
	
	@Override
	public void onGameLoop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReady()
	{
		CreateWorld();
		CreateSprites();
	}
	
	private void CreateWorld()
	{
		//mWorld = new World(new Vector2(40.0f, 20.0f), false);
		mWorld = new World(new Vector2(0.0f, 0.0f), false);
		
		PolygonShape eastWestShape = new PolygonShape();
		eastWestShape.setAsBox(10, MainActivity.screenHeight);
		
		PolygonShape northSouthShape = new PolygonShape();
		northSouthShape.setAsBox(MainActivity.screenWidth, 10);

		BodyDef northDef = new BodyDef();
		northDef.type = BodyDef.BodyType.StaticBody;
		northDef.position.set(new Vector2(0, 0));
		Body northBody = mWorld.createBody(northDef);
		northBody.createFixture(northSouthShape, 1.0f);
		
		BodyDef southDef = new BodyDef();
		southDef.type = BodyDef.BodyType.StaticBody;
		southDef.position.set(new Vector2(0, MainActivity.screenHeight));
		Body southBody = mWorld.createBody(southDef);
		southBody.createFixture(northSouthShape, 1.0f);
		
		BodyDef eastDef = new BodyDef();
		eastDef.type = BodyDef.BodyType.StaticBody;
		eastDef.position.set(new Vector2(MainActivity.screenWidth, 0));
		Body eastBody = mWorld.createBody(eastDef);
		eastBody.createFixture(eastWestShape, 1.0f);
				
		BodyDef westDef = new BodyDef();
		westDef.type = BodyDef.BodyType.StaticBody;
		westDef.position.set(new Vector2(0, 0));
		Body westBody = mWorld.createBody(westDef);
		westBody.createFixture(eastWestShape, 1.0f);

		this.setWorld(mWorld);
	}

	private void CreateSprites()
	{
		mPeach = new PhysicalSprite(50.0f, 50.0f, 28.0f, 48.0f);
		mPeach.createDynamicBox();
		mPeach.setTexture(Textures.peach);
		this.add(mPeach);
		
		mMario = new PhysicalSprite(100.0f, 100.0f, 50.0f, 50.0f);
		mMario.createDynamicBox();
		mMario.setTexture(Textures.mario);
		this.add(mMario);
		
		mBowser = new PhysicalSprite(200.0f, 77.0f, 50.0f, 50.0f);
		mBowser.createDynamicBox();
		mBowser.setTexture(Textures.bowser);
		this.add(mBowser);		
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onTouchDown(float x, float y, MotionEvent event, int pointerCount, int pointerId)
	{
		// This is called when you press down on the screen.
	}

	@Override
	public void onTouchMove(float x, float y, MotionEvent event, int pointerCount, int pointerId)
	{
		// This is called when you move your finger over the screen. (ie pretty much every frame if your holding your finger down)

		// Here we'll just make Bob follow your finger.
		//bob.x = x - (Textures.bob.getWidth() / 2);
		//bob.y = y - (Textures.bob.getHeight() / 2);
		
		//mMario.bodyDef.position.set(new Vector2(x, y));
		//mMario.x = x;
		//mMario.y = y;
		
		mMario.body.applyLinearImpulse(new Vector2(10000, 10000), new Vector2(x, y));
	}

	@Override
	public void onTouchUp(float x, float y, MotionEvent event, int pointerCount, int pointerId)
	{
		// And this is called when you stop pressing.
	}

}
