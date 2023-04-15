package com.birb.game.Utils;

public class Coordinate {
    public float x;
    public float y;
    public float w;
    public float h;
    public float dir;
    public int attr;

    public Coordinate(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public Coordinate(float x, float y, float w, float h, int attr) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.attr = attr;
    }

    public Coordinate(float x, float y, float w, float h, float dir) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.dir = (float) (dir * Math.PI);
    }

    @Override
    public String toString() {
        return "X: "+x+" Y: "+y+" W: "+w+" H: "+h+" Attr: "+attr;
    }

}
