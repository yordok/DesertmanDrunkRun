package com.mygdx.desertcommander;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Jordan on 11/25/2015.
 */
public class Turret extends Obstacle {

    public float StateTime;
    public float FireTimeOffsets;

    public Turret(Vector2 position, AssetInitializer AI, int TileDimen, BulletManager bulletManager){
        super(position, true, AI.cactusSprite, TileDimen, 0.25f, 0.5f);
        FireTimeOffsets = 5.0f;
        StateTime = 0.0f;
    }

    public void fireWeapon(){
        StateTime = Gdx.graphics.getDeltaTime();

    }


}
