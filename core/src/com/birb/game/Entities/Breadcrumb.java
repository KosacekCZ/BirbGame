package com.birb.game.Entities;

import com.birb.game.Enums.EntityType;
import com.birb.game.Managers.SpriteManager;
import com.birb.game.Managers.TickManager;
import com.birb.game.Utils.Coordinate;

public class Breadcrumb extends Entity {

    SpriteManager sm = SpriteManager.getInstance();
    TickManager tm = TickManager.getInstance();

    private final int lifetime;
    private final int bounces;
    private final float spawnY;
    private final int curveHeight;
    private final double curveSpread;
    private final boolean flipped;

    public Breadcrumb(float x, float y, float w, float h, float scale, int lifetime, int bounces, int curveHeight, double curveSpread, boolean flipped) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.scale = scale;
        this.lifetime = lifetime;
        this.bounces = bounces;
        this.spawnY = y;
        this.curveHeight = curveHeight;
        this.curveSpread = curveSpread;;
        this.flipped = flipped;

    }

    float t = 2.3f;
    int bounced = 0;
    public void update() {
        // Shadow

        sm.draw("shadow", x , spawnY + 10f, 1f, 1f);

        // Bounce function

        if (t <= lifetime && bounced < bounces) {
            this.y = this.spawnY + (float)(Math.pow(Math.E, -0.3 * (t)) * Math.abs(this.curveHeight * Math.cos(this.curveSpread * t))) * 100;
            if (y < this.spawnY + 10f) bounced++;
            this.x += 0.5*t;
            t+= 0.13f;
        }
        this.scale = (2 + (float)Math.abs((Math.sin(tm.getSlowT()))) / 4);


        // Draw texture
        sm.draw("breadcrumb1", this.x, this.y, this.scale, this.scale);
    }

    public void onCollide(Entity e) {
        destroy();
    }

    public EntityType getType() {
        return EntityType.BREADCRUMB;
    }

    public Coordinate getPos() {
        return new Coordinate(x, y, w, h);
    }
}
