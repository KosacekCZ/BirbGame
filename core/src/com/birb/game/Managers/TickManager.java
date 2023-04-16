package com.birb.game.Managers;

import java.util.ArrayList;

public class TickManager {
    public static TickManager instance;
    public static TickManager getInstance() {
        if (instance == null) instance = new TickManager();
        return instance;
    }

    private int t = 0;
    private int t2 = 0;
    private double t3 = 0;

    public void update() {
        t = (++t % 60 == 0 ? 0 : t);
        t2 = ((t2 += 2) % 60 == 0 ? 0 : t2);
        t3 = ((t3 += 0.03) > 6 ? 0 : t3);

    }

    public int getT() {
        return t;
    }
    public int getFastT() {
        return t2;
    }
    public double getSlowT() {return t3;}
}
