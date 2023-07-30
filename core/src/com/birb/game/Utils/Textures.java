package com.birb.game.Utils;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.birb.game.Managers.SpriteManager;

public class Textures {
    public static Textures instance;
    public static Textures getInstance() {
        if (instance == null) instance = new Textures();
        return instance;
    }

    SpriteManager sm = SpriteManager.getInstance();

    public void loadTextures() {
        // Player
        sm.loadSprite("Player/pigeon_still.png", "pigeon_still");
        sm.loadSprite("Player/pigeon_walking.png", "pigeon_walking");
        sm.loadSprite("Player/pigeon_walking_up.png", "pigeon_walking_up");
        sm.loadSprite("Player/pigeon_walking_down.png", "pigeon_walking_down");

        // Crow
        sm.loadSprite("Entities/crow_idle.png", "crow_idle");
        sm.loadSprite("Entities/crow_chirp.png", "crow_chirp");
        sm.loadSprite("Entities/crow_walking.png", "crow_walking");
        sm.loadSprite("Entities/crow_walking_down.png", "crow_walking_down");

        // Level details
        sm.loadSprite("Scene/grass.png", "grass");
        sm.loadSprite("Scene/grass2.png", "grass2");
        sm.loadSprite("Scene/grass3.png", "grass3");
        sm.loadSprite("Scene/grass4.png", "grass4");
        sm.loadSprite("Scene/tiles.png", "pavement");
        sm.loadSprite("Scene/tiles2.png", "pavement2");
        sm.loadSprite("Scene/tiles3.png", "pavement3");
        sm.loadSprite("Scene/bench.png", "bench");
        sm.loadSprite("Scene/stone1.png", "stone1");
        sm.loadSprite("Scene/stone2.png", "stone2");
        sm.loadSprite("Scene/flower_1.png", "flower_1");
        sm.loadSprite("Scene/flower_2.png", "flower_2");
        sm.loadSprite("Scene/leaf1_green.png", "leaf1_green");
        sm.loadSprite("Scene/leaf1_orange.png", "leaf1_orange");
        sm.loadSprite("Scene/leaf1_yellow.png", "leaf1_yellow");
        sm.loadSprite("Scene/leaf2_green.png", "leaf2_green");
        sm.loadSprite("Scene/leaf2_orange.png", "leaf2_orange");
        sm.loadSprite("Scene/leaf2_yellow.png", "leaf2_yellow");

        // Items
        sm.loadSprite("Misc/breadcrumb_1.png", "breadcrumb1");

        // Misc
        sm.loadSprite("Misc/shadow.png", "shadow");
        sm.loadSprite("Misc/debugorb.png", "deb");
    }
}
