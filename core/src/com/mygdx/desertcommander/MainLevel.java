package com.mygdx.desertcommander;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

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
    float CameraScrollSpeed;
    boolean CameraIsScrolling;
    public Player player;
    AssetInitializer AI;
    BulletManager bulletManager;
    BackgroundTileManager BCKTileManager;
    public LevelChunkManager lvlChunkManager;
    OrthographicCamera mainCamera;

    //for debug use
    ShapeRenderer shapeRenderer;

    public boolean playing;

    public MainLevel(){
        playing = false;
    }

    public void generateGame(){
        AI = new AssetInitializer(true);
        //sets and creates the input
        Gdx.input.setInputProcessor(this.getSwipeInput());
        //managers...
        BCKTileManager = new BackgroundTileManager(AI,150,150, 20,10);
        bulletManager = new BulletManager();
        lvlChunkManager = new LevelChunkManager(500, 100, AI);
        mainCamera = new OrthographicCamera();
        mainCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        CameraIsScrolling = false;
        CameraScrollSpeed = 3.0f;
        shapeRenderer = new ShapeRenderer();
        player = new Player(20.0f,0,Gdx.graphics.getHeight(),Gdx.graphics.getHeight(),AI.playerSprite);
    }

    public void draw(SpriteBatch batch){

        this.update();
        batch.setProjectionMatrix(mainCamera.combined);

        BCKTileManager.draw(batch, mainCamera);
        lvlChunkManager.draw(batch);
        player.draw(batch, mainCamera);
        bulletManager.draw(batch);
    }

    public void update(){
        if(playing) {
            int screenMaxX = (int) mainCamera.position.x + Gdx.graphics.getWidth() / 2;
            int screenMinX = (int) mainCamera.position.x - Gdx.graphics.getWidth() / 2;
            this.handleCamera();
            bulletManager.update();
            lvlChunkManager.update(screenMaxX, screenMinX);
            player.checkCollision(lvlChunkManager.MasterChunkList);
            player.move();
        }
    }

    public void handleCamera(){
        //this needs to be fixed, needs to be 300 in the camera space, not the world space
        CameraIsScrolling = true;

        if(CameraIsScrolling){
            mainCamera.position.set(mainCamera.position.x + CameraScrollSpeed, mainCamera.position.y, mainCamera.position.z);
            mainCamera.update();
        }

    }

    public InputProcessor getSwipeInput(){

        InputProcessor SwipeProcessor = new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {

            @Override
            public void onUp() {

                player.setDirection(new Vector2(0, 1));
            }

            @Override
            public void onRight() {
                player.setDirection(new Vector2(1, 0));

            }

            @Override
            public void onLeft() {
                player.setDirection(new Vector2(-1, 0));

            }

            @Override
            public void onDown() {
                player.setDirection(new Vector2(0, -1));

            }


        });

        return SwipeProcessor;
    }
}
