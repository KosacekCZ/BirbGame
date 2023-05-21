package com.birb.game.Utils;

import com.birb.game.Enums.Action;

public class Coordinate {
    public float x;
    public float y;
    public float w;
    public float h;
    public float dir;
    public int attr;
    public Action action;
    public String data;

    public Coordinate(float x, float y, String data) {
        this.x = x;
        this.y = y;
        this.data = data;
    }

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
        dir = (float) (dir * Math.PI);
    }

    public Coordinate (float x, float y, float w, float h, float dir, Action a) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        dir = (float) (dir * Math.PI);
        action = a;
    }

    @Override
    public String toString() {
        return "X: "+x+" Y: "+y+" W: "+w+" H: "+h+" Attr: "+attr;
    }

}
