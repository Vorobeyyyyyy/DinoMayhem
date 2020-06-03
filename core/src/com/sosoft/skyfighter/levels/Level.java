package com.sosoft.skyfighter.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;
import com.sosoft.skyfighter.heroes.Hero;
import com.sosoft.skyfighter.skyFighter;
import com.sosoft.skyfighter.weapons.Bullet;


public class Level implements Screen {
    private final skyFighter app;
    public boolean isPaused;

    private LevelPause levelPause;
    public TiledMap tiledMap;
    public Array<Hero> players = new Array<Hero>();
    public Array<Bullet> bullets = new Array<Bullet>();

    public LevelController levelController;
    public LevelDrawer levelDrawer;

    Level(skyFighter app, String levelName, boolean isKeyboard, Array<Controller> controllers) {
        this.app = app;
        levelPause = new LevelPause(app, this);
        tiledMap = new TmxMapLoader().load("Tilemaps/Map1.tmx");

        levelController = new LevelController(this, isKeyboard, controllers);
        levelDrawer = new LevelDrawer(this, tiledMap, true);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        levelDrawer.updateAndRender();
        if (!isPaused)
            levelController.update(delta);
        else
            levelPause.Draw(delta);
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            isPaused = !isPaused;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        tiledMap.dispose();
        levelPause.dispose();
        levelController.dispose();
        levelDrawer.dispose();
        for(Hero hero: players)
            hero.dispose();
    }
}
