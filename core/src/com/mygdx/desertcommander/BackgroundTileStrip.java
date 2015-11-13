package com.mygdx.desertcommander;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by jordan on 11/12/15.
 */
public class BackgroundTileStrip {

    private int PositionX;
    private int TileHeightPixels, StripHeightTiles;
    private ArrayList<BackgroundTile> tilesInStrip;

    public BackgroundTileStrip(int PosX, int tileheight, int stripHeight){
        PositionX = PosX;
        TileHeightPixels = tileheight;
        StripHeightTiles = stripHeight;
        tilesInStrip = new ArrayList<BackgroundTile>();
    }

    public void draw(SpriteBatch spriteBatch){
        for (int i = 0; i< tilesInStrip.size(); i++) {
            BackgroundTile b = tilesInStrip.get(i);
            spriteBatch.draw(b.getPTexture(), PositionX, i * TileHeightPixels, TileHeightPixels, TileHeightPixels);
        }
    }

    public ArrayList<BackgroundTile> getTilesinStrip(){
        return tilesInStrip;
    }

    public int getPosX(){
        return PositionX;
    }

    public void setPosX(int newX){
        PositionX = newX;
    }

}
