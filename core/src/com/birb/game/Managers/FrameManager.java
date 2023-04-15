package com.birb.game.Managers;

public class FrameManager {

    private int t = 1;
    public void update() {
        if (t <= 60 && t > 0) {
            t++;
        } else {
            t = 0;
        }
    }

    private int getFrameTick() {
        return t;
    }
}
