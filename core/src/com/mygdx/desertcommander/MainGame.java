package com.mygdx.desertcommander;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;


public class MainGame extends ApplicationAdapter{
	SpriteBatch batch;
	boolean GameStarted;
	MainLevel MAINLEVEL;
	MainMenuScreen MenuScreen;
	ShapeRenderer shapeRenderer;

	@Override
	public void create () {
		this.menuInput();
		GameStarted = false;
		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		MenuScreen = new MainMenuScreen();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1.0f, 0.843137f, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(GameStarted){
			batch.begin();
			MAINLEVEL.draw(batch);
			batch.end();
			/*
			//below is used for rendering the hitboxes on all objects with hitboxes
			shapeRenderer.setColor(Color.BLACK);
			shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
			shapeRenderer.rect(MAINLEVEL.player.getPosition().x, MAINLEVEL.player.getPosition().y, MAINLEVEL.player.Width, MAINLEVEL.player.Height);
			shapeRenderer.rect(MAINLEVEL.player.HitBox.x, MAINLEVEL.player.HitBox.y, MAINLEVEL.player.HitBox.width, MAINLEVEL.player.HitBox.height);
			ArrayList<LevelChunk> Lchunklist = MAINLEVEL.lvlChunkManager.MasterChunkList;

			for(int i =0; i < Lchunklist.size(); i++){
				LevelChunk LC = Lchunklist.get(i);
				for(int j =0; j < LC.ObstacleMasterList.size(); j++ ){
					Obstacle obs = LC.ObstacleMasterList.get(j);
					shapeRenderer.rect(obs.getHitBox().x, obs.getHitBox().y, obs.getHitBox().width, obs.getHitBox().height);
				}
			}
			shapeRenderer.end();
			*/
		}
		else{
			batch.begin();

			MenuScreen.draw(batch);
			batch.end();
		}

	}

	@Override
	public void resume() {
		if(GameStarted){
			MAINLEVEL.playing = true;
		}
	}

	@Override
	public void pause() {
		if(GameStarted) {
			MAINLEVEL.playing = false;
		}
	}

	public void menuInput(){
		Gdx.input.setInputProcessor(new InputAdapter() {

			@Override
			public boolean touchUp (int x, int y, int pointer, int button) {
				if(GameStarted==false){
					int actualY = Gdx.graphics.getHeight() - y;

					if(MenuScreen.startButton.HitBox.contains(new Vector2(x,actualY))){
						Gdx.app.log("BUTTON PRESSED","");
						MAINLEVEL = new MainLevel();
						MAINLEVEL.generateGame();
						GameStarted = true;
						MAINLEVEL.playing = true;

					}
				}
				return true;
			}
		});
	}




}
