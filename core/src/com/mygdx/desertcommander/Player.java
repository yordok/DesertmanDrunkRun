package com.mygdx.desertcommander;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import org.w3c.dom.css.Rect;

import java.awt.Graphics;

import sun.rmi.runtime.Log;

/**
 * Created by jordan on 11/4/15.
 */
public class Player {
    private Vector2 Position;
    private Vector2 Direction;
    private Texture PTexture;
    private float Speed;
    private Rectangle hitBox;
    public int Width, Height;

    public Player(float x, float y,int width, int height, Texture img){
        Width = width;
        Height = height;
        Position = new Vector2(x,y);
        PTexture = img;
        Direction = new Vector2(1,0);
        Speed = 8.0f;
        hitBox = new Rectangle(Position.x, Position.y, PTexture.getWidth(), PTexture.getHeight());

    }

    public void draw(SpriteBatch spritebatch, OrthographicCamera mainCam) {
        //Gdx.app.log("MAINCAM", mainCam.position.toString());
        spritebatch.draw(PTexture, Position.x, Position.y);

        if (Position.y <= 0) {
            Position.y = 0;
        }
        if (Position.y + PTexture.getHeight() >= Gdx.graphics.getHeight()) {
            Position.y = Gdx.graphics.getHeight() - PTexture.getHeight();
        }
        Vector3 playerVec3 =  mainCam.project(new Vector3(Position.x, Position.y, 0.0f));
        if(playerVec3.x <= 0) {
            Vector3 reproject = mainCam.unproject(new Vector3(0, Position.y, 0.0f));
            Position.x = reproject.x;
        }
        if(playerVec3.x >= Gdx.graphics.getWidth()) {
            Vector3 reproject = mainCam.unproject(new Vector3(playerVec3.x, Position.y, 0.0f));
            Position.x = reproject.x;
        }

    }

    public void move(){
        //Gdx.app.log("PLAYERPOS", Position.toString());
        //move the player in the current Direction
        Position = new Vector2(Position.x + (Direction.x * Speed), Position.y + (Direction.y * Speed));

        //set the position of the hitbox
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