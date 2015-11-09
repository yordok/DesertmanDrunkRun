package com.mygdx.desertcommander;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**
 * Created by jordan on 11/4/15.
 */
public class AssetInitializer {

    public Texture playerSprite;
    public Texture obstacleSprite;
    public Texture backgrounddirtSprite;
    public Texture bulletSprite;

    public Texture sandA, sandB, sandC, sandD, sandE;

    public AssetInitializer(boolean init){

        //init tells whether the instance should load all the assets
        if(init){
            playerSprite = new Texture("player.png");
            backgrounddirtSprite = new Texture("dirt.png");
            obstacleSprite = new Texture("Cactus.png");
            bulletSprite = new Texture("bullet.png");

            sandA = new Texture("sandA.png");
            sandB = new Texture("sandB.png");
            sandC = new Texture("sandC.png");
            sandD = new Texture("sandD.png");
            sandE = new Texture("sandE.png");
        }
        else{

        }

    }

    public ArrayList getBackgroundTiles(){
        ArrayList<Texture> bcktext = new ArrayList<Texture>();
        bcktext.add(sandA);
        bcktext.add(sandB);
        bcktext.add(sandC);
        bcktext.add(sandD);
        bcktext.add(sandE);
        return bcktext;
    }







}
