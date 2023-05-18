package com.birb.game.Entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.birb.game.Enums.ZI;

public class TextureObject {
    private float x;
    private float y;
    private float w;
    private float h;
    private float rotation;
    private TextureRegion texture;
    private ZI zindex;

    public TextureObject(TextureRegion texture, float x, float y, float w, float h, float rotation, ZI zindex) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.rotation = rotation;
        this.texture = texture;
        this.zindex = zindex;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getW() {
        return w;
    }

    public float getH() {
        return h;
    }

    public float getRotation() {
        return rotation;
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public ZI getZindex() {
        return zindex;
    }

    public void setZindex(ZI zindex) {
        this.zindex = zindex;
    }

    @Override
    public String toString() {
        return texture.toString();
    }
}
