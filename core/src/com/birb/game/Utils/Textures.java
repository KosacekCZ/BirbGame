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
        sm.loadSprite("Scene/tiles.png", "pavement");
        sm.loadSprite("Scene/bench.png", "bench");
        sm.loadSprite("Scene/stone1.png", "stone1");
        sm.loadSprite("Scene/stone2.png", "stone2");

        // Items
        sm.loadSprite("Misc/breadcrumb_1.png", "breadcrumb1");

        // Misc
        sm.loadSprite("Misc/shadow.png", "shadow");
    }
}
