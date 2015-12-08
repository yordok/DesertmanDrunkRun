package com.mygdx.desertcommander;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;

import org.w3c.dom.Text;

/**
 * Created by Jordan on 12/7/2015.
 */
public class InGameUI {
    private Texture Cross, WaterJug;
    private TextureRegion BlueBar;
    private int ScrnHeight, ScrnWidth;
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    BitmapFont Font;

     // don't forget to dispose to avoid memory leaks!
    public InGameUI(Texture cross, Texture bluebar, Texture waterjug){
        Cross = cross;
        WaterJug = waterjug;
        BlueBar = new TextureRegion(bluebar);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("pixelmix.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        parameter.color = Color.BLACK;
        Font = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose();

        ScrnHeight = Gdx.graphics.getHeight();
        ScrnWidth = Gdx.graphics.getWidth();
    }

    public void draw(SpriteBatch spriteBatch, int playerHealth, int wordlPosX, float waterLevel, float Distance){
        drawHealthBar(spriteBatch, playerHealth, wordlPosX);
        drawWaterBar(spriteBatch, wordlPosX, waterLevel);
        drawDistanceMeter(spriteBatch, wordlPosX, Distance);
    }

    public void drawHealthBar(SpriteBatch spriteBatch, int playerHealth, int worldPosX){
        for(int i = 0; i < playerHealth; i++){
            spriteBatch.draw(Cross, worldPosX + 100 + (150 * i),ScrnHeight - 150, 100,100);
        }
    }

    public void drawWaterBar(SpriteBatch spriteBatch, int worldPosX, float WaterLevel){
        spriteBatch.draw(WaterJug, (worldPosX + ScrnWidth)-620 ,ScrnHeight - 150, 100 ,100);
        spriteBatch.draw(BlueBar, (worldPosX + ScrnWidth)-500 ,ScrnHeight - 150,0 ,0, 10,100, WaterLevel, 1.0f, 0.0f);

    }

    public void drawDistanceMeter(SpriteBatch spriteBatch, int worldPosX, float Distance){

        Font.draw(spriteBatch, "DISTANCE: " + Distance, worldPosX + 50,80);
    }
}
