package com.mygdx.desertcommander;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class MainGame extends ApplicationAdapter{
	SpriteBatch batch;
	boolean GameStarted;
	MainLevel MAINLEVEL;
	MainMenuScreen MenuScreen;


	@Override
	public void create () {
		this.menuInput();
		GameStarted = false;
		batch = new SpriteBatch();
		MenuScreen = new MainMenuScreen();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1.0f, 0.843137f, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if(GameStarted){
			MAINLEVEL.draw(batch);
		}
		else{
			MenuScreen.draw(batch);
		}
		batch.end();
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
