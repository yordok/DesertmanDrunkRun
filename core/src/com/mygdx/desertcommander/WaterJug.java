package com.mygdx.desertcommander;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Jordan on 12/2/2015.
 */
public class WaterJug extends Obstacle{
    public WaterJug(Vector2 position, Texture txt, int TileDimen){
        //vec2 pos, is deadly, sprite, dimension
        super(position, false, txt, TileDimen, 0.0f, 1.0f);
        //need to make this work, increase the the size


    }



}
