package com.mygdx.desertcommander;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Jordan on 11/24/2015.
 */
public class MainMenuScreen {
    MainMenuInputProcessor input;
    boolean enabled;
    MainMenuButton startButton;
    public MainMenuScreen(){
        Gdx.input.setInputProcessor(input);
        enabled = true;
        startButton = new MainMenuButton(100,100, 10.0f, new Texture("startbutton.png"));
        input = new MainMenuInputProcessor(startButton);
    }

    public void draw(SpriteBatch spriteBatch){
        if(enabled){
            startButton.draw(spriteBatch);
        }
    }


}
