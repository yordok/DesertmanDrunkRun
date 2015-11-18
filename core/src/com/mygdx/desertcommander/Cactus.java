package com.mygdx.desertcommander;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by jordan on 11/4/15.
 */
public class Cactus extends Obstacle {


    public Cactus(Vector2 position, AssetInitializer AI, int TileDimen){
        super(position, true, AI.cactusSprite, TileDimen);
    }




}
