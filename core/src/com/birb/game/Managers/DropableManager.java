package com.birb.game.Managers;

import com.badlogic.gdx.Gdx;
import com.birb.game.Entities.Breadcrumb;
import com.birb.game.Entities.Entity;

import javax.naming.spi.NamingManager;

public class DropableManager {
    public static DropableManager instance;
    public static DropableManager getInstance() {
        if (instance == null) instance = new DropableManager();
        return instance;
    }

    EntityManager em = EntityManager.getInstance();
    TickManager tm = TickManager.getInstance();

    public void update() {
        if (tm.getSlowT() > 14.9) {
            if (Math.random() > 0.5) {
                // dropBreadcrumb((float)Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 200f);
                 dropBatch((int) (Math.random() * 10));
            }
        }


    }

    private void dropBreadcrumb(float spawnX, float spawnY) {
        em.addEntity(new Breadcrumb(spawnX, spawnY, 20, 20, 1.5f,10, 4, 6, 1, false, 0.13f));
    }

    private void dropBreadcrumb(float spawnX, float spawnY, int lifetime, int bounces, int curveHeight, double curveSpread, boolean isFlipped, float fallSpread) {
        em.addEntity(new Breadcrumb(spawnX, spawnY, 20, 20, 1.5f,lifetime, bounces, curveHeight, curveSpread, isFlipped, fallSpread));
    }

    private void dropBatch(int amount) {
        for (int i = 0; i <= amount; i++) {
            dropBreadcrumb(
                    500f + (float) Math.random() * 100f,
                    500f + (float) Math.random() * 200f,
                    10 + (int) (Math.random() * 5),
                    3 + (int) (Math.random() * 3),
                    5 + (int) (Math.random() * 1),
                    1 + (Math.random() * 1),
                    Math.random() > 0.5,
                    0.1f + ((float) Math.random() * 0.2f)
                    );
        }
    }


    
}
