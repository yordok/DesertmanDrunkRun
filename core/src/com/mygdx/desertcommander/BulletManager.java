package com.mygdx.desertcommander;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by jordan on 11/6/15.
 */
public class BulletManager {
    ArrayList<Bullet> Bullets;

    public BulletManager(){
        Bullets = new ArrayList<Bullet>();
    }

    public void draw(SpriteBatch spriteBatch){
        for (Bullet b: Bullets) {
            b.draw(spriteBatch);
        }
    }

    public void update(){
        for (Bullet b: Bullets) {

            b.update();
        }
    }

    public void addBullet(Bullet b){
        Bullets.add(b);
    }

}
