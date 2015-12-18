package com.mygdx.desertcommander;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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
    public Texture leftArrow, rightArrow;

    public Sound healthPickupSFX;
    public Sound coinPickupSFX;
    public Sound endgameSFX;
    public Sound hurtSFX;
    public Sound waterJugSFX;

    public Texture TitleScreen;

    public Texture sandA, sandB, sandC, sandD, sandE;

    public Texture InstructionA, InstructionB,InstructionC,InstructionD,InstructionE,InstructionF, InstructionG, InstructionH;

    public AssetInitializer(boolean init){

        //init tells whether the instance should load all the assets
        if(init){
            TitleScreen = new Texture("TitleScreen.png");

            waterJugSFX = Gdx.audio.newSound(Gdx.files.internal("WaterJugPickupSFX.wav"));
            healthPickupSFX = Gdx.audio.newSound(Gdx.files.internal("ChickenPickUpSFX.wav"));
            coinPickupSFX = Gdx.audio.newSound(Gdx.files.internal("CoinPickupSFX.wav"));
            endgameSFX = Gdx.audio.newSound(Gdx.files.internal("EndgameSFX.wav"));
            hurtSFX = Gdx.audio.newSound(Gdx.files.internal("HurtSFX.wav"));

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

            leftArrow = new Texture("leftarrow.png");
            rightArrow = new Texture("rightarrow.png");


            InstructionA = new Texture("Instructions1.png");
            InstructionB = new Texture("Instructions2.png");
            InstructionC = new Texture("Instructions2.5.png");
            InstructionD = new Texture("Instructions3.png");
            InstructionE = new Texture("Instructions4.png");
            InstructionF = new Texture("Instructions5.png");
            InstructionG = new Texture("Instructions6.png");
            InstructionH = new Texture("Instructions7.png");
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

    public Texture[] getInstructionsScreens(){
        Texture[] screens = new Texture[8];
        screens[0] =     InstructionA;
        screens[1] =  InstructionB;
        screens[2] =  InstructionC;
        screens[3] =   InstructionD;
        screens[4] =   InstructionE;
        screens[5] =    InstructionF;
        screens[6] =    InstructionG;
        screens[7] =     InstructionH;

        return  screens;
    }







}
