package com.mygdx.desertcommander;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Jordan on 11/24/2015.
 */
public class MainMenuScreen {

    boolean enabled;
    public MainMenuButton startButton;
    public MainMenuScreen(){
        enabled = true;
        startButton = new MainMenuButton(100,100, 10.0f, new Texture("startbutton.png"));

    }

    public void draw(SpriteBatch spriteBatch){
        if(enabled){
            startButton.draw(spriteBatch);
        }
    }


}
