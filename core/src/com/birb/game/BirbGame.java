package com.birb.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Graphics;
import com.birb.game.Entities.Breadcrumb;
import com.birb.game.Entities.Player;
import com.birb.game.Managers.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.ScreenUtils;

public class BirbGame extends ApplicationAdapter {
	SpriteManager sm;
	EntityManager em;
	TickManager tm;
	SceneDrawManager sd;
	DropableManager dm;

	@Override
	public void create () {
		sm = SpriteManager.getInstance();
		em = EntityManager.getInstance();
		tm = TickManager.getInstance();
		sd = SceneDrawManager.getInstance();
		em.spawnPlayer(new Player(500, 500, 20, 20, 1.7f, 8, 100, 10));
		dm = DropableManager.getInstance();


		Gdx.graphics.setVSync(true);
		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());


		sm.loadSprite("Player/pigeon_still.png", "pigeon_still");
		sm.loadSprite("Player/pigeon_walking.png", "pigeon_walking");
		sm.loadSprite("Player/pigeon_walking_up.png", "pigeon_walking_up");
		sm.loadSprite("Player/pigeon_walking_down.png", "pigeon_walking_down");
		sm.loadSprite("Misc/shadow.png", "shadow");
		sm.loadSprite("Scene/grass.png", "grass");
		sm.loadSprite("Scene/tiles.png", "pavement");
		sm.loadSprite("Scene/bench.png", "bench");
		sm.loadSprite("Scene/stone1.png", "stone1");
		sm.loadSprite("Scene/stone2.png", "stone2");
		sm.loadSprite("Misc/breadcrumb_1.png", "breadcrumb1");
	}

	@Override
	public void render () {

		// Clear screen
		ScreenUtils.clear(1f, 1f, 1f, 1);

		sm.batchBegin();
		// Frame update start

		// Draw background
		sd.drawBackground();

		// Frame update
		tm.update();
		em.update();
		dm.update();


		// Frame update end
		sm.batchEnd();

		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
	}

	@Override
	public void dispose () {
		sm.dispose();
	}
}
