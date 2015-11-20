package com.mygdx.desertcommander;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by jordan on 11/16/15.
 */
public class LevelChunk {

    public ArrayList<Obstacle> ObstacleMasterList;
    public int[][] LevelChunkShell;
    public int NumofRows;
    public int LengthinTiles;
    public int LengthinPixels;
    public int initialX;
    public int TileDimension;

    private AssetInitializer AI;

    public LevelChunk(int type, int initX, int tileDimen, AssetInitializer assetI){
        ObstacleMasterList = new ArrayList<Obstacle>();
        TileDimension = tileDimen;
        NumofRows = 8;
        initialX = initX;
        AI = assetI;
        this.generateChunkShell(type);
    }

    public void generateChunkShell(int type){
        if(type == 0){
            LengthinTiles = 6;
            LengthinPixels = LengthinTiles * TileDimension;
            LevelChunkShell = new int[LengthinTiles][NumofRows];
            LevelChunkShell[0][0] = 1;
            LevelChunkShell[1][0] = 1;
            LevelChunkShell[1][1] = 1;
            LevelChunkShell[3][3] = 1;
            LevelChunkShell[3][4] = 1;
            LevelChunkShell[4][4] = 1;
            LevelChunkShell[4][5] = 1;
            LevelChunkShell[5][5] = 1;
        }
        if(type == 1){
            LengthinTiles = 5;
            LengthinPixels = LengthinTiles * TileDimension;
            LevelChunkShell = new int[LengthinTiles][NumofRows];
            LevelChunkShell[0][0] = 1;
            LevelChunkShell[0][1] = 1;
            LevelChunkShell[0][2] = 1;
            LevelChunkShell[0][3] = 1;
            LevelChunkShell[0][4] = 1;
            LevelChunkShell[0][5] = 1;
            LevelChunkShell[0][6] = 1;
            LevelChunkShell[0][7] = 1;


        }
        if(type == 2){
            LengthinTiles = 5;
            LengthinPixels = LengthinTiles * TileDimension;
            LevelChunkShell = new int[LengthinTiles][NumofRows];
            LevelChunkShell[0][1] = 1;
            LevelChunkShell[1][1] = 1;
            LevelChunkShell[1][2] = 1;
            LevelChunkShell[4][3] = 1;
            LevelChunkShell[0][4] = 1;
            LevelChunkShell[3][5] = 1;
            LevelChunkShell[2][6] = 1;
            LevelChunkShell[0][7] = 1;


        }

        generateChunkObject(LevelChunkShell);
    }

    public void generateChunkObject(int[][] shell){
        for(int x = 0; x < LengthinTiles; x++ ){
            for(int y = 0; y < NumofRows; y++){
                if(shell[x][y] == 1){
                    Vector2 pos = new Vector2((x * TileDimension) + initialX, (y * TileDimension));
                    Gdx.app.log("Positions: ", pos + "");
                    Cactus c = new Cactus(pos, AI, TileDimension);
                    ObstacleMasterList.add(c);
                }


            }
        }
    }


}
