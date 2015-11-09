package com.mygdx.desertcommander;

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
    private Random rnd;
    public BackgroundTileManager(AssetInitializer assetInitializer, int TileWidth, int TileHeight, int ScreenWidthTiles, int ScreenHeightTiles){
        AI = assetInitializer;
        //rnd = Random();
        BackgroundTextures = AI.getBackgroundTiles();
        MasterTileList = new ArrayList<BackgroundTile>();
        tileHeight = TileHeight;
        tileWidth = TileWidth;
        TilesByScreenHeight = ScreenHeightTiles;
        TilesByScreenWidth = ScreenWidthTiles;
        this.generateTileMap();
    }

    public void draw(SpriteBatch spriteBatch, OrthographicCamera mainCam){

        for(int i = -1; i < TilesByScreenWidth; i++){
            for(int j = -1; j < TilesByScreenHeight; j++){
                if(mainCam.frustum.pointInFrustum((tileWidth * i) -100 , tileHeight*j , 0) || mainCam.frustum.pointInFrustum((tileWidth * i) + 100, tileHeight*j , 0)) {
                    spriteBatch.draw(AI.backgrounddirtSprite, tileWidth * i, tileHeight * j, tileWidth, tileHeight);
                }
            }
        }

    }

    public void generateTileMap(){
        for(int i = -1; i < TilesByScreenWidth; i++){
            for(int j = -1; j < TilesByScreenHeight; j++) {
                Vector2 vec2 = new Vector2(tileWidth * i, tileHeight * j);
                //add random sprites
               // MasterTileList.add(new BackgroundTile(vec2, tileWidth, tileHeight, ))
            }
        }

    }
}
