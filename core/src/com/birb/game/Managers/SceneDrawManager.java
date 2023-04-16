package com.birb.game.Managers;

import com.badlogic.gdx.Gdx;
import com.birb.game.Utils.Coordinate;

import java.util.ArrayList;

public class SceneDrawManager {
    public static SceneDrawManager instance;
    public static SceneDrawManager getInstance() {
        if (instance == null) instance = new SceneDrawManager();
        return instance;
    }

    SpriteManager sm = SpriteManager.getInstance();
    AnimationManager am = AnimationManager.getInstance();
    Coordinate c;

    // Teselace xd
    private int[][] background = {
            {0, 1, 0, 0, 1, 0, 1, 1, 0, 2, 3, 1, 0, 0, 1, 0},
            {2, 0, 0, 1, 0, 1, 3, 0, 1, 0, 0, 2, 3, 0, 3, 1},
            {0, 1, 0, 1, 1, 0, 1, 1, 3, 0, 1, 0, 1, 0, 0, 2},
            {1, 1, 2, 0, 2, 1, 0, 0, 1, 1, 0, 1, 0, 3, 1, 0},
            {3, 3, 1, 3, 1, 2, 0, 2, 0, 1, 3, 0, 1, 1, 2, 1},
            {0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 2, 0, 0, 1, 0},
            {1, 0, 2, 1, 0, 1, 2, 0, 1, 2, 0, 1, 3, 2, 3, 2},
            {2, 3, 0, 1, 3, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1},
            {1, 2, 1, 2, 1, 3, 2, 0, 1, 3, 2, 1, 2, 1, 1, 3},
            {0, 1, 3, 0, 2, 1, 0, 0, 2, 1, 3, 0, 1, 2, 0, 1}
    };

    private int[][] pavement = {
            {0, 1, 0, 3, 0, 1, 2, 1, 0, 0, 1, 0, 0, 1, 2, 1},
            {1, 0, 1, 1, 0, 3, 1, 0, 0, 1, 0, 1, 3, 0, 1, 0},
            {2, 1, 3, 1, 0, 0, 1, 1, 3, 1, 0, 2, 1, 0, 1, 3}
    };

    private ArrayList<Coordinate> decorPos = new ArrayList<Coordinate>();

    public SceneDrawManager() {
        for (int i = 0; i < 20; i++) {
            decorPos.add(new Coordinate((float) (Math.random() * Gdx.graphics.getWidth()),
                    (float) (Math.random() * (Gdx.graphics.getHeight() / 2)), 2, 2, getRandomNumber(0, 2)));
        }
    }


    public void drawBackground() {
        int k = 0;
        int l = 0;
        for (int[] i : background) {
            for (int j : i) {
                switch (j) {
                    case 0:
                        sm.draw("grass",l * 128, k * 128, 2, 2);
                        break;
                    case 1:
                        sm.draw("grass2",l * 128, k * 128, 2, 2);
                        break;
                    case 2:
                        sm.draw("grass3",l * 128, k * 128, 2, 2);
                        break;
                    case 3:
                        sm.draw("grass4",l * 128, k * 128, 2, 2);
                        break;
                }
                l++;
            }
            l = 0;
            k++;
        }

        /* int vCount = (int)(Gdx.graphics.getWidth() / (32 * 2)) + 1;
        int hCount = (int) (Gdx.graphics.getHeight() / (32 * 2)) + 2;

        // System.out.println("W: " + Gdx.graphics.getWidth() + " H: " + Gdx.graphics.getHeight());
        // Grass
        for (int i = 0; i < vCount ; i++) {
            for (int j = 0; j < hCount; j++) {

                sm.draw("grass",i * 128, j * 128, 2, 2);
            }
        } */

        // Pavement

        for (int i = 0; i < 16 * 1.6; i++) {
            for (int j = 5; j < 8; j++) {
                sm.draw("pavement", i * 128, j * 128, 2, 2);
            }
        }

        // Bench
        sm.draw("bench", (Gdx.graphics.getWidth() / 2) - 256, Gdx.graphics.getHeight() - 128, 2, 2);

        // Decors
        for (Coordinate c : decorPos) {

            if (c.attr == 0) sm.draw("stone1", c.x, c.y, c.w, c.h);
            if (c.attr == 1) sm.draw(am.animate("stone2"), c.x, c.y, c.w, c.h);
        }
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
