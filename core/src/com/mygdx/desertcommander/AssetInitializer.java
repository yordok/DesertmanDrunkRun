package com.mygdx.desertcommander;

import com.badlogic.gdx.graphics.Texture;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by jordan on 11/4/15.
 */
public class AssetInitializer {

    public Texture playerSprite;
    public Texture cactusSprite;
    public Texture bulletSprite;
    public Texture barrelSprite;

    public Texture startbuttonSprite;

    public Texture sandA, sandB, sandC, sandD, sandE;

    public AssetInitializer(boolean init){

        //init tells whether the instance should load all the assets
        if(init){
            playerSprite = new Texture("playerSheet.png");
            cactusSprite = new Texture("cactus.png");
            bulletSprite = new Texture("bullet.png");
            barrelSprite = new Texture("Barrel.png");

            startbuttonSprite = new Texture("startbutton.png");

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
