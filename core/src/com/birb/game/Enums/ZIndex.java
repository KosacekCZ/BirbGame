package com.birb.game.Enums;

public enum ZIndex {
    BACKGROUND(0), DECOR(1), DROPKABLE(2), SHADOW(3), ENTITIES(4);
    private int index;

    ZIndex(int index) {
        this.index = index;
    }
}
