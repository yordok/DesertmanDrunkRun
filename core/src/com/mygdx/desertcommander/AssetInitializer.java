package com.mygdx.desertcommander;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by jordan on 11/4/15.
 */
public class AssetInitializer {

    public Texture playerSprite;
    public Texture obstacleSprite;
    public Texture backgrounddirtSprite;
    public Texture bulletSprite;

    public AssetInitializer(boolean init){
        //init tells whether the instance should load all the assets
        if(init){
            playerSprite = new Texture("player.png");
            backgrounddirtSprite = new Texture("dirt.png");
            obstacleSprite = new Texture("Cactus.png");
            bulletSprite = new Texture("bullet.png");
        }
        else{

        }

    }







}
