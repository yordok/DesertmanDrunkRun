package com.mygdx.desertcommander;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


public class MainGame extends ApplicationAdapter{
	SpriteBatch batch;
	float CameraScrollSpeed;
	boolean CameraIsScrolling;
	boolean stopInput;
	Player player;
	AssetInitializer AI;
	BulletManager bulletManager;
	BackgroundTileManager BCKTileManager;
	LevelChunkManager lvlChunkManager;
	OrthographicCamera mainCamera;


	@Override
	public void create () {
		batch = new SpriteBatch();
		AI = new AssetInitializer(true);
		//sets and creates the input
		InputMultiplexer InputMult = new InputMultiplexer();
		InputMult.addProcessor(this.getSwipeInput());
		InputMult.addProcessor(this.getTouchInput());
		Gdx.input.setInputProcessor(InputMult);
		stopInput = false;
		//managers...
		BCKTileManager = new BackgroundTileManager(AI,150,150, 20,10);
		bulletManager = new BulletManager();
		lvlChunkManager = new LevelChunkManager(500, 100, AI);
		mainCamera = new OrthographicCamera();
		mainCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		CameraIsScrolling = false;
		CameraScrollSpeed = 3.0f;
		player = new Player(20.0f,0,200,200,AI.playerSprite);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0.8f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.update();
		batch.setProjectionMatrix(mainCamera.combined);
		batch.begin();
		BCKTileManager.draw(batch, mainCamera);
		lvlChunkManager.draw(batch);
		player.draw(batch, mainCamera);
		bulletManager.draw(batch);
		batch.end();
	}

	public void update(){
		int screenMaxX = (int)mainCamera.position.x + Gdx.graphics.getWidth()/2;
		int screenMinX = (int)mainCamera.position.x - Gdx.graphics.getWidth()/2;
		this.handleCamera();
		bulletManager.update();
		lvlChunkManager.update(screenMaxX, screenMinX);
		player.move();

	}
	//an attempt to make the camera move
	public void handleCamera(){
		//this needs to be fixed, needs to be 300 in the camera space, not the world space

		CameraIsScrolling = true;

		if(CameraIsScrolling){
			mainCamera.position.set(mainCamera.position.x + CameraScrollSpeed, mainCamera.position.y, mainCamera.position.z);
			mainCamera.update();
		}

	}
	@Override
	public void resize(int width, int height){
		mainCamera = new OrthographicCamera();
		mainCamera.setToOrtho(false, width, height);
	}
	public InputProcessor getTouchInput(){

		InputProcessor TouchProcessor = new InputManager() {
			@Override
			public boolean touchDown (int x, int y, int pointer, int button) {
				// your touch down code here
				/*
				Gdx.app.log("TOUCH X", x + "");
				Vector3 playerVec3 =  mainCamera.unproject(new Vector3(player.getPosition().x, player.getPosition().y, 0.0f));
				shapeRenderer.begin();
				//shapeRenderer.line(playerVec3.x, playerVec3.y, x, y);
				shapeRenderer.rect(playerVec3.x, playerVec3.y, 300, 100);
				shapeRenderer.end();
				*/
				//this shit works
				return true; // return true to indicate the event was handled
			}

			@Override
			public boolean touchUp (int x, int y, int pointer, int button) {
				// your touch up code here
				Gdx.app.log("TouchX", x + "");
				if(stopInput ==false) {
					Vector3 touchVec3 =  mainCamera.unproject(new Vector3(x, y, 0.0f));
					Vector2 direcVec = new Vector2(player.getPosition().x - touchVec3.x, player.getPosition().y - touchVec3.y).nor();
					direcVec = direcVec.scl(-1.0f);
					Vector2 bulletLoc = new Vector2(player.getPosition().x + player.Width, player.getPosition().y + player.Height/2);
					bulletManager.addBullet(new Bullet(bulletLoc, direcVec, AI.bulletSprite));
				}
				stopInput = false;
				return true; // return true to indicate the event was handled
			}
		};
		return TouchProcessor;
	}

	public InputProcessor getSwipeInput(){

		InputProcessor SwipeProcessor = new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {

			@Override
			public void onUp() {
				stopInput = true;
				//player.setDirection(new Vector2(CameraScrollSpeed, 3).nor());
				player.setDirection(new Vector2(0, 1));
			}

			@Override
			public void onRight() {
				stopInput = true;
				player.setDirection(new Vector2(1, 0));

			}

			@Override
			public void onLeft() {
				stopInput = true;
				player.setDirection(new Vector2(-1, 0));

			}

			@Override
			public void onDown() {
				stopInput = true;
				//player.setDirection(new Vector2(CameraScrollSpeed, -3).nor());
				player.setDirection(new Vector2(0, -1));

			}


		});

		return SwipeProcessor;
	}


}
