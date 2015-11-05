package com.mygdx.desertcommander;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import sun.awt.X11.Screen;

public class MainGame extends ApplicationAdapter {
	SpriteBatch batch;
	float CameraScrollSpeed;
	boolean CameraIsScrolling;
	Player player;
	Cactus c;
	OrthographicCamera mainCamera;

	@Override
	public void create () {
		batch = new SpriteBatch();
		AssetInitializer AI = new AssetInitializer();
		mainCamera = new OrthographicCamera();
		mainCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		CameraIsScrolling = false;
		CameraScrollSpeed = 1.0f;
		c = new Cactus(new Vector2(1000, 100), AI.obstacleSprite);
		player = new Player(20.0f,0,AI.playerSprite);
		this.getInput();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0.8f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.update();
		batch.setProjectionMatrix(mainCamera.combined);
		batch.begin();
		player.draw(batch, mainCamera);
		c.draw(batch);
		batch.end();
	}

	public void update(){
		this.handleCamera();
		player.move();

	}
	//an attempt to make the camera move
	public void handleCamera(){
		//this needs to be fixed, needs to be 300 in the camera space, not the world space

		CameraIsScrolling = true;

		if(CameraIsScrolling){
			player.setSpeed(4.0f);
			mainCamera.position.set(mainCamera.position.x + CameraScrollSpeed, mainCamera.position.y, mainCamera.position.z);
			mainCamera.update();
		}

	}
	@Override
	public void resize(int width, int height){
		mainCamera = new OrthographicCamera();
		mainCamera.setToOrtho(false, width, height);
	}

	public void getInput(){
		if(Gdx.input.isTouched()){
			//code to fire bullet
		}

		Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {

			@Override
			public void onUp() {
				player.setDirection(new Vector2(CameraScrollSpeed, 3).nor());
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
				player.setDirection(new Vector2(CameraScrollSpeed, -3).nor());

			}
		}));
	}
}
