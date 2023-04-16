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

        // Ai behaviour
        if (tm.getSlowT() > 5) currentAction = ai.update(c);

        if (currentAction == Action.IDLE) {
            sm.draw(am.animate("crow_idle"), x, y, scale, scale);

        } else if (currentAction == Action.CHIRP) {
            sm.draw(am.animateFast("crow_chirp"), x, y, scale, scale);

        } else if (currentAction == Action.TRACE) {
            // Trace breadcrumb
            Coordinate nearest = em.nearestBread(c);
            System.out.println(Math.abs(x - nearest.x) + " " + Math.abs(y - nearest.y));

            // linear direction to point x (nearest)
            float direction = (float) (Math.atan2(nearest.x - x, -(nearest.y - y)) - (Math.PI / 2));

            // Move player
            this.x += Math.cos(direction) * speed;
            this.y += Math.sin(direction) * speed;

            // Directional animations
            if (Math.cos(direction) >= 0) {
                sm.draw(am.animateFast("crow_walking"), x, y, scale, scale);
                turned = false;
            } else {
                sm.draw(am.animateFast("crow_walking"), x + (64 * scale), y, -scale, scale);
                turned = true;
            }

            if ((Math.abs(x - nearest.x) < 20.0f) && (Math.abs(y - nearest.y) < 20.0f)) {
                System.out.println("near");
                currentAction = Action.FEED;
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
