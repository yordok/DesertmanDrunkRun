package com.mygdx.desertcommander;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jordan on 11/16/15.
 */
public class LevelChunkManager {

    int BeginSpace, BufferSpace;
    ArrayList<LevelChunk> MasterChunkList;
    int tileDimension;
    int levelTileLength;
    AssetInitializer AI;
    Random rnd;
    public LevelChunkManager(int beginSpace, int bufferSpace, AssetInitializer assetI){
        rnd = new Random();
        //set tile height and width of each tile
        tileDimension = Gdx.graphics.getHeight() / 8;
        levelTileLength = 0;
        //asset initializer
        AI = assetI;
        //begin space is the space before the level begins
        BeginSpace = beginSpace;
        //bufferspace is the space between each chunk
        BufferSpace = bufferSpace;
        MasterChunkList = new ArrayList<LevelChunk>();
        addChunk(-1);
        levelTileLength = BeginSpace;
    }
    public void update(int ScreenMaxX, int ScreenMinX){

        LevelChunk LastChunk = MasterChunkList.get(MasterChunkList.size() -1);
        LevelChunk FirstChunk = MasterChunkList.get(0);
        if((FirstChunk.initialX + FirstChunk.LengthinPixels) < (ScreenMinX)){
            MasterChunkList.remove(FirstChunk);
            Gdx.app.log("SIZE OF MASTER CHUNLIST:", MasterChunkList.size() +"");
        }
        if((LastChunk.initialX + LastChunk.LengthinPixels) < (ScreenMaxX)){
            addRandomChunkToEnd(ScreenMaxX);
        }

    }
    public void draw(SpriteBatch spriteBatch){
    //draw everything in the chunks
        for(int i =0; i < MasterChunkList.size(); i++){
            ArrayList<Obstacle> obstaclelist = MasterChunkList.get(i).ObstacleMasterList;
            for(int j =0; j < obstaclelist.size(); j++){
                obstaclelist.get(j).draw(spriteBatch);
            }
        }
    }

    public void addChunkToEnd(int type, int ScreenMaxX){
        LevelChunk LC = new LevelChunk(type, ScreenMaxX, tileDimension, AI);
        MasterChunkList.add(LC);

    }
    public void addChunk(int type){
        LevelChunk LC = new LevelChunk(type, levelTileLength + BufferSpace, tileDimension, AI);
        levelTileLength += BufferSpace + LC.LengthinPixels;
        MasterChunkList.add(LC);
    }

    public void addRandomChunkToEnd(int ScreenMaxX){
        int type = rnd.nextInt(5);
        addChunkToEnd(type, ScreenMaxX);
    }
}
