package com.mygdx.desertcommander;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Jordan on 11/24/2015.
 */
public class Barrel extends Obstacle {

    public Barrel(Vector2 position, AssetInitializer AI, int TileDimen){
        super(position, true, AI.barrelSprite, TileDimen, 0.25f, 0.5f);
    }
}
