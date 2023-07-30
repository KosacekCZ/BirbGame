package com.birb.game.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class PlacementHelper {
    public static PlacementHelper instance;
    public static PlacementHelper getInstance() {
        if (instance == null) instance = new PlacementHelper();
        return instance;
    }

    private boolean placementModeOn;
    private ArrayList<Coordinate> placements = new ArrayList<>();


    public PlacementHelper() {

    }

    public void update () {
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) placementModeOn = !placementModeOn;

        if (placementModeOn) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
                placements.add(new Coordinate(Gdx.input.getX(), Gdx.input.getY(), "flower_1"));
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
                placements.add(new Coordinate(Gdx.input.getX(), Gdx.input.getY(), "flower_2"));
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
                placements.add(new Coordinate(Gdx.input.getX(), Gdx.input.getY(), "leaf1_green"));
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
                placements.add(new Coordinate(Gdx.input.getX(), Gdx.input.getY(), "leaf1_yellow"));
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)) {
                placements.add(new Coordinate(Gdx.input.getX(), Gdx.input.getY(), "leaf1_orange"));
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)) {
                placements.add(new Coordinate(Gdx.input.getX(), Gdx.input.getY(), "leaf2_green"));
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_7)) {
                placements.add(new Coordinate(Gdx.input.getX(), Gdx.input.getY(), "leaf2_yellow"));
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_8)) {
                placements.add(new Coordinate(Gdx.input.getX(), Gdx.input.getY(), "leaf2_orange"));
            }


        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.INSERT)) writeToFile();
    }

    public boolean isPlacementModeOn() {
        return placementModeOn;
    }

    public String getPlacedItemsOverview() {
        return String.valueOf(placements.size());
    }

    private void writeToFile() {
        try {
            File file = new File("assets/Out/Decorations.txt");
            FileWriter fw = new FileWriter("assets/Out/Decorations.txt");
            //fw.append("{ \n");
            for (Coordinate c : placements) {
                fw.append(c.x + ", " + c.y + ", " + c.data + "\n");
            }
            //fw.append("};");
            fw.close();
        } catch (Exception e) {
            System.out.println("!" + e.getMessage());
        }


    }
 }
