package com.birb.game.Managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationManager {
    public static AnimationManager instance;
    public static AnimationManager getInstance() {
        if (instance == null) instance = new AnimationManager();
        return instance;
    }

    TickManager tm = TickManager.getInstance();
    SpriteManager sm = SpriteManager.getInstance();

    public TextureRegion animate(String textureName) {
        Texture tx = sm.textures.get(textureName);
        int w = tx.getWidth();
        int h = tx.getHeight();
        if (tm.getT() > 0 && tm.getT() <= 10) {
            return new TextureRegion(tx, 0, 0, (w / 6), h);
        } else if (tm.getT() > 10 && tm.getT() <= 20) {
            return new TextureRegion(tx, (w / 6), 0, (w / 6), h);
        } else if (tm.getT() > 20 && tm.getT() <= 30) {
            return new TextureRegion(tx, (w / 6) * 2,  0, (w / 6), h);
        } else if (tm.getT() > 30 && tm.getT() <= 40) {
            return new TextureRegion(tx, (w / 6) * 3, 0, (w / 6), h);
        } else if (tm.getT() > 40 && tm.getT() <= 50) {
            return new TextureRegion(tx, (w / 6) * 4, 0, (w / 6), h);
        } else {
            return new TextureRegion(tx, (w / 6) * 5, 0, (w / 6), h);
        }
    }

    public TextureRegion animateFast(String textureName) {
        Texture tx = sm.textures.get(textureName);
        int w = tx.getWidth();
        int h = tx.getHeight();
        if (tm.getFastT() > 0 && tm.getFastT() <= 10) {
            return new TextureRegion(tx, 0, 0, (w / 6), h);
        } else if (tm.getFastT() > 10 && tm.getFastT() <= 20) {
            return new TextureRegion(tx, (w / 6), 0, (w / 6), h);
        } else if (tm.getFastT() > 20 && tm.getFastT() <= 30) {
            return new TextureRegion(tx, (w / 6) * 2,  0, (w / 6), h);
        } else if (tm.getFastT() > 30 && tm.getFastT() <= 40) {
            return new TextureRegion(tx, (w / 6) * 3, 0, (w / 6), h);
        } else if (tm.getFastT() > 40 && tm.getFastT() <= 50) {
            return new TextureRegion(tx, (w / 6) * 4, 0, (w / 6), h);
        } else {
            return new TextureRegion(tx, (w / 6) * 5, 0, (w / 6), h);
        }
    }
}
