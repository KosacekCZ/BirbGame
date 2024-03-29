package com.birb.game.Entities;

import com.birb.game.Enums.EntityType;
import com.birb.game.Enums.ZI;
import com.birb.game.Managers.SpriteManager;
import com.birb.game.Managers.TickManager;
import com.birb.game.Utils.Coordinate;
import com.birb.game.Utils.Font;

public class Breadcrumb extends Entity {

    SpriteManager sm = SpriteManager.getInstance();
    TickManager tm = TickManager.getInstance();

    private final int lifetime;
    private final int bounces;
    private final float spawnY;
    private final int curveHeight;
    private final double curveSpread;
    private final boolean flipped;
    private final float fallspread;
    private int bites;
    private int tempCtdSwitcher = 0;

    public Breadcrumb(float x, float y, float w, float h, float scale, int lifetime, int bounces, int curveHeight, double curveSpread, boolean flipped, float fallSpread) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.scale = scale;
        this.lifetime = lifetime; // t of particle lifetime
        this.bounces = bounces; // # of bounces
        this.spawnY = y; // Spawn origin
        this.curveHeight = curveHeight; // smrt
        this.curveSpread = curveSpread; // smrt
        this.flipped = flipped; // direction of fall
        this.fallspread = fallSpread; // 0.1 ~ 0.3
        this.bites = 5;

    }

    float t = 2.3f;
    int bounced = 0;
    public void update() {
        // Shadow

        sm.draw("shadow", x - 10f , spawnY - 5f, 1f, 1f, ZI.SHADOW);

        // Bounce function

        if (t <= lifetime && bounced < bounces) {
            this.y = this.spawnY + (float)(Math.pow(Math.E, -0.3 * (t)) * Math.abs(this.curveHeight * Math.cos(this.curveSpread * t))) * 100;
            if (y < this.spawnY + 10f) bounced++;
            this.x += (flipped ? 0.5*t : -0.5*t);
            t+= 0.13f;
        } else {
            this.y += Math.sin(tm.getGeometricT() * Math.PI) * 0.513;
        }

        if (tm.getT() > 55) {
            bites -= tempCtdSwitcher;
        }


        // Draw texture
        sm.draw("breadcrumb1", this.x, this.y, this.scale, this.scale, ZI.DROPABLE);
        Font.draw(String.valueOf(bites), 10, this.x, this.y + 10);
    }

    public void onCollide(Entity e) {
        if (e.getType() != EntityType.BREADCRUMB) {
            tempCtdSwitcher = 1;
            if (bites < 0) {
                destroy();
            }
        }
    }

    public EntityType getType() {
        return EntityType.BREADCRUMB;
    }

    public Coordinate getPos() {
        return new Coordinate(x, y, w, h);
    }
}
