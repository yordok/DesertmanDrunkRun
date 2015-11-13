package com.mygdx.desertcommander;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;



/**
 * Created by jmk1637 on 11/9/2015.
 */
public class BackgroundTile {

    private Vector2 Position;
    private Texture PTexture;
    private int Height, Width;

    public BackgroundTile(int height , int width, Texture texture){
        PTexture = texture;
        Height = height;
        Width = width;
    }

    public void setPosition(Vector2 position) {
        Position = position;
    }

    public Vector2 getPosition() {
        return Position;
    }
    public Texture getPTexture(){return PTexture;}
    public int getWidth(){return Width;}
    public int getHeight(){return Height;}
}
