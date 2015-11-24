package com.mygdx.desertcommander;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import org.w3c.dom.css.Rect;


import java.util.ArrayList;

import sun.rmi.runtime.Log;

/**
 * Created by jordan on 11/4/15.
 */
public class Player {
    private Vector2 Position;
    private Vector2 Direction;
    private Animation PlayerAnimimation;
    private float AnimStateTime;
    private float Speed;

    public Rectangle HitBox;
    public int Width, Height;


    public Player(float x, float y,int width, int height, Texture img){
        Width = (int)((width/8) * 0.75f);
        Height = (int)((height/8) * 0.75f);
        Position = new Vector2(x,y);
        AnimStateTime = 0.0f;
        TextureRegion[] textureArray = new TextureRegion[2];
        textureArray[0] =  new TextureRegion(img, 0, 0, 16, 16);
        textureArray[1] =  new TextureRegion(img, 16, 0, 16, 16);
        PlayerAnimimation = new Animation(1.0f,textureArray);
        PlayerAnimimation.setPlayMode(Animation.PlayMode.LOOP);
        HitBox = new Rectangle(x,y,Width,Height);
        Direction = new Vector2(1,0);
        Speed = 8.0f;

    }

    public void draw(SpriteBatch spritebatch, OrthographicCamera mainCam) {
        //Gdx.app.log("MAINCAM", mainCam.position.toString());
        AnimStateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = PlayerAnimimation.getKeyFrame(AnimStateTime, true);
        spritebatch.draw(currentFrame, Position.x, Position.y, Width,Height);

        if (Position.y <= 0) {
            Position.y = 0;
        }
        if (Position.y + currentFrame.getRegionHeight() >= Gdx.graphics.getHeight()) {
            Position.y = Gdx.graphics.getHeight() - Height;
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
    public void checkCollision(ArrayList<LevelChunk> chunklist){
        for(int i = 0; i< chunklist.size(); i++){
            ArrayList<Obstacle> ObjList = chunklist.get(i).ObstacleMasterList;
            for(int j = 0; j < ObjList.size(); j++){
                if(this.HitBox.overlaps(ObjList.get(j).getHitBox())){
                    Gdx.app.log("Collision Detected","TRUE");
                }
            }
        }
    }

    public void move(){
        //Gdx.app.log("PLAYERPOS", Position.toString());
        //move the player in the current Direction
        Position = new Vector2(Position.x + (Direction.x * Speed), Position.y + (Direction.y * Speed));

        //set the position of the hitbox
        //hitBox.setPosition(Position);
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

}