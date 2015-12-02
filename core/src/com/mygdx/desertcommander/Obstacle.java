package com.mygdx.desertcommander;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by jordan on 11/4/15.
 */
public class Obstacle {

    private Vector2 Position;
    private boolean isDeadly;
    private Rectangle hitBox;
    private Texture PTexture;
    private boolean isActive;
    private int TileDimension;

    public Obstacle(Vector2 position, boolean deadly, Texture texture, int tileDimension){
        TileDimension = tileDimension;
        isActive = true;
        Position = position;
        isDeadly = deadly;
        PTexture = texture;
        hitBox = new Rectangle(position.x, position.y, tileDimension/2, tileDimension/2);
    }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(PTexture, Position.x, Position.y, TileDimension, TileDimension);
        hitBox.setPosition(Position.x + TileDimension/4, Position.y + TileDimension/4);
    }
    public boolean getActive(){
        return isActive;
    }
    public void setActive(boolean newActive){
        isActive = newActive;
    }

    public Vector2 getPosition(){
        return Position;
    }
    public void setPosition(Vector2 newPos){
        Position = newPos;
    }
    public boolean getisDeadly(){
        return isDeadly;
    }
    public Rectangle getHitBox(){
        return hitBox;
    }

}
