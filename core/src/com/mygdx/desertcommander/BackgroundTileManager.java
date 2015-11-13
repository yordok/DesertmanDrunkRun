package com.mygdx.desertcommander;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jordan on 11/6/15.
 */
public class BackgroundTileManager {

    private int tileWidth;
    private int tileHeight;
    private int TilesByScreenWidth;
    private int TilesByScreenHeight;
    private AssetInitializer AI;
    private ArrayList<Texture> BackgroundTextures;
    private ArrayList<BackgroundTile> MasterTileList;
    private ArrayList<BackgroundTileStrip> MasterTileStripList;
    private Random rnd;
    private int RedrawCycles;
    public BackgroundTileManager(AssetInitializer assetInitializer, int TileWidth, int TileHeight, int ScreenWidthTiles, int ScreenHeightTiles){
        AI = assetInitializer;
        rnd = new Random();
        RedrawCycles = 0;
        BackgroundTextures = AI.getBackgroundTiles();
        MasterTileStripList = new ArrayList<BackgroundTileStrip>();
        MasterTileList = new ArrayList<BackgroundTile>();
        tileHeight = TileHeight;
        tileWidth = TileWidth;
        TilesByScreenHeight = ScreenHeightTiles;
        TilesByScreenWidth = ScreenWidthTiles;

        this.generateTileMap();
    }

    public void draw(SpriteBatch spriteBatch, OrthographicCamera mainCam){
        /*
        for(int i = -1; i < TilesByScreenWidth; i++){
            for(int j = -1; j < TilesByScreenHeight; j++){
                if(mainCam.frustum.pointInFrustum((tileWidth * i) -100 , tileHeight*j , 0) || mainCam.frustum.pointInFrustum((tileWidth * i) + 100, tileHeight*j , 0)) {
                    spriteBatch.draw(AI.backgrounddirtSprite, tileWidth * i, tileHeight * j, tileWidth, tileHeight);
                }
            }
        }
        */

        for(int i =0; i< MasterTileStripList.size(); i++){
            BackgroundTileStrip b = MasterTileStripList.get(i);

            if(b.getPosX() < (mainCam.position.x - (Gdx.graphics.getWidth()/2) - 200)){
                BackgroundTileStrip bc = MasterTileStripList.get(MasterTileStripList.size() - 1);
                MasterTileStripList.remove(b);
                b.setPosX(bc.getPosX() + tileWidth);
                MasterTileStripList.add(b);
            }

            b.draw(spriteBatch);
        }

    }

    public void generateTileMap(){

        for(int i = -1; i < TilesByScreenWidth; i++){
            BackgroundTileStrip newBCKStrip = new BackgroundTileStrip(i * tileWidth, tileHeight, TilesByScreenHeight);
            for(int j = -1; j < TilesByScreenHeight; j++) {
                BackgroundTile b = new BackgroundTile(tileHeight,tileWidth, BackgroundTextures.get(rnd.nextInt(5)));
                newBCKStrip.getTilesinStrip().add(b);

            }
            MasterTileStripList.add(newBCKStrip);
        }

    }
}
