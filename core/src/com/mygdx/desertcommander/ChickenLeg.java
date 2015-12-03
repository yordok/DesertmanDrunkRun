package com.mygdx.desertcommander;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Jordan on 12/2/2015.
 */
public class ChickenLeg extends Obstacle{
    public ChickenLeg(Vector2 position, AssetInitializer AI, int TileDimen){
        //vec2 pos, is deadly, sprite, dimension
        super(position, false, AI.chickenSprite, TileDimen);
        //need to make this work, increase the the size
        super.getHitBox().setPosition(position.x + TileDimen / 4, position.y + TileDimen / 4);


    }

    public void resolveCollsion(){
        if(super.getActive()){
            super.setActive(false);
        }

    }


}
