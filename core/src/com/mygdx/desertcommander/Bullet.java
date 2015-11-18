package com.mygdx.desertcommander;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by jordan on 11/6/15.
 */
public class Bullet {

    private Vector2 Position;
    private Vector2 Direction;
    private Texture PTexture;
    private float Speed;

    public Bullet(Vector2 startingPos, Vector2 direction, Texture texture){
        Position = startingPos;
        Direction = direction;
        PTexture = texture;
        Speed = 20.0f;

    }
    public void update(){
        Position = new Vector2(Position.x + (Direction.nor().x * Speed), Position.y + (Direction.nor().y* Speed));
    }

    public void draw(SpriteBatch spriteBatch){
        spriteBatch.draw(PTexture, Position.x, Position.y, 50, 50);
    }
}
