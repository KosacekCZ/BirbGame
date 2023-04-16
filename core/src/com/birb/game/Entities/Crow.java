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

    public Crow() {
        this.ai = new Ai();
    }

    public void update() {
        if (tm.getSlowT() > 5) currentAction = ai.update(new Coordinate(this.x, this.y, this.w, this.h));

        if (currentAction == Action.IDLE) {
            sm.draw(am.animate("crow_idle"), this.x, this.y, this.scale, this.scale);

        } else if (currentAction == Action.CHIRP) {
            sm.draw(am.animate("crow_chirp"), this.x, this.y, this.scale, this.scale);

        } else if (currentAction == Action.TRACE) {


        }
    }

    public void onCollide(Entity e) {

    }

    public EntityType getType() {
        return EntityType.CROW;
    }
}
