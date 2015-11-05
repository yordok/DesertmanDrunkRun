package com.mygdx.desertcommander;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import org.w3c.dom.css.Rect;

import java.awt.Graphics;

/**
 * Created by jordan on 11/4/15.
 */
public class Player {
    private Vector2 Position;
    private Vector2 Direction;
    private Texture PTexture;
    private float Speed;
    private Rectangle hitBox;

    public Player(float x, float y, Texture img){
        Position = new Vector2(x,y);
        PTexture = img;
        Direction = new Vector2(1,0);
        Speed = 3.0f;

        hitBox = new Rectangle(Position.x, Position.y, PTexture.getWidth(), PTexture.getHeight());

    }

    public void draw(SpriteBatch spritebatch){
        spritebatch.draw(PTexture, Position.x, Position.y);
    }

    public void move(){
        Position = new Vector2(Position.x + (Direction.x * Speed), Position.y + (Direction.y * Speed));
        if(Position.y <= 0){
            Position.y = 0;
        }
        if(Position.y + PTexture.getHeight() >= Gdx.graphics.getHeight()){
            Position.y  = Gdx.graphics.getHeight() - + PTexture.getHeight();

        }
        hitBox.setPosition(Position);
    }
    public float getSpeed(){
        return Speed;
    }
    public void setSpeed(float newSpeed){
        Speed = newSpeed;
    }

    public Vector2 getPosition(){
        return Position;
    }
    public void setPosition(Vector2 newPos){Position = newPos; }
    public Vector2 getDirection(){
        return Direction;
    }
    public void setDirection(Vector2 newDirect){
        Direction = newDirect;
    }
    public Texture getTexture(){
        return PTexture;
    }

}
