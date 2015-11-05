package com.mygdx.desertcommander;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by jordan on 11/4/15.
 */
public class AssetInitializer {

    public Texture playerSprite;
    public Texture obstacleSprite;

    public AssetInitializer(){
        playerSprite = new Texture("player.png");
        obstacleSprite = new Texture("Cactus.png");
    }



}
