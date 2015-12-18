package com.mygdx.desertcommander;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


public class MainGame extends ApplicationAdapter{
	SpriteBatch batch;
	boolean GameStarted;
	boolean showingInstructions;
	MainLevel MAINLEVEL;
	MainMenuScreen MenuScreen;
	ShapeRenderer shapeRenderer;
	AssetInitializer AI;
	OrthographicCamera MainCam;
	Vector3 InitalCamerPos;
	int instructionIndex;
	Texture instructScreens[];
	int ScreenWidth, ScreenHeight;

	@Override
	public void create () {
		this.menuInput();
		ScreenHeight = Gdx.graphics.getHeight();
		ScreenWidth = Gdx.graphics.getWidth();
		instructionIndex = 0;
		MainCam = new OrthographicCamera();
		MainCam.setToOrtho(false, ScreenWidth, ScreenHeight);
		GameStarted = false;
		showingInstructions = false;
		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		AI = new AssetInitializer(true);
		InitalCamerPos = MainCam.position;
		Gdx.app.log("INITIAL CAM VALUES", MainCam.position.toString());
		MenuScreen = new MainMenuScreen();
		instructScreens = AI.getInstructionsScreens();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1.0f, 0.843137f, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.setProjectionMatrix(MainCam.combined);
		if(GameStarted){
			MAINLEVEL.draw(batch);
			//if the level has ended
			if(MAINLEVEL.LEVELENDED == true){
				Gdx.app.log("FINAL POSITION CAM", MainCam.position.toString());

				GameStarted = false;

				MainCam = new OrthographicCamera();
				MainCam.setToOrtho(false, ScreenWidth, ScreenHeight);
				menuInput();

			}
		}
		else{
			if(showingInstructions){
				drawInstructions(batch);
			}
			else{
				MenuScreen.draw(batch);
			}


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
				//if the game has not started
				if(GameStarted==false){
					int actualY = ScreenHeight - y;
					//if the instruction screen is not currently in use
					if(showingInstructions==false) {
						if (MenuScreen.startButton.HitBox.contains(new Vector2(x, actualY))) {
							MAINLEVEL = new MainLevel();
							MAINLEVEL.generateGame(AI, MainCam);
							GameStarted = true;
							MAINLEVEL.playing = true;

						}
						if (MenuScreen.instructButton.HitBox.contains(new Vector2(x, actualY))) {
							Gdx.app.log("BUTTON PRESSED", "instructions");
							showingInstructions = true;

						}
					}
					//if the using the instruction screen
					else{
						//buttons on the right and left of the screen to make the instructions go forward and back
						if(x > ScreenWidth - 500){
							if(instructionIndex < instructScreens.length){
								instructionIndex++;
							}
							if(instructionIndex >= instructScreens.length){
								instructionIndex = 0;
								showingInstructions = false;
							}

						}
						if(x< 500){
							if(instructionIndex > 0){
								instructionIndex--;
							}
							if(instructionIndex <= 0){
								instructionIndex = 0;
								showingInstructions = false;
							}
						}

					}

				}
				return true;
			}
		});
	}
	//draw the instruction screen
	public void drawInstructions(SpriteBatch spriteBatch){
		spriteBatch.draw(instructScreens[instructionIndex],0,0,ScreenWidth, ScreenHeight);
		spriteBatch.draw(AI.rightArrow,ScreenWidth - 350, 100, 250,250);
		spriteBatch.draw(AI.leftArrow,100, 100, 250,250);


	}




}
