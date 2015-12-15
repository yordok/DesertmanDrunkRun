package com.mygdx.desertcommander;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Jordan on 12/15/2015.
 */
public class Coin extends Obstacle {
    public Coin(Vector2 position, Texture txt, int TileDimen){
        super(position, true, txt, TileDimen, 0.0f, 1.0f);
    }
}
