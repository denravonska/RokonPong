package com.ormgas.rokonpong;

import android.util.Log;
import android.view.MotionEvent;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.stickycoding.rokon.PhysicalSprite;
import com.stickycoding.rokon.Scene;
import com.stickycoding.rokon.Sprite;
import com.stickycoding.rokon.audio.RokonMusic;
import com.stickycoding.rokon.background.FixedBackground;
import com.stickycoding.rokon.device.Accelerometer;
import com.stickycoding.rokon.device.OnAccelerometerChange;
import com.badlogic.gdx.math.Vector2;


public class GameScene extends Scene implements OnAccelerometerChange
{
	private World mWorld;
	private PhysicalSprite mPeach;
	private PhysicalSprite mMario;
	private PhysicalSprite mBowser;
	
	private Sprite mSmallCloud;
	private Sprite mBigCloud;
	
	private FixedBackground mBackground;
	
	private SceneHandler theSceneHandler;
	private boolean sceneDone = false;
	
	public GameScene(SceneHandler sceneHandler)
	{
		// 2 layers with 3 sprites in each
		super(2, 3);
		
		theSceneHandler = sceneHandler;
	}
	
	@Override
	public void onGameLoop()
	{
		mSmallCloud.x += 0.002f;
		if(mSmallCloud.x > MainActivity.screenWidth)
		{
			mSmallCloud.x = -mSmallCloud.getWidth() -1;
		}

		mBigCloud.x += 0.005f;
		if(mBigCloud.x > MainActivity.screenWidth)
		{
			mBigCloud.x = -mBigCloud.getWidth() -1;
		}
		
		if(sceneDone)
			theSceneHandler.SetScene("StartScene");
	}

	@Override
	public void onReady()
	{
		mBackground = new FixedBackground(Textures.background);
		setBackground(mBackground);

		CreateWorld();
		CreateSprites();
		
		mWorld.setContactListener(new CollisionHandler());

		mPeach.body.applyLinearImpulse(new Vector2(1.1f, 1.1f), new Vector2(0, 0));
		
		onResume();
	}

	@Override
	public void onPause()
	{
		Accelerometer.stopListening();
		RokonMusic.stop();
	}


	@Override
	public void onResume()
	{
		Accelerometer.startListening(this);
		RokonMusic.play("trmpledbeta.mp3", true);
	}
	
	private void CreateWorld()
	{
		mWorld = new World(new Vector2(0.0f, 0.0f), true);
		
		PolygonShape eastWestShape = new PolygonShape();
		eastWestShape.setAsBox(1.0f, MainActivity.screenHeight);
		
		PolygonShape northSouthShape = new PolygonShape();
		northSouthShape.setAsBox(MainActivity.screenWidth, 1.0f);

		BodyDef northDef = new BodyDef();
		northDef.type = BodyDef.BodyType.StaticBody;
		northDef.position.set(new Vector2(0, 0));
		Body northBody = mWorld.createBody(northDef);
		FixtureDef northFixture = new FixtureDef();
		northFixture.restitution = 0.0f;
		northFixture.friction = 0.0f;
		northFixture.shape = northSouthShape;
		northBody.createFixture(northFixture);
		
		BodyDef southDef = new BodyDef();
		southDef.type = BodyDef.BodyType.StaticBody;
		southDef.position.set(new Vector2(0, MainActivity.screenHeight));
		Body southBody = mWorld.createBody(southDef);
		FixtureDef southFixture = new FixtureDef();
		southFixture.restitution = 0.0f;
		southFixture.friction = 0.0f;
		southFixture.shape = northSouthShape;
		southBody.createFixture(southFixture);
		
		BodyDef eastDef = new BodyDef();
		eastDef.type = BodyDef.BodyType.StaticBody;
		eastDef.position.set(new Vector2(MainActivity.screenWidth, 0));
		Body eastBody = mWorld.createBody(eastDef);
		FixtureDef eastFixture = new FixtureDef();
		eastFixture.restitution = 0.0f;
		eastFixture.friction = 0.0f;
		eastFixture.shape = eastWestShape;
		eastBody.createFixture(eastFixture);
				
		BodyDef westDef = new BodyDef();
		westDef.type = BodyDef.BodyType.StaticBody;
		westDef.position.set(new Vector2(0, 0));
		Body westBody = mWorld.createBody(westDef);
		FixtureDef westFixture = new FixtureDef();
		westFixture.restitution = 0.0f;
		westFixture.friction = 0.0f;
		westFixture.shape = eastWestShape;
		westBody.createFixture(westFixture);

		this.setWorld(mWorld);
	}

