package com.mygdx.desertcommander;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Jordan on 11/24/2015.
 */
public class MainMenuButton {
    private int X, Y;
    private float ScaleFactor;
    private int Width, Height;
    private Texture PTexture;
    public Rectangle HitBox;

    public MainMenuButton(int x, int y, float scaleFactor, Texture texture){
        X= x;
        Y = y;
        ScaleFactor = scaleFactor;
        Width = (int)(texture.getWidth() * scaleFactor);
        Height = (int)(texture.getHeight() * scaleFactor);
        HitBox = new Rectangle(x,y,Width,Height);
        PTexture = texture;
    }

    public void draw(SpriteBatch spriteBatch){

        spriteBatch.draw(PTexture,X,Y,Width,Height);
    }





}
