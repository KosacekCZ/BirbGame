package com.birb.game.Enums;

public enum ZIndex {
    BACKGROUND(0), SHADOW(1), DECOR(2), DROPABLE(3), ENTITIES(4);
    private int index;

    ZIndex(int index) {
        this.index = index;
    }
}
