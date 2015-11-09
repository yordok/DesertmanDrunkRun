package com.mygdx.desertcommander;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by jordan on 11/6/15.
 */
public class BackgroundTileManager {

    int tileWidth;
    int tileHeight;
    int TilesByScreenWidth;
    int TilesByScreenHeight;
    AssetInitializer AI;
    public BackgroundTileManager(AssetInitializer assetInitializer, int TileWidth, int TileHeight, int ScreenWidthTiles, int ScreenHeightTiles){
        AI = assetInitializer;
        tileHeight = TileHeight;
        tileWidth = TileWidth;
        TilesByScreenHeight = ScreenHeightTiles;
        TilesByScreenWidth = ScreenWidthTiles;
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
}
