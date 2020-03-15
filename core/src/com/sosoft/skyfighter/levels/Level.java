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

import java.util.ArrayList;

public class Level implements Screen {
    private final skyFighter app;
    public boolean isPaused;

    private LevelPause levelPause;
    TiledMap tiledMap;
    ArrayList<Hero> players = new ArrayList<Hero>();
    LevelController levelController;
    LevelDrawer levelDrawer;

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
    }
}
