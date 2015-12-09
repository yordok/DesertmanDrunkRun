package com.mygdx.desertcommander;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import java.util.ArrayList;


//TODO
//create art: change barrel to Gravel or something like that
//create art: draw a sack of gold or something that would mean more points
//create a points/score system
//overall balancing, espcially of how fast the scroll speed increases and hit detection on bad things ie cactuses
//add more level chunk shells
//add gold sack code in
//perhaps make UI larger
//STRETCH GOAL: add in something that fires at the player.

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
    InGameUI HUD;
    OrthographicCamera mainCamera;
    boolean hasSwiped;
    int ScrnWidth, ScrnHeight;
    int WaterValue;
    float WaterDepletionRate;
    float fakeDistance;

    boolean drawingEndOfLevelUI;
    boolean LEVELENDED;

    //for debug use
    ShapeRenderer shapeRenderer;

    public boolean playing;

    public MainLevel(){
        playing = false;
    }

    public void generateGame(AssetInitializer ai, OrthographicCamera camera){
        hasSwiped = false;
        ScrnWidth = Gdx.graphics.getWidth();
        ScrnHeight = Gdx.graphics.getHeight();
        AI = ai;
        //sets and creates the input
        Gdx.input.setInputProcessor(this.getSwipeInput());
        //managers...
        BCKTileManager = new BackgroundTileManager(AI,150,150, 20,10);
        bulletManager = new BulletManager();
        lvlChunkManager = new LevelChunkManager(500, 100, AI);

        mainCamera = camera;

        CameraIsScrolling = false;
        drawingEndOfLevelUI = false;
        CameraScrollSpeed = 3.0f;
        WaterValue = 5;
        fakeDistance = 0;
        WaterDepletionRate = 0.05f;
        HUD = new InGameUI(AI.redCrossSprite, AI.blueBarSprite, AI.jugSprite, AI.DistanceFont, AI.DeathFont);
        shapeRenderer = new ShapeRenderer();
        LEVELENDED = false;
        player = new Player(20.0f,0,ScrnHeight,ScrnHeight,AI.playerSprite);
    }

    public void draw(SpriteBatch batch){
        this.update();
        BCKTileManager.draw(batch, mainCamera);
        lvlChunkManager.draw(batch);
        player.draw(batch, mainCamera);
        bulletManager.draw(batch);
        HUD.draw(batch, player.health, (int)mainCamera.position.x - ScrnWidth/2, player.waterLevel, fakeDistance, drawingEndOfLevelUI);
    }

    public void update(){
        if(playing) {
            this.increaseSpeed();
            int screenMaxX = (int) mainCamera.position.x + ScrnWidth / 2;
            int screenMinX = (int) mainCamera.position.x - ScrnWidth / 2;
            this.handleCamera();
            player.update(WaterDepletionRate);
            bulletManager.update();
            lvlChunkManager.update(screenMaxX, screenMinX);
            checkCollision(lvlChunkManager.MasterChunkList);
            fakeDistance += Gdx.graphics.getDeltaTime();
            player.move();
            //player runs out of health
            if(player.health <= 0){
                playing = false;
                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run() {
                        //do some cool graphic here to end level
                        drawingEndOfLevelUI = true;

                    }
                }, 2.0f);

                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run() {
                        LEVELENDED = true;

                    }
                }, 6.0f);

            }
            //player runs out of water
            if(player.waterLevel <= 0){
                playing = false;
                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run() {
                        //do some cool graphic here to end level
                        drawingEndOfLevelUI = true;

                    }
                }, 2.0f);
                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run() {
                        LEVELENDED = true;

                    }
                }, 6.0f);

            }
        }else{

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

    public void checkCollision(ArrayList<LevelChunk> chunklist){
        for(int i = 0; i< chunklist.size(); i++){
            ArrayList<Obstacle> ObjList = chunklist.get(i).ObstacleMasterList;
            for(int j = 0; j < ObjList.size(); j++){
                if(player.HitBox.overlaps(ObjList.get(j).getHitBox())){
                    Obstacle obs = ObjList.get(j);
                    if(obs instanceof Cactus){
                        if(player.isInvulnerable == false) {
                            player.health--;
                            Gdx.app.log("Collision Detected", "CACTUS");
                            player.isInvulnerable = true;
                        }
                    }
                    if(obs instanceof Barrel){
                        if(player.isSlowed == false){
                            player.isSlowed = true;
                            player.setSpeed(player.getSpeed() - 3.0f);
                            Gdx.app.log("Collision Detected","BARREL");
                        }

                    }
                    if(obs instanceof WaterJug){
                        WaterJug ch = (WaterJug)obs;
                        ObjList.remove(ch);
                        player.waterLevel += WaterValue;
                        Gdx.app.log("Collision Detected","PICKUP WATERJUG");
                    }
                    if(obs instanceof HealthPickUp){
                        HealthPickUp hp = (HealthPickUp)obs;
                        ObjList.remove(hp);
                        if(player.health ==3){
                            player.health++;
                        }

                        Gdx.app.log("Collision Detected","PICKUP HEALTH");
                    }
                }
            }
        }
    }

    public void increaseSpeed(){
        CameraScrollSpeed += 0.00075f;
        player.setSpeed(player.getSpeed() + 0.00075f);
    }

    public InputProcessor getSwipeInput(){

        InputProcessor SwipeProcessor = new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {

            @Override
            public void onUp() {

                if(player.getDirection().y == 1){
                    player.setDirection(new Vector2(0, 1.5f));
                }
                else{
                    player.setDirection(new Vector2(0, 1));

                }
            }

            @Override
            public void onRight() {
                if(player.getDirection().x == 1){
                    player.setDirection(new Vector2(1.5f, 0));
                }
                else{
                    player.setDirection(new Vector2(1, 0));

                }

            }

            @Override
            public void onLeft() {
                if(player.getDirection().x == -1){
                    player.setDirection(new Vector2(-1.5f, 0));
                }
                else{
                    player.setDirection(new Vector2(-1, 0));

                }

            }

            @Override
            public void onDown() {
                if(player.getDirection().y == -1){
                    player.setDirection(new Vector2(0, -1.5f));
                }
                else{
                    player.setDirection(new Vector2(0, -1));

                }

            }


        });

        return SwipeProcessor;
    }
}
