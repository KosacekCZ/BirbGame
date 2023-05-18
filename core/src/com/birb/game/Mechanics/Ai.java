package com.birb.game.Mechanics;

import com.badlogic.gdx.Gdx;
import com.birb.game.Enums.Action;
import com.birb.game.Managers.EntityManager;
import com.birb.game.Managers.TickManager;
import com.birb.game.Utils.Coordinate;

public class Ai {

    TickManager tm = TickManager.getInstance();
    EntityManager em = EntityManager.getInstance();

    public Ai() {

    }

    public Action update(Coordinate crd) {
        if (em.isBread() && Math.random() > 0.2) {
            //TODO: eat bread
            if (Math.abs(em.nearestBread(crd).x) > 1f && Math.abs(em.nearestBread(crd).y) > 1f) {
                return Action.TRACE;
            } else {
                return Action.FEED;
            }

        } else if(Math.random() == 0.6) {
            // TODO attack mechanics
        } else {
                if (Math.random() > 0.5) {
                    return Action.WANDER;
                } else {
                    return Action.CHIRP;
                }
            }
        return Action.IDLE;
    }
}
