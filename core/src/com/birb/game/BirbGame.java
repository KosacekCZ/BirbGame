package com.birb.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Graphics;
import com.birb.game.Entities.Breadcrumb;
import com.birb.game.Entities.Crow;
import com.birb.game.Entities.Player;
import com.birb.game.Managers.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.ScreenUtils;
import com.birb.game.Utils.Textures;

public class BirbGame extends ApplicationAdapter {
	SpriteManager sm;
	EntityManager em;
	TickManager tm;
	SceneDrawManager sd;
	DropableManager dm;
	Textures t;

	@Override
	public void create () {
		// Directive for managers
		sm = SpriteManager.getInstance();
		em = EntityManager.getInstance();
		tm = TickManager.getInstance();
		sd = SceneDrawManager.getInstance();
		dm = DropableManager.getInstance();
		t = Textures.getInstance();

		// Temp spawns & setups
		em.spawnPlayer(new Player(500, 500, 20, 20, 1.7f, 8, 100, 10));
		em.addEntity(new Crow(200, 200, 20, 20, 7,1.7f));
		em.addEntity(new Crow(200, 200, 20, 20, 7,1.7f));
		em.addEntity(new Crow(200, 200, 20, 20, 7,1.7f));
		t.loadTextures();


		// Setup Window properties
		Gdx.graphics.setVSync(true);
		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());

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