	private void CreateSprites()
	{
		mPeach = new PhysicalSprite(MainActivity.screenWidth / 2, MainActivity.screenHeight / 2, 28.0f * MainActivity.scaleFactor, 48.0f * MainActivity.scaleFactor);
		FixtureDef peachFixture = new FixtureDef();
		peachFixture.restitution = 1.0f;
		peachFixture.density = 0.0f;
		mPeach.createDynamicCircle(peachFixture);
		mPeach.body.setLinearDamping(0.0f);
		mPeach.body.setUserData(mPeach);
		mPeach.setTexture(Textures.peach);
		this.add(1, mPeach);
		
		mMario = new PhysicalSprite(1.0f, MainActivity.screenHeight / 2, 32.0f * MainActivity.scaleFactor, 64.0f * MainActivity.scaleFactor);
		FixtureDef marioFixture = new FixtureDef();
		marioFixture.restitution = 0.0f;
		mMario.createDynamicBox(marioFixture);
		mMario.body.setUserData(mMario);
		mMario.setTexture(Textures.mario);
		this.add(1, mMario);
		
		mBowser = new PhysicalSprite(MainActivity.screenWidth - 6.0f, MainActivity.screenHeight / 2, 64.0f * MainActivity.scaleFactor, 64.0f * MainActivity.scaleFactor);
		FixtureDef bowserFixture = new FixtureDef();
		bowserFixture.restitution = 0.0f;
		mBowser.createDynamicBox(bowserFixture);
		mBowser.body.setUserData(mBowser);
		mBowser.setTexture(Textures.bowser);
		this.add(1, mBowser);
		
		mSmallCloud = new Sprite(1.0f, 0.5f, Textures.smallcloud.getWidth() * MainActivity.scaleFactor * 2, Textures.smallcloud.getHeight() * MainActivity.scaleFactor * 2);
		mSmallCloud.setTexture(Textures.smallcloud);
		this.add(0, mSmallCloud);

		mBigCloud = new Sprite(5.4f, 1.0f, Textures.bigcloud.getWidth() * MainActivity.scaleFactor * 2, Textures.bigcloud.getHeight() * MainActivity.scaleFactor * 2);
		mBigCloud.setTexture(Textures.bigcloud);
		this.add(0, mBigCloud);
	}

	@Override
	public void onTouchDown(float x, float y, MotionEvent event, int pointerCount, int pointerId)
	{
		Sounds.laser.play();
	}

	@Override
	public void onTouchMove(float x, float y, MotionEvent event, int pointerCount, int pointerId)
	{
		//mMario.body.applyForce(new Vector2(10000, 10000), new Vector2(0, 0));
		float newY = y - (MainActivity.screenHeight / 2);
		Log.d("GameScene", String.valueOf(newY));
	}

	@Override
	public void onTouchUp(float x, float y, MotionEvent event, int pointerCount, int pointerId)
	{
		// And this is called when you stop pressing.
		//mPeach.body.applyForce(new Vector2(10.0f, 0.0f), new Vector2(0.0f, 0.0f));
	}

	@Override
	public void onAccelerometerChange(float x, float y, float z)
	{
		mMario.body.applyLinearImpulse(new Vector2(y * 0.1f, x * 0.1f), new Vector2(0.0f, 0.0f));
		
		//Log.d("Accel", "x: " + String.valueOf(x));
		//Log.d("Accel", "y: " + String.valueOf(y));
		//Log.d("Accel", "z: " + String.valueOf(z));
	}

	@Override
	public void onShake(float intensity)
	{
		
	}

}
