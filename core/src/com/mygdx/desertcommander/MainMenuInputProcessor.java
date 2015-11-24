package com.mygdx.desertcommander;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by Jordan on 11/24/2015.
 */
public class MainMenuInputProcessor implements InputProcessor {
    public ArrayList<MainMenuButton> Buttons;
    public MainMenuButton StartButton;
    public MainMenuInputProcessor(MainMenuButton start){
        StartButton = start;
    }

    public boolean keyDown (int keycode) {
        return false;
    }

    public boolean keyUp (int keycode) {
        return false;
    }

    public boolean keyTyped (char character) {
        return false;
    }

    public boolean touchDown (int x, int y, int pointer, int button) {

            Vector2 point = new Vector2(x,y);
            if(StartButton.HitBox.contains(point)){
                return true;
            }

        return false;
    }

    public boolean touchUp (int x, int y, int pointer, int button) {

        return false;
    }

    public boolean touchDragged (int x, int y, int pointer) {
        return false;
    }

    public boolean mouseMoved (int x, int y) {
        return false;
    }

    public boolean scrolled (int amount) {
        return false;
    }

}
