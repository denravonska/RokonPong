
package com.ormgas.rokonpong;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.stickycoding.rokon.Sprite;
import com.stickycoding.rokon.modifiers.Blink;

public class CollisionHandler implements ContactListener
{

	@Override
	public void beginContact(Contact contact)
	{
		Fixture fixture1 = contact.getFixtureA();
		Fixture fixture2 = contact.getFixtureB();
		
		Body body1 = fixture1.getBody();
		Body body2 = fixture2.getBody();
		
		Sprite object1 = (Sprite)body1.getUserData();
		Sprite object2 = (Sprite)body2.getUserData();
		
		if(object1 != null)
		{
			object1.clearModifiers();
			object1.addModifier(new Blink());
		}
		
		if(object2 != null)
		{
			object2.clearModifiers();
			object2.addModifier(new Blink());
		}
		
	}

	@Override
	public void endContact(Contact contact)
	{
		// TODO Auto-generated method stub
	}

}
