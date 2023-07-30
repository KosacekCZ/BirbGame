package com.birb.game.Managers;

import com.badlogic.gdx.Gdx;
import com.birb.game.Enums.ZI;
import com.birb.game.Utils.Coordinate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SceneDrawManager {
    public static SceneDrawManager instance;
    public static SceneDrawManager getInstance() {
        if (instance == null) instance = new SceneDrawManager();
        return instance;
    }

    File decors;

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
        // Load decors file
        try {
            decors = new File("assets/Out/Decorations.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

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
                        sm.draw("grass",l * 128, k * 128, 2, 2, ZI.BACKGROUND);
                        break;
                    case 1:
                        sm.draw("grass2",l * 128, k * 128, 2, 2, ZI.BACKGROUND);
                        break;
                    case 2:
                        sm.draw("grass3",l * 128, k * 128, 2, 2, ZI.BACKGROUND);
                        break;
                    case 3:
                        sm.draw("grass4",l * 128, k * 128, 2, 2, ZI.BACKGROUND);
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

        for (int i = 0; i < 16; i++) {
            for (int j = 5; j < 8; j++) {
                switch (pavement[j - 5][i]) {
                    case 0:
                        sm.draw("pavement", i * 128, j * 128, 2, 2, ZI.BACKGROUND_2);
                        break;

                    case 1:
                        sm.draw("pavement2", i * 128, j * 128, 2, 2, ZI.BACKGROUND_2);
                        break;

                    case 2:
                        sm.draw("pavement3", i * 128, j * 128, 2, 2, ZI.BACKGROUND_2);
                        break;
                }
            }
        }

        // Leafs n shit
        try {
            Scanner sc = new Scanner(decors);
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(",");
                if (line[2].trim().equals("flower_1") || line[2].trim().equals("flower_2")) {
                    sm.draw(am.animate(line[2].trim()), Float.parseFloat(line[0]) - 50f, Gdx.graphics.getHeight() - Float.parseFloat(line[1]) - 50f, 2, 2, ZI.DECOR);
                } else {
                    sm.draw(line[2].trim(), Float.parseFloat(line[0]) - 50f, Gdx.graphics.getHeight() - Float.parseFloat(line[1]) - 50f, 2, 2, ZI.DECOR);
                }
                // System.out.println("drawn" + line[2] + " at " + line[0] + ", " + line[1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Bench
        sm.draw("bench", (Gdx.graphics.getWidth() / 2) - 256, Gdx.graphics.getHeight() - 128, 6, 2, ZI.DECOR);

        // Decors
        for (Coordinate c : decorPos) {

            if (c.attr == 0) sm.draw("stone1", c.x, c.y, c.w, c.h, ZI.DECOR);
            if (c.attr == 1) sm.draw(am.animate("stone2"), c.x, c.y, c.w, c.h, 0, ZI.DECOR);
        }


    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
