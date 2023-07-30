package com.birb.game.Entities;

import com.birb.game.Enums.EntityType;

public abstract class Entity {
    protected float x;
    protected float y;
    protected float w;
    protected float h;
    protected float scale;
    protected float speed;
    protected boolean isDestroyed = false;
    protected int health;
    protected int damage;
    protected boolean isAttacking = false;

    public abstract void update();

    public abstract void onCollide(Entity e);

    public void destroy() {
        isDestroyed = true;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public abstract EntityType getType();

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getW() {
        return w;
    }

    public float getH() {
        return h;
    }

    public boolean isAttacking() {
        return isAttacking;
    }
}
