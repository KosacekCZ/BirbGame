package com.birb.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.birb.game.Entities.Breadcrumb;
import com.birb.game.Entities.Crow;
import com.birb.game.Entities.Player;
import com.birb.game.Enums.EntityType;
import com.birb.game.Managers.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.ScreenUtils;
import com.birb.game.Utils.Font;
import com.birb.game.Utils.PlacementHelper;
import com.birb.game.Utils.Textures;

import java.util.Arrays;

public class BirbGame extends ApplicationAdapter {
	SpriteManager sm;
	EntityManager em;
	TickManager tm;
	SceneDrawManager sd;
	DropableManager dm;
	PlacementHelper ph;
	Textures t;

	@Override
	public void create () {
		// Directive for managers
		sm = SpriteManager.getInstance();
		em = EntityManager.getInstance();
		tm = TickManager.getInstance();
		sd = SceneDrawManager.getInstance();
		dm = DropableManager.getInstance();
		ph = PlacementHelper.getInstance();
		t = Textures.getInstance();

		// Temp spawns & setups
		em.spawnPlayer(new Player(500f, 500f, 40f, 40f, 1.7f, 8, 100, 10));
		em.addEntity(new Crow(200, 200, 40, 40, 7,1.7f));
		em.addEntity(new Crow(700, 500, 40, 40, 7,1.7f));
		em.addEntity(new Crow(1200, 300, 40, 40, 7,1.7f));

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
		dm.update();
		em.update();
		ph.update();



		sm.issueDraw();
		debugConsole();
		cursorPos();
		// Frame update end
		sm.batchEnd();


		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
	}

	private void debugConsole() {
		for (int i = 0; i < em.getCrows().toArray().length; i++) {
			Font.draw("Crow #" + i + ": " + em.getCrows().toArray()[i].toString(), 25, 50, (i + 1) * 50);
		}
		Font.draw("Placement Mode: " + ph.isPlacementModeOn(), 20, 50, Gdx.graphics.getHeight() - 100);
		Font.draw("Placed Items: " + ph.getPlacedItemsOverview(), 20, 50, Gdx.graphics.getHeight() - 125);

	}

	private void cursorPos() {
		Font.draw("X:" + String.valueOf(Gdx.input.getX()) + ", Y:" + String.valueOf(Gdx.input.getY()), 25, 50, Gdx.graphics.getHeight() - 50);
	}

	@Override
	public void dispose () {
		sm.dispose();
	}
}
