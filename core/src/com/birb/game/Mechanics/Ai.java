package com.birb.game.Mechanics;

import com.badlogic.gdx.Gdx;
import com.birb.game.Enums.Action;
import com.birb.game.Managers.EntityManager;
import com.birb.game.Managers.TickManager;
import com.birb.game.Utils.Coordinate;
import com.birb.game.Entities.Player;

public class Ai {

    TickManager tm = TickManager.getInstance();
    EntityManager em = EntityManager.getInstance();

    public Ai() {

    }

    public Action update(Coordinate crd) {
        // Base tree
        if (em.isBread() && crd.action == Action.IDLE) {
            // Decision : ?trace
            if (dist(crd, em.nearestBread(crd))[0] > 1f &&
                    dist(crd, em.nearestBread(crd))[1] > 1f &&
                    dist(crd, em.nearestBread(crd))[0] < 1000f &&
                    dist(crd, em.nearestBread(crd))[1] > 1000f &&
                    crd.action != Action.TRACE) {
                return Action.TRACE;
            }
            // Decision : ?!trace =?> near
            else if (dist(crd, em.nearestBread(crd))[0] <= 1f && dist(crd, em.nearestBread(crd))[1] <= 1f && crd.action != Action.FEED) {
                // Decision : ?attack =?> p-near
                if (playerDist(crd, em.getPlayer())[0] >= 5f && playerDist(crd, em.getPlayer())[1] >= 5f && crd.action == Action.FEED) {
                    return Action.ATTACK;
                } else {
                    return Action.FEED;
                }
            }
        } else {
            // !Bread -> idle state
            return (Math.random() > 0.5 ? Action.CHIRP : Action.IDLE);
        }
        // Default
        return (Math.random() > 0.5 ? Action.CHIRP : Action.IDLE);
    }

    private float[] dist(Coordinate c1, Coordinate c2) {
        return new float[] {Math.abs(c1.x - c2.x), Math.abs(c1.y - c2.y)};
    }

    private float[] playerDist(Coordinate c, Player p) {
        return new float[] {Math.abs(c.x - p.getX()), Math.abs(c.y - p.getY())};
    }
}
