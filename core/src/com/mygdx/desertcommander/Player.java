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
    public boolean isInvulnerable;
    public boolean isSlowed;
    public int health;
    public float waterLevel;

    private float slowedTimer;
    private float invulnerableTimer;



    public Player(float x, float y,int width, int height, Texture img){
        Width = (int)((width/8) * 0.75f);
        Height = (int)((height/8) * 0.75f);
        Position = new Vector2(x,y);
        AnimStateTime = 0.0f;
        health = 3;
        TextureRegion[] textureArray = new TextureRegion[2];
        textureArray[0] =  new TextureRegion(img, 0, 0, 16, 16);
        textureArray[1] =  new TextureRegion(img, 16, 0, 16, 16);
        PlayerAnimimation = new Animation(0.5f,textureArray);
        PlayerAnimimation.setPlayMode(Animation.PlayMode.LOOP);
        HitBox = new Rectangle();
        isInvulnerable = false;
        isSlowed = false;
        HitBox.set(x, y, Width, Height);
        Direction = new Vector2(1,0);
        invulnerableTimer = 0.0f;
        slowedTimer = 0.0f;
        Speed = 8.0f;
        waterLevel = 40.0f;

    }

    public void draw(SpriteBatch spritebatch, OrthographicCamera mainCam) {
        //Gdx.app.log("MAINCAM", mainCam.position.toString());
        if(isInvulnerable==false) {
            AnimStateTime += Gdx.graphics.getDeltaTime();
            TextureRegion currentFrame = PlayerAnimimation.getKeyFrame(AnimStateTime, true);
            spritebatch.draw(currentFrame, Position.x, Position.y, Width, Height);
            HitBox.set(Position.x + Width / 4, Position.y + Height / 4, Width / 2, Height / 2);


            if (Position.y <= 0) {
                Position.y = 0;
            }
            if (Position.y + currentFrame.getRegionHeight() >= Gdx.graphics.getHeight()) {
                Position.y = Gdx.graphics.getHeight() - Height;
            }
            Vector3 playerVec3 = mainCam.project(new Vector3(Position.x, Position.y, 0.0f));
            if (playerVec3.x <= 0) {
                Vector3 reproject = mainCam.unproject(new Vector3(0, Position.y, 0.0f));
                Position.x = reproject.x;
            }
            if (playerVec3.x >= Gdx.graphics.getWidth()) {
                Vector3 reproject = mainCam.unproject(new Vector3(playerVec3.x, Position.y, 0.0f));
                Position.x = reproject.x;
            }
        }
        else{
            if((invulnerableTimer % 0.2f) < 0.1f ) {
                AnimStateTime += Gdx.graphics.getDeltaTime();
                TextureRegion currentFrame = PlayerAnimimation.getKeyFrame(AnimStateTime, true);
                spritebatch.draw(currentFrame, Position.x, Position.y, Width, Height);
                HitBox.set(Position.x + Width / 4, Position.y + Height / 4, Width / 2, Height / 2);


                if (Position.y <= 0) {
                    Position.y = 0;
                }
                if (Position.y + currentFrame.getRegionHeight() >= Gdx.graphics.getHeight()) {
                    Position.y = Gdx.graphics.getHeight() - Height;
                }
                Vector3 playerVec3 = mainCam.project(new Vector3(Position.x, Position.y, 0.0f));
                if (playerVec3.x <= 0) {
                    Vector3 reproject = mainCam.unproject(new Vector3(0, Position.y, 0.0f));
                    Position.x = reproject.x;
                }
                if (playerVec3.x >= Gdx.graphics.getWidth()) {
                    Vector3 reproject = mainCam.unproject(new Vector3(playerVec3.x, Position.y, 0.0f));
                    Position.x = reproject.x;
                }
            }

        }

    }
    public void update(float waterDepletionRate){

        if(isInvulnerable){
            Gdx.app.log("PLAYER IS NOW", "INVULNERABLE");
            invulnerableTimer += Gdx.graphics.getDeltaTime();
            if(invulnerableTimer >= 3){
                invulnerableTimer = 0.0f;
                isInvulnerable = false;
            }

        }
        if(isSlowed){
            Gdx.app.log("PLAYER IS NOW", "SLOWED");
            slowedTimer += Gdx.graphics.getDeltaTime();
            if(slowedTimer >= 2.5f){
                this.Speed =  this.Speed + 3.0f;
                slowedTimer = 0.0f;
                isSlowed = false;
            }

        }

        waterLevel -= waterDepletionRate;

        if(health >3){
            health = 3;
        }
        if(health < 0){
            health =0;
        }
        if(waterLevel > 40.0f){
            waterLevel = 40.0f;
        }
        if(waterLevel< 0.0f){
            waterLevel = 0.0f;
        }

    }


    public void move() {
        Position = new Vector2(Position.x + (Direction.x * Speed), Position.y + (Direction.y * Speed));


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