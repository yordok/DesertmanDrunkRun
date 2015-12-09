package com.mygdx.desertcommander;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by jordan on 12/8/15.
 */
public class HealthPickUp extends Obstacle {

    public HealthPickUp(Vector2 position, Texture txt, int TileDimen){
        super(position, false, txt, TileDimen, 0.0f, 1.0f);

    }
}
