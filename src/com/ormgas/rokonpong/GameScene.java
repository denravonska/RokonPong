package com.ormgas.rokonpong;

import android.view.MotionEvent;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.stickycoding.rokon.PhysicalSprite;
import com.stickycoding.rokon.Scene;
import com.stickycoding.rokon.Sprite;
import com.stickycoding.rokon.audio.RokonAudio;
import com.stickycoding.rokon.audio.RokonMusic;
import com.stickycoding.rokon.audio.SoundFile;
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
	
	private RokonAudio mAudio;
	private SoundFile mLaserSound;

	public GameScene()
	{
		super(2, 3);
		
		mBackground = new FixedBackground(Textures.background);
		setBackground(mBackground);
	}
	
	@Override
	public void onGameLoop()
	{
		mSmallCloud.x += 0.05f;
		if(mSmallCloud.x > MainActivity.screenWidth)
		{
			mSmallCloud.x = -mSmallCloud.getWidth() -10;
		}

		mBigCloud.x += 0.1f;
		if(mBigCloud.x > MainActivity.screenWidth)
		{
			mBigCloud.x = -mBigCloud.getWidth() -10;
		}
		
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
		
		RokonMusic.play("trmpledbeta.mp3", true);
		mAudio = new RokonAudio();
		mLaserSound = mAudio.createSoundFile("laserweapon.mp3");
		
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
		this.add(1, mPeach);
		
		mMario = new PhysicalSprite(30.0f, MainActivity.screenHeight / 2, 32.0f, 64.0f);
		mMario.createDynamicBox();
		mMario.setTexture(Textures.mario);
		this.add(1, mMario);
		
		mBowser = new PhysicalSprite(MainActivity.screenWidth - 60.0f, MainActivity.screenHeight / 2, 64.0f, 64.0f);
		mBowser.createDynamicBox();
		mBowser.setTexture(Textures.bowser);
		this.add(1, mBowser);
		
		mSmallCloud = new Sprite(100.0f, 50.0f, Textures.smallcloud.getWidth(), Textures.smallcloud.getHeight());
		mSmallCloud.setTexture(Textures.smallcloud);
		this.add(0, mSmallCloud);

		mBigCloud = new Sprite(40.0f, 70.0f, Textures.bigcloud.getWidth(), Textures.bigcloud.getHeight());
		mSmallCloud.setTexture(Textures.bigcloud);
		this.add(0, mBigCloud);
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
		mLaserSound.play();
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
