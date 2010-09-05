package com.ormgas.rokonpong;

import android.view.MotionEvent;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.stickycoding.rokon.PhysicalSprite;
import com.stickycoding.rokon.Scene;
import com.stickycoding.rokon.device.Accelerometer;
import com.stickycoding.rokon.device.OnAccelerometerChange;
import com.badlogic.gdx.math.Vector2;


public class GameScene extends Scene implements OnAccelerometerChange
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
		
		Accelerometer.startListening(this);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		
	}
	
	private void CreateWorld()
	{
		mWorld = new World(new Vector2(0.0f, 0.0f), true);
		
		PolygonShape eastWestShape = new PolygonShape();
		eastWestShape.setAsBox(10, MainActivity.screenHeight);
		
		PolygonShape northSouthShape = new PolygonShape();
		northSouthShape.setAsBox(MainActivity.screenWidth, 10);

		BodyDef northDef = new BodyDef();
		northDef.type = BodyDef.BodyType.StaticBody;
		northDef.linearDamping = 0.0f;
		northDef.angularDamping = 0.0f;
		northDef.position.set(new Vector2(0, 0));
		Body northBody = mWorld.createBody(northDef);
		northBody.createFixture(northSouthShape, 1.0f);
		
		BodyDef southDef = new BodyDef();
		southDef.type = BodyDef.BodyType.StaticBody;
		southDef.linearDamping = 0.0f;
		southDef.angularDamping = 0.0f;
		southDef.position.set(new Vector2(0, MainActivity.screenHeight));
		Body southBody = mWorld.createBody(southDef);
		southBody.createFixture(northSouthShape, 1.0f);
		
		BodyDef eastDef = new BodyDef();
		eastDef.type = BodyDef.BodyType.StaticBody;
		eastDef.linearDamping = 0.0f;
		eastDef.angularDamping = 0.0f;
		eastDef.position.set(new Vector2(MainActivity.screenWidth, 0));
		Body eastBody = mWorld.createBody(eastDef);
		eastBody.createFixture(eastWestShape, 1.0f);
				
		BodyDef westDef = new BodyDef();
		westDef.type = BodyDef.BodyType.StaticBody;
		westDef.linearDamping = 0.0f;
		westDef.angularDamping = 0.0f;
		westDef.position.set(new Vector2(0, 0));
		Body westBody = mWorld.createBody(westDef);
		westBody.createFixture(eastWestShape, 1.0f);

		this.setWorld(mWorld);
	}

	private void CreateSprites()
	{
		mPeach = new PhysicalSprite(MainActivity.screenWidth / 2, MainActivity.screenHeight / 2, 28.0f, 48.0f);
		mPeach.createDynamicCircle();
		mPeach.setTexture(Textures.peach);
		this.add(mPeach);
		
		mMario = new PhysicalSprite(MainActivity.screenWidth - 30.0f, MainActivity.screenHeight / 2, 50.0f, 50.0f);
		mMario.createDynamicBox();
		mMario.setTexture(Textures.mario);
		this.add(mMario);
		
		mBowser = new PhysicalSprite(30.0f, MainActivity.screenHeight / 2, 50.0f, 50.0f);
		mBowser.createDynamicBox();
		mBowser.setTexture(Textures.bowser);
		this.add(mBowser);		
	}

	@Override
	public void onTouchDown(float x, float y, MotionEvent event, int pointerCount, int pointerId)
	{
		// This is called when you press down on the screen.
	}

	@Override
	public void onTouchMove(float x, float y, MotionEvent event, int pointerCount, int pointerId)
	{
		//mMario.body.applyForce(new Vector2(10000, 10000), new Vector2(0, 0));
	}

	@Override
	public void onTouchUp(float x, float y, MotionEvent event, int pointerCount, int pointerId)
	{
		// And this is called when you stop pressing.
	}

	@Override
	public void onAccelerometerChange(float x, float y, float z)
	{
		mPeach.body.applyForce(new Vector2(y * 100000, x * 100000), new Vector2(0, 0));		
	}

	@Override
	public void onShake(float intensity) {
		// TODO Auto-generated method stub
		
	}

}
