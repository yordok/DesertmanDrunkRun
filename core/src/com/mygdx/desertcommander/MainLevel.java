package com.mygdx.desertcommander;

/**
 * Created by jordan on 11/17/15.
 */
/*
we need this to:
create a level
restart the level
change between the game and the menu
*/
public class MainLevel {
    public boolean paused;

    public MainLevel(){
        paused = true;
    }

    public void generateGame(){

    }

    public void pauseGame(){
        paused = true;
    }

    public void unpauseGame(){
        paused = false;
    }
}
