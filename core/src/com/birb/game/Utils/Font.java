package com.birb.game.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.birb.game.Managers.SpriteManager;

import java.util.HashMap;

public class Font {

    static SpriteManager sm = SpriteManager.getInstance();

    private static HashMap<Integer, BitmapFont> fontStash = new HashMap<>();

    public Font() {

    }

    public static void draw(String text, int size, float x, float y) {
        if (fontStash.get(size) == null) fontStash.put(size, generateFont(size));
        fontStash.get(size).draw(sm.batch, text, x, y);

    }



    private static BitmapFont generateFont(int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("assets/Font/slkscrb.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.borderWidth = 0;
        parameter.color = Color.WHITE;
        parameter.shadowOffsetX = 4;
        parameter.shadowOffsetY = 4;
        parameter.shadowColor = new Color(0.2f, 0.2f, 0.2f, 0.85f);
        BitmapFont customFont = generator.generateFont(parameter); // font size 24 pixels
        generator.dispose();
        return customFont;
    }
}
