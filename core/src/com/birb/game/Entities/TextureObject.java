package com.birb.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.birb.game.Enums.ZIndex;

public class TextureObject {
    private float x;
    private float y;
    private float w;
    private float h;
    private float rotation;
    private Texture texture;
    private ZIndex zindex;

    public TextureObject(float x, float y, float w, float h, float rotation, Texture texture, ZIndex zindex) {
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

    public Texture getTexture() {
        return texture;
    }

    public ZIndex getZindex() {
        return zindex;
    }

    public void setZindex(ZIndex zindex) {
        this.zindex = zindex;
    }
}
