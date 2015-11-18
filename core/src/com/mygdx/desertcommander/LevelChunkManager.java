package com.mygdx.desertcommander;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.nio.Buffer;
import java.util.ArrayList;

/**
 * Created by jordan on 11/16/15.
 */
public class LevelChunkManager {

    int BeginSpace, BufferSpace;
    ArrayList<LevelChunk> MasterChunkList;
    int tileDimension;
    int levelTileLength;
    AssetInitializer AI;
    public LevelChunkManager(int beginSpace, int bufferSpace, AssetInitializer assetI){
        //set tile height and width of each tile
        tileDimension = Gdx.graphics.getHeight() / 8;
        Gdx.app.log("TILE DIMENSION" ,tileDimension + "");
        levelTileLength = 0;
        //asset initializer
        AI = assetI;
        //begin space is the space before the level begins
        BeginSpace = beginSpace;
        //bufferspace is the space between each chunk
        BufferSpace = bufferSpace;
        MasterChunkList = new ArrayList<LevelChunk>();
        addChunk(0);
        levelTileLength = BeginSpace;
    }
    public void update(){

    }
    public void draw(SpriteBatch spriteBatch){

        for(int i =0; i < MasterChunkList.size(); i++){
            ArrayList<Obstacle> obstaclelist = MasterChunkList.get(i).ObstacleMasterList;

            for(int j =0; j < obstaclelist.size(); j++){
                obstaclelist.get(j).draw(spriteBatch);
            }
        }
    }
    public void addChunk(int type){
        LevelChunk LC = new LevelChunk(type, levelTileLength + BufferSpace, tileDimension, AI);
        MasterChunkList.add(LC);
    }
}
