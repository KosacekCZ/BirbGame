package com.birb.game.Managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.birb.game.Entities.TextureObject;
import com.birb.game.Enums.ZI;

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
    HashMap<ZI, ArrayList<TextureObject>> renderLayers = new HashMap<>();
    HashMap<String, Texture> textures = new HashMap<>();

    public SpriteManager(){
        batch = new SpriteBatch();
        for (ZI i: ZI.values()) {
            renderLayers.put(i, new ArrayList<>());
        }
    }

    public void loadSprite(String path, String name) {
        textures.put(name, new Texture(path));

    }

    /*public void issueDraw () {
        for (ZIndex i: ZIndex.values()) {
            renderLayers.get(i).forEach(textureObject -> batch.draw(textureObject.getTexture(),
                                                                    textureObject.getX(),
                                                                    textureObject.getY(),
                                                                    textureObject.getW() * 64f,
                                                                    textureObject.getH() * 64f));
            System.out.println(renderLayers.get(i).size());
        }
    }*/

    public void issueDraw() {
        renderLayers
                .keySet()
                .stream()
                .sorted()
                .forEach(ZI -> renderLayers.get(ZI)
                        .forEach(textureObject -> batch.draw(textureObject
                                        .getTexture(),
                                        textureObject.getX(),
                                        textureObject.getY(),
                                        textureObject.getW() * 64f,
                                        textureObject.getH() * 64f)));
        for (ZI i: ZI.values()) {
            //System.out.println( i + " " + renderLayers.get(i).size());
        }

    }

    public void draw(String name, float x, float y) {
        Texture t = textures.get(name);

        //batch.draw(t, x, y, t.getWidth() * 2, t.getHeight() * 2);
    }

    public void draw(String name, float x, float y, float w, float h, ZI i) {
        Texture t = textures.get(name);
        renderLayers.get(i).add(new TextureObject(new TextureRegion(t), x, y, w, h, 0, i));
    }

    public void draw(TextureRegion texture, float x, float y, float w, float h, float rotation, ZI i) {
        renderLayers.get(i).add(new TextureObject(texture, x, y, w, h, rotation, i));
    }

    public void draw (TextureObject tb) {
        renderLayers.get(tb.getZindex()).add(tb);
        }



    public void draw(TextureRegion texture, float x, float y, float w, float h, ZI i) {
        renderLayers.get(i).add(new TextureObject(texture, x, y, w, h, 0, i));
    }


    public void batchBegin() {
        batch.begin();

    }

    public void batchEnd() {
        batch.end();
        // turbosmrt
        renderLayers.keySet().forEach(ZI -> renderLayers.get(ZI).clear());
    }

    public void dispose() {
        batch.dispose();
        for (Texture t: textures.values()) {
            t.dispose();
        }

    }

}
