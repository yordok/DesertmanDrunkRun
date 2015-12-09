package com.mygdx.desertcommander;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by jordan on 11/4/15.
 */
public class Cactus extends Obstacle {


    public Cactus(Vector2 position, Texture txt, int TileDimen){
        super(position, true, txt, TileDimen, 0.25f, 0.5f);
    }

    public void resolveCollsion(){
        if(super.getActive()){

        }
        else{

        }
    }




}
