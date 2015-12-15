package com.mygdx.desertcommander;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

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
    private Random rnd;

    public LevelChunk(int type, int initX, int tileDimen, AssetInitializer assetI){
        ObstacleMasterList = new ArrayList<Obstacle>();
        TileDimension = tileDimen;
        NumofRows = 8;
        initialX = initX;
        AI = assetI;
        rnd = new Random();
        this.generateChunkShell(type);
    }

    public void generateChunkShell(int type){
        if(type == 0){
            LengthinTiles = 6;
            LengthinPixels = LengthinTiles * TileDimension;
            LevelChunkShell = new int[LengthinTiles][NumofRows];
            LevelChunkShell[0][0] = 1;
            LevelChunkShell[1][0] = 2;
            LevelChunkShell[1][1] = 1;
            LevelChunkShell[3][3] = 1;
            LevelChunkShell[3][4] = 2;
            LevelChunkShell[4][4] = 1;
            LevelChunkShell[4][5] = 2;
            LevelChunkShell[5][5] = 2;
        }
        if(type == 1){
            LengthinTiles = 5;
            LengthinPixels = LengthinTiles * TileDimension;
            LevelChunkShell = new int[LengthinTiles][NumofRows];
            LevelChunkShell[4][0] = 1;
            LevelChunkShell[4][1] = 2;
            LevelChunkShell[3][2] = 1;
            LevelChunkShell[0][3] = 2;
            LevelChunkShell[2][4] = 2;
            LevelChunkShell[0][5] = 1;
            LevelChunkShell[1][5] = 1;
            LevelChunkShell[2][5] = 1;
        }
        if(type == 2){
            LengthinTiles = 5;
            LengthinPixels = LengthinTiles * TileDimension;
            LevelChunkShell = new int[LengthinTiles][NumofRows];
            LevelChunkShell[0][1] = 2;
            LevelChunkShell[1][1] = 1;
            LevelChunkShell[1][2] = 1;
            LevelChunkShell[4][3] = 2;
            LevelChunkShell[0][4] = 1;
            LevelChunkShell[3][5] = 1;
            LevelChunkShell[2][6] = 1;
            LevelChunkShell[0][7] = 1;
        }

        generateChunkObject(LevelChunkShell);
    }
    //this creates a list of objects and where to draw them based on the level chunk
    //1 = cactus
    //2 = barrel
    public void generateChunkObject(int[][] shell){
        int Chikencount = 0;
        for(int x = 0; x < LengthinTiles; x++ ){
            for(int y = 0; y < NumofRows; y++){
                if(shell[x][y] == 1){
                    Vector2 pos = new Vector2((x * TileDimension) + initialX, (y * TileDimension));
                    Gdx.app.log("Positions: ", pos + "");
                    Cactus c = new Cactus(pos, AI.cactusSprite, TileDimension);
                    ObstacleMasterList.add(c);
                }
                if(shell[x][y] == 2){
                    Vector2 pos = new Vector2((x * TileDimension) + initialX, (y * TileDimension));
                    Gdx.app.log("Positions: ", pos + "");
                    Barrel b = new Barrel(pos, AI.barrelSprite, (int)(TileDimension * 0.8f));
                    ObstacleMasterList.add(b);
                }
                if(rnd.nextInt(100)< 10){
                    if(shell[x][y] == 0){
                        if(Chikencount < LengthinTiles-1) {
                            shell[x][y] = 3;
                            Vector2 pos = new Vector2((x * TileDimension) + initialX, (y * TileDimension));
                            Gdx.app.log("Positions: ", pos + "");
                            WaterJug ch = new WaterJug(pos, AI.jugSprite, (int) (TileDimension * 0.8f));
                            ObstacleMasterList.add(ch);
                            Chikencount++;
                        }
                    }
                }
                if(rnd.nextInt(500) <= 1){
                    if(shell[x][y] == 0){
                        shell[x][y] = 4;
                        Vector2 pos = new Vector2((x * TileDimension) + initialX, (y * TileDimension));
                        Gdx.app.log("Positions: ", pos + "");
                        HealthPickUp ch = new HealthPickUp(pos, AI.chickenSprite, (int) (TileDimension * 0.8f));
                        ObstacleMasterList.add(ch);
                    }
                }
                //use for coin
                if(rnd.nextInt(750) <= 1){
                    if(shell[x][y] == 0){
                        shell[x][y] = 5;
                        Vector2 pos = new Vector2((x * TileDimension) + initialX, (y * TileDimension));
                        Gdx.app.log("Positions: ", pos + "");
                        Coin coin = new Coin(pos, AI.coinTexture, (int) (TileDimension * 0.8f));
                        ObstacleMasterList.add(coin);
                    }
                }



            }
        }
    }


}
