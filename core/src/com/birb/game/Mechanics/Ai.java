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
        if (em.isBread()) {
            // TODO eat bred
            if ((Math.abs(crd.x - em.nearestBread(crd).x) > 10f) && (Math.abs(crd.y - em.nearestBread(crd).y) > 10f)) {
                System.out.println((crd.x - em.nearestBread(crd).x) + " X len bread, " + (crd.y - em.nearestBread(crd).y) + " Y len bread" );
                return Action.TRACE;
            } else {
                System.out.println((crd.x - em.nearestBread(crd).x) + " X len bread, " + (crd.y - em.nearestBread(crd).y) + " Y len bread" );
                return Action.FEED;
            }
        } else if(Math.random() == 0.6) {
            // TODO attack mechanics
        } else {
                if (Math.random() > 0.5) {
                    if (Math.random() > 0.5) {
                        if (Math.random() > 0.5) {
                            if (Gdx.graphics.getWidth() - crd.x > 10f) return Action.W_D;
                        } else {
                            return Action.W_A;
                        }
                    } else {
                        if (Math.random() > 0.5) {
                            if (Gdx.graphics.getHeight() - crd.y > 10f) return Action.W_W;
                        } else {
                            return Action.W_S;
                        }
                    }
                } else {
                    return Action.CHIRP;
                }
            }
        return Action.IDLE;
    }



}
