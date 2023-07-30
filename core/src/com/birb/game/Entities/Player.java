package com.birb.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.birb.game.Enums.EntityType;
import com.birb.game.Enums.ZI;
import com.birb.game.Managers.AnimationManager;
import com.birb.game.Managers.SpriteManager;

public class Player extends Entity{

    private boolean flipped = false;
    private float xVelocity = 0;
    private float yVelocity = 0;
    private float acceleration = 0.1f;
    private boolean isAttacking = false;
    private int attackCooldown = 0;

    SpriteManager sm = SpriteManager.getInstance();
    AnimationManager am = AnimationManager.getInstance();
    public Player(float x, float y, float w, float h, float scale, float speed, int health, int damage) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.scale = scale;
        this.speed = speed;
        this.health = health;
        this.damage = damage;
    }

    public void update() {
        sm.draw("shadow", x+4f, y-10f, 1.5f, 1.5f, ZI.SHADOW);

        // Velocity parameters init
        boolean movedOnX = false;
        boolean movedOnY = false;
        float ww = Gdx.graphics.getWidth() * 0.93f;
        float wh = Gdx.graphics.getHeight() * 0.93f;


        // Movement
        if (Gdx.input.isKeyPressed(Input.Keys.D) && !(Gdx.input.isKeyPressed(Input.Keys.A))) {
            sm.draw(am.animateFast("pigeon_walking"), x, y, scale, scale, ZI.PLAYER);
            flipped = false;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A) && !(Gdx.input.isKeyPressed(Input.Keys.D))) {
            sm.draw(am.animateFast("pigeon_walking"), x + (64 * scale), y, -scale, scale, ZI.PLAYER);
            flipped = true;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            sm.draw(am.animateFast("pigeon_walking_up"), x, y, scale, scale, ZI.PLAYER);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            sm.draw(am.animateFast("pigeon_walking_down"), x, y, scale, scale, ZI.PLAYER);
        } else {
            sm.draw(am.animate("pigeon_still"), (flipped ? x + (64 * scale) : x), y, (flipped ? -scale : scale), scale, ZI.PLAYER);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W) && yVelocity < 1 && y < wh) {
            yVelocity+= acceleration;
            movedOnY = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) && yVelocity > -1 && y > 10) {
            yVelocity-= acceleration;
            movedOnY = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && !(Gdx.input.isKeyPressed(Input.Keys.D)) && xVelocity > -1 && x > 10) {
            xVelocity-= acceleration;
            movedOnX = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) && !(Gdx.input.isKeyPressed(Input.Keys.A)) && xVelocity < 1 && x < ww) {
            xVelocity+= acceleration;
            movedOnX = true;
        }

        if (!movedOnX) xVelocity -= Math.signum(xVelocity) * acceleration;
        if (!movedOnY) yVelocity -= Math.signum(yVelocity) * acceleration;

        if (Math.abs(xVelocity) < acceleration) xVelocity = 0;
        if (Math.abs(yVelocity) < acceleration) yVelocity = 0;

        x += xVelocity * speed;
        y += yVelocity * speed;

        // End movement

        // Attack check
        pushAttack();

        // Attack update

        attackCooldown -= (attackCooldown > 0 ? 1 : 0);
        isAttacking = attackCooldown > 0;




    }

    private void pushAttack() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            attackCooldown = 20;
        }
    }

    public void onCollide(Entity e) {

    }

    public boolean isAttacking() {
        return isAttacking;
    }

    public String toString() {
        return "Player: ATC: [" + attackCooldown + "], ?AT: [" + isAttacking() + "]";
    }

    public EntityType getType() {
        return EntityType.PLAYER;
    }
}
