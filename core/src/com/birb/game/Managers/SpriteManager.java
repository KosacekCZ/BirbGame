package com.birb.game.Managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.birb.game.Entities.TextureObject;
import com.birb.game.Enums.ZIndex;

import java.util.ArrayList;
import java.util.HashMap;

public class SpriteManager {

    private static SpriteManager instance;

    public static SpriteManager getInstance() {
        if (instance == null) {
            instance = new SpriteManager();
        }

        return instance;
    }

    SpriteBatch batch;
    HashMap<ZIndex, ArrayList<TextureObject>> renderLayers = new HashMap<>();
    HashMap<String, Texture> textures = new HashMap<>();

    public SpriteManager(){
        batch = new SpriteBatch();
    }

    public void loadSprite(String path, String name) {
        textures.put(name, new Texture(path));

    }

    public void issueDraw() {
        renderLayers
                .keySet()
                .stream()
                .sorted()
                .map((z) -> renderLayers.get(z))
                .forEach((x) -> x.forEach(this::draw));
    }

    public void draw(String name, float x, float y) {
        Texture t = textures.get(name);

        batch.draw(t, x, y, t.getWidth() * 2, t.getHeight() * 2);
    }

    public void draw(String name, float x, float y, float w, float h) {
        Texture t = textures.get(name);
        batch.draw(t, x, y, t.getWidth()*2*w, t.getHeight()*2*h);
    }

    public void draw(TextureRegion textureRegion, float x, float y, float w, float h, float rotation) {
        float originX = textureRegion.getRegionWidth() / 2f;
        float originY = textureRegion.getRegionHeight() / 2f;
        float scaleX = 1f;
        float scaleY = 1f;
        batch.draw(textureRegion, x, y, originX, originY, w, h, scaleX, scaleY, rotation);
    }

    private void draw (TextureObject tb) {
        batch.draw((new TextureRegion(tb.getTexture())), tb.getX(), tb.getY(), (tb.getTexture().getWidth() / 2f), (tb.getTexture().getHeight() / 2f), tb.getW(), tb.getH(), 1f, 1f, tb.getRotation());
    }



    public void draw(TextureRegion textureRegion, float x, float y, float w, float h) {
        batch.draw(textureRegion, x, y, textureRegion.getRegionWidth()*2*w, textureRegion.getRegionHeight()*2*h);
    }


    public void batchBegin() {
        batch.begin();

    }

    public void batchEnd() {
        batch.end();

    }

    public void dispose() {
        batch.dispose();
        for (Texture t: textures.values()) {
            t.dispose();
        }
    }

}
