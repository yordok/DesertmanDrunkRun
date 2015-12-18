package com.mygdx.desertcommander;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.xml.soap.Text;

/**
 * Created by Jordan on 11/24/2015.
 */
public class MainMenuScreen {

    boolean enabled;
    public MainMenuButton startButton;
    public MainMenuButton instructButton;
    public Texture TitleScreen;
    public MainMenuScreen(){
        enabled = true;
        startButton = new MainMenuButton(100,100, 10.0f, new Texture("startbutton.png"));
        instructButton = new MainMenuButton(Gdx.graphics.getWidth()-100 - (80 * 10), 100,10.0f, new Texture("instructionbutton.png"));
        TitleScreen = new Texture("TitleScreen.png");
    }

    public void draw(SpriteBatch spriteBatch){
            spriteBatch.draw(TitleScreen,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            startButton.draw(spriteBatch);
            instructButton.draw(spriteBatch);
    }


}
