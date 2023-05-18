package com.birb.game.Enums;

public enum ZI {
    BACKGROUND(0),
    BACKGROUND_2(2),
    SHADOW(4),
    DECOR(6),
    DROPABLE(8),
    ENTITIES(10),
    PLAYER(11),
    UPPER(12),
    UPPER_2(14);

    private int index;

    ZI(int index) {
        this.index = index;
    }
}
