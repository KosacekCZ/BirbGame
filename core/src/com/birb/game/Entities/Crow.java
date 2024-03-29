package com.birb.game.Entities;

import com.badlogic.gdx.Gdx;
import com.birb.game.Enums.Action;
import com.birb.game.Enums.EntityType;
import com.birb.game.Enums.ZI;
import com.birb.game.Managers.AnimationManager;
import com.birb.game.Managers.EntityManager;
import com.birb.game.Managers.SpriteManager;
import com.birb.game.Managers.TickManager;
import com.birb.game.Mechanics.Ai;
import com.birb.game.Utils.Coordinate;
import com.birb.game.Utils.Font;
import com.birb.game.Utils.Amath;

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
    private Coordinate c;
    private float pushVelocity = 0f;
    private float throwDirection = 0;

    private int cooldown = 0;
    private final int selfTickRate;

    public Crow(float x, float y, float w, float h, float speed, float scale) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.speed = speed;
        this.scale = scale;
        this.selfTickRate = Amath.getRandomNumber(3,5) + Amath.getRandomNumber(1,5);
        this.ai = new Ai();
        changeCoordinate();
    }

    public void update() {
        // Current position of actor
        c = new Coordinate(x, y, w, h, 0, currentAction);
        nearest = em.nearestBread(c);

        // Colision movement handling
        if (x > 100 && x < Gdx.graphics.getWidth() - 100 && y > 100 && y < Gdx.graphics.getHeight() - 100) {
            x -= Math.cos(throwDirection) * pushVelocity;
            y -= Math.sin(throwDirection) * pushVelocity;

            pushVelocity *= (pushVelocity > 0.25 ? 0.85 : 0);

        } else {
            pushVelocity = 0;
        }

        // -- DEBUG --
        //System.out.println(currentAction);
        //System.out.println(c.toString());



        // -- DEBUG END --

        cooldown = (tm.getT() % 60 == 0 ? cooldown + 1 : cooldown);

        if (cooldown ==  selfTickRate) {
            currentAction = ai.update(c);
            cooldown = 0;
            Font.draw(currentAction.toString(), 32, 100, 100);
        }


        // Shadow
        sm.draw("shadow", x, y - 10f, scale, scale, ZI.SHADOW);

        // Ai behaviour update

        // Ai actions
        executeBehaviour();
        }

    private void changeCoordinate() {
        rPoint = new Coordinate(100f + (float)Math.random() * 1720, 100f + (float)Math.random() * 880, 0, 0);
    }

    private void executeBehaviour() {
        if (currentAction == Action.IDLE) {
            sm.draw(am.animate("crow_idle"), x, y, scale, scale, 0, ZI.ENTITIES);

        } else if (currentAction == Action.CHIRP) {
            sm.draw(am.animateFast("crow_chirp"), x, y, scale, scale, 0, ZI.ENTITIES);

        } else if (currentAction == Action.TRACE && em.nearestBread(c).x < 2000f && em.nearestBread(c).y < 2000f) {
            // Trace breadcrumb
            // System.out.println(Math.abs(x - nearest.x) + " " + Math.abs(y - nearest.y));

            // linear direction to point x (nearest)
            float direction = (float) (Math.atan2(nearest.x - x, -(nearest.y - y)) - (Math.PI / 2));

            // Move actor
            if (x + Math.cos(direction) * speed > 100f && x + Math.cos(direction) * speed < 1820f) x += Math.cos(direction) * speed;
            if (y + Math.sin(direction) * speed > 100f && y + Math.sin(direction) * speed < 980f) y += Math.sin(direction) * speed;

            // Directional animations
            if (Math.cos(direction) >= 0) {
                sm.draw(am.animateFast("crow_walking"), x, y, scale, scale, 0, ZI.ENTITIES);
                turned = false;
            } else {
                sm.draw(am.animateFast("crow_walking"), x + (64 * scale), y, -scale, scale, 0, ZI.ENTITIES);
                turned = true;
            }

            if ((Math.abs(x - nearest.x) < 5.0f) && (Math.abs(y - nearest.y) < 5.0f)) {
                System.out.println("near");
                currentAction = Action.FEED;
            } else {
                currentAction = Action.TRACE;
            }


        } else if (currentAction == Action.WANDER) {

            if ((float)Math.sqrt(Math.pow(Math.abs(x - rPoint.x), 2) + Math.pow(Math.abs(y - rPoint.y), 2)) > 5f ) {
                // Debug
                sm.draw("deb", rPoint.x, rPoint.y, 1f, 1f, ZI.DROPABLE);

                // linear direction to point x (random)
                float direction = (float) (Math.atan2(rPoint.x - x, -(rPoint.y - y)) - (Math.PI / 2));

                // Move actor (Slower, wandering)
                if ((x + Math.cos(direction) * (speed / 2) > 100f) && (x + Math.cos(direction) * (speed / 2.3f)  < 1820f)) x += Math.cos(direction) * (speed / 2);
                if ((y + Math.sin(direction) * (speed / 2) > 100f) && (y + Math.sin(direction) * (speed / 2.3f) < 980f)) y += Math.sin(direction) * (speed / 2);

                // Directional animations
                if (Math.cos(direction) > 0 && Math.sin(direction) <= 0.9 && Math.sin(direction) >= -0.9) {
                    sm.draw(am.animate("crow_walking"), x, y, scale, scale, 0, ZI.ENTITIES);
                } else if (Math.cos(direction) < 0 && Math.sin(direction) <= 0.9 && Math.sin(direction) >= -0.9) {
                    sm.draw(am.animateFast("crow_walking"), x + (64 * scale), y, -scale, scale, 0, ZI.ENTITIES);
                }
            } else {
                // Change action on success
                changeCoordinate();

                // Draw něco ěco
                if (!turned) {
                    sm.draw(am.animate("crow_idle"), x, y, scale, scale, 0, ZI.ENTITIES);
                } else {
                    sm.draw(am.animate("crow_idle"), x + (64 * scale), y, -scale, scale, 0, ZI.ENTITIES);
                }
            }

        } else if (currentAction == Action.FEED) {
            sm.draw(am.animateFast("crow_chirp"), x, y, scale, scale, 0, ZI.ENTITIES);
            if (tm.getSlowT() > 2.0f) {
                ai.update(new Coordinate(x, y, w, h));
                System.out.println("Action changed");
            }
        } else {
            if (!turned) {
                sm.draw(am.animate("crow_idle"), x, y, scale, scale, 0, ZI.ENTITIES);
            } else {
                sm.draw(am.animate("crow_idle"), x + (64 * scale), y, -scale, scale, 0, ZI.ENTITIES);
            }

        }
    }

    @Override
    public String toString() {
        return currentAction + ": " + cooldown + ", phVel [" + pushVelocity + "]";
    }

    public void onCollide(Entity e) {
        if (e.getType().equals(EntityType.PLAYER)) {
            if (e.isAttacking()) {
                throwDirection = (float) (Math.atan2(e.getX() - x, -(e.getY() - y)) - (Math.PI / 2));
                pushVelocity = 18;
            }


        }
    }

    public EntityType getType() {
        return EntityType.CROW;
    }
}
