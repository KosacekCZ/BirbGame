package com.birb.game.Managers;

import com.badlogic.gdx.Gdx;
import com.birb.game.Entities.*;
import com.birb.game.Enums.*;
import com.birb.game.Utils.Coordinate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class EntityManager {
    private final ArrayList<Entity> entities = new ArrayList<>();
    private final ArrayList<Entity> tempBuffer = new ArrayList<>();
    private long t = 0;
    private static EntityManager instance;
    private Player player;

    public static EntityManager getInstance() {
        if (instance == null) instance = new EntityManager();
        return instance;
    }

    public void addEntity(Entity e) {
        tempBuffer.add(e);
    }

    public void update() {
        for (Entity e: entities) {
            // Entity tick

            e.update();
            //System.out.println(entities.size());


            // Collision check for each entity in list
            for (Entity f : entities) {
                if (e != f &&
                        e.getX() < f.getX() + f.getW() &&
                        e.getX() + e.getW() > f.getX() &&
                        e.getY() < f.getY() + f.getH() &&
                        e.getY() + e.getH() > f.getY()) {

                    e.onCollide(f);
                }
            }
        }
        // Despawn of "dead" entities
        entities.removeIf(Entity::isDestroy);
        entities.addAll(tempBuffer);
        tempBuffer.clear();
    }

    public Stream<Entity> getCrows() {
        return entities.stream().filter(entity -> entity.getType() == EntityType.CROW);
    }

    public Player getPlayer() {
        return player;
    }

    public void spawnPlayer(Player player) {
        this.player = player;
        addEntity(player);
    }

    public boolean isBread() {
        for (Entity e : entities) {
            if (e.getType() == EntityType.BREADCRUMB) return true;
        }
        return false;
    }

    public Coordinate nearestBread(Coordinate c) {
        Coordinate closest = new Coordinate(10000, 10000, 2, 2);
        float distOrigin = (float)Math.sqrt(Math.pow(Math.abs(closest.x - c.x), 2) + Math.pow(Math.abs(closest.y - c.y), 2));
        float distNew;
        for (Entity e: entities) {
            if (e.getType() == EntityType.BREADCRUMB) {
                distNew = (float)Math.sqrt(Math.pow(Math.abs(e.getX() - c.x), 2) + Math.pow(Math.abs(e.getY() - c.y), 2));
                if (distNew < distOrigin) {
                    closest = new Coordinate(e.getX(), e.getY(), e.getW(), e.getH());
                    distOrigin = distNew;
                }
            }
        }
        return closest;
    }

    public boolean isEnemiesDead() {
        int enemies = 0;
        for (Entity e : entities) {
            if(e.getType() == EntityType.ENEMY) enemies++;
        }

        return (enemies == 0);
    }
}
