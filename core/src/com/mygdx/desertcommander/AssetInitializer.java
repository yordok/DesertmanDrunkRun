package com.mygdx.desertcommander;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

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
    public Texture chickenSprite;
    public Texture jugSprite;
    public Texture redCrossSprite;
    public Texture blueBarSprite;
    public Texture startbuttonSprite;
    public Texture coinTexture;
    public BitmapFont DeathFont, DistanceFont;

    public Texture sandA, sandB, sandC, sandD, sandE;

    public AssetInitializer(boolean init){

        //init tells whether the instance should load all the assets
        if(init){
            playerSprite = new Texture("playerSheet.png");
            cactusSprite = new Texture("cactus.png");
            bulletSprite = new Texture("bullet.png");
            barrelSprite = new Texture("Barrel.png");
            jugSprite = new Texture("waterjug.png");
            chickenSprite = new Texture("chickenleg.png");
            redCrossSprite = new Texture("RedCross.png");
            blueBarSprite = new Texture("bluebar.png");
            coinTexture = new Texture("coin.png");

            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("pixelmix.ttf"));
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = 20;
            parameter.color = Color.BLACK;
            DistanceFont = generator.generateFont(parameter); // font size 12 pixels
            parameter.size = 500;
            DeathFont = generator.generateFont(parameter);
            generator.dispose();

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
