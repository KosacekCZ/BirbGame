package com.birb.game.Managers;

import com.badlogic.gdx.Gdx;
import com.birb.game.Entities.Breadcrumb;
import com.birb.game.Entities.Entity;

public class DropableManager {
    public static DropableManager instance;
    public static DropableManager getInstance() {
        if (instance == null) instance = new DropableManager();
        return instance;
    }

    EntityManager em = EntityManager.getInstance();
    TickManager tm = TickManager.getInstance();

    public void update() {
        if (tm.getT() == 59) {
            if (Math.random() > 0.5) {
                dropBreadcrumb((float)Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 200f);
            }
        }


    }

    private void dropBreadcrumb(float spawnX, float spawnY) {
        em.addEntity(new Breadcrumb(spawnX, spawnY, 20, 20, 1.5f,10, 4, 6, 1, false));
    }


    
}
