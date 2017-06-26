package com.procrastinators.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by jazeem on 18/06/17.
 */

public class AssetLoader {
    public static Texture texture, font;
    public static TextureRegion textureRegion;
    public static TextureRegion letters[] = new TextureRegion[48];
    public static void load(){
        texture = new Texture(Gdx.files.internal("restart.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        textureRegion = new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());

        font = new Texture(Gdx.files.internal("font_texture.png"));
        for (int i=0;i<3;i++)
            for (int j=0;j<16;j++){
                int index = i*16+j;
                letters[index] = new TextureRegion(font, Constants.LETTER_WIDTH*j, Constants.LETTER_HEIGHT*i, Constants.LETTER_WIDTH, Constants.LETTER_HEIGHT);
                letters[index].flip(false, true);
            }
    }
    public static void dispose(){

    }
}
