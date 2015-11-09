package com.mygdx.desertcommander;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import javafx.geometry.Pos;

/**
 * Created by jmk1637 on 11/9/2015.
 */
public class BackgroundTile {

    private Vector2 Position;
    private Texture PTexture;
    private int Height, Width;

    public BackgroundTile(Vector2 pos,int height , int width, Texture texture){
        Position = pos;
        PTexture = texture;
        Height = height;
        Width = width;
    }

    public void draw(SpriteBatch spriteBatch){
        spriteBatch.draw(PTexture, Position.x,Position.y,Width,Height);
    }

    public void setPosition(Vector2 position) {
        Position = position;
    }

    public Vector2 getPosition() {
        return Position;
    }
}
