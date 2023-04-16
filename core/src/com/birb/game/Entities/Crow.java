package com.birb.game.Entities;

import com.birb.game.Enums.Action;
import com.birb.game.Enums.EntityType;
import com.birb.game.Managers.AnimationManager;
import com.birb.game.Managers.EntityManager;
import com.birb.game.Managers.SpriteManager;
import com.birb.game.Managers.TickManager;
import com.birb.game.Mechanics.Ai;
import com.birb.game.Utils.Coordinate;

public class Crow extends Entity{

    private final Ai ai;
    private final TickManager tm = TickManager.getInstance();
    private final EntityManager em = EntityManager.getInstance();
    private final SpriteManager sm = SpriteManager.getInstance();
    private final AnimationManager am = AnimationManager.getInstance();
    private Action currentAction;
    private boolean turned = false;
    private Coordinate rPoint;
    private Coordinate nearest;

    public Crow(float x, float y, float w, float h, float speed, float scale) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.speed = speed;
        this.scale = scale;
        this.ai = new Ai();
    }

    public void update() {
        // Current position of actor
        Coordinate c = new Coordinate(x, y, w, h);

        // -- DEBUG --
        //System.out.println(currentAction);
        //System.out.println(c.toString());



        // -- DEBUG END --


        // Shadow
        sm.draw("shadow", x, y - 10f, scale, scale);

        // Ai behaviour update
        if (tm.getSlowT() > (5f + (Math.random() * 10f))) {
            currentAction = ai.update(c);
            if (currentAction == Action.WANDER) rPoint = new Coordinate(100f + (float)Math.random() * 1720, 100f + (float)Math.random() * 880, 0, 0);
            if (em.nearestBread(c).x < 2000f && em.nearestBread(c).y < 2000f) nearest = em.nearestBread(c);
            System.out.println(currentAction);
        }

        // Ai actions
        if (currentAction == Action.IDLE) {
            sm.draw(am.animate("crow_idle"), x, y, scale, scale);

        } else if (currentAction == Action.CHIRP) {
            sm.draw(am.animateFast("crow_chirp"), x, y, scale, scale);

        } else if (currentAction == Action.TRACE) {
            // Trace breadcrumb
            System.out.println(Math.abs(x - nearest.x) + " " + Math.abs(y - nearest.y));

            // linear direction to point x (nearest)
            float direction = (float) (Math.atan2(nearest.x - x, -(nearest.y - y)) - (Math.PI / 2));

            // Move actor
            if (x + Math.cos(direction) * speed > 100f && x + Math.cos(direction) * speed < 1820f) x += Math.cos(direction) * speed;
            if (y + Math.sin(direction) * speed > 100f && y + Math.sin(direction) * speed < 980f) y += Math.sin(direction) * speed;

            // Directional animations
            if (Math.cos(direction) >= 0) {
                sm.draw(am.animateFast("crow_walking"), x, y, scale, scale);
                turned = false;
            } else {
                sm.draw(am.animateFast("crow_walking"), x + (64 * scale), y, -scale, scale);
                turned = true;
            }

            if ((Math.abs(x - nearest.x) < 5.0f) && (Math.abs(y - nearest.y) < 5.0f)) {
                System.out.println("near");
                currentAction = Action.FEED;
            }


        } else if (currentAction == Action.WANDER) {

            if (Math.abs(x - rPoint.x) > 10f && Math.abs(y - rPoint.y) > 10f) {
                // linear direction to point x (random)
                float direction = (float) (Math.atan2(rPoint.x - x, -(rPoint.y - y)) - (Math.PI / 2));

                // Move actor (Slower, wandering)
                if ((x + Math.cos(direction) * (speed / 2) > 100f) && (x + Math.cos(direction) * (speed / 2)  < 1820f)) x += Math.cos(direction) * (speed / 2);
                if ((y + Math.sin(direction) * (speed / 2) > 100f) && (y + Math.sin(direction) * (speed / 2) < 980f)) y += Math.sin(direction) * (speed / 2);

                // Directional animations
                if (Math.cos(direction) > 0 && Math.sin(direction) <= 0.9 && Math.sin(direction) >= -0.9) {
                    sm.draw(am.animate("crow_walking"), x, y, scale, scale);
                } else if (Math.cos(direction) < 0 && Math.sin(direction) <= 0.9 && Math.sin(direction) >= -0.9) {
                    sm.draw(am.animateFast("crow_walking"), x + (64 * scale), y, -scale, scale);
                }
            } else {
                if (!turned) {
                    sm.draw(am.animate("crow_idle"), x, y, scale, scale);
                } else {
                    sm.draw(am.animate("crow_idle"), x + (64 * scale), y, -scale, scale);
                }
            }

        } else {
            if (!turned) {
                sm.draw(am.animate("crow_idle"), x, y, scale, scale);
            } else {
                sm.draw(am.animate("crow_idle"), x + (64 * scale), y, -scale, scale);
            }

        }
    }

    public void onCollide(Entity e) {

    }

    public EntityType getType() {
        return EntityType.CROW;
    }
}
