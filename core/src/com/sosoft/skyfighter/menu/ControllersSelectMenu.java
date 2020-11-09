package com.sosoft.skyfighter.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.mappings.Xbox;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.sosoft.skyfighter.ContentManager;
import com.sosoft.skyfighter.SkyFighter;
import com.sosoft.skyfighter.levels.Level;

import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.Gdx.files;
import static com.badlogic.gdx.Gdx.gl;

public class ControllersSelectMenu implements Screen {
    private Stage stage;
    private SkyFighter skyFighter;
    private Skin skin;
    private final Map<Controller, HeroSelectWidget> controllers;
    JsonValue mapDescription;
    final Table mainTable;
    Array<JsonValue> heroWaitAnimations;

    public ControllersSelectMenu(final SkyFighter game, JsonValue mapDesc) {
        mapDescription = mapDesc;
        stage = new Stage();
        stage.setDebugAll(true);
        controllers = new HashMap<Controller, HeroSelectWidget>();
        skyFighter = game;
        skin = new Skin(files.internal("Interface/craftacular/skin/craftacular-ui.json"));
        heroWaitAnimations = ContentManager.getAllHeroes();

        mainTable = new Table();
        mainTable.setFillParent(true);
        stage.addActor(mainTable);
        Gdx.input.setInputProcessor(stage);

        stage.addListener(new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (!controllers.containsKey(null)) {
                    HeroSelectWidget kbHeroSelectWidget = new HeroSelectWidget("Player1", skin, (Gdx.graphics.getWidth() - 60) / 4,heroWaitAnimations);
                    mainTable.add(kbHeroSelectWidget).pad(20f);
                    controllers.put(null, kbHeroSelectWidget);
                }
                return super.keyDown(event, keycode);
            }
        });

        for (Controller controller : Controllers.getControllers())
            controller.addListener(new ControllerAdapter(){
                @Override
                public boolean buttonDown(Controller controller, int buttonIndex) {
                    if (!controllers.containsKey(controller)) {
                        HeroSelectWidget heroSelectWidget = new HeroSelectWidget("Player1", skin, (Gdx.graphics.getWidth() - 60) / 4,heroWaitAnimations);
                        mainTable.add(heroSelectWidget);
                        controllers.put(controller, heroSelectWidget);
                    }
                    else
                    {
                        HeroSelectWidget widget = controllers.get(controller);
                        if (buttonIndex == Xbox.A)
                            widget.setReady(true);
                        if (buttonIndex == Xbox.B)
                            widget.setReady(false);
                        if (buttonIndex == Xbox.DPAD_LEFT)
                            widget.changeHero(false);
                        if (buttonIndex == Xbox.DPAD_RIGHT)
                            widget.changeHero(true);
                    }
                    return super.buttonDown(controller, buttonIndex);
                }
            });

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gl.glClearColor(skin.getColor("gray").r,skin.getColor("gray").g,skin.getColor("gray").b,skin.getColor("gray").a);
        stage.act(delta);
        stage.draw();


        boolean allReady = true;
        for (HeroSelectWidget widget : controllers.values()) {
            if (!widget.isReady) {
                allReady = false;
                break;
            }
        }

        if (allReady && !controllers.isEmpty()) {
            Array<Controller> controllerArray = new Array<Controller>();
            for (Controller controller : controllers.keySet())
                if (controller != null)
                    controllerArray.add(controller);
                boolean isKeyboard = controllers.containsKey(null);
                skyFighter.setScreen(new Level(skyFighter, mapDescription, isKeyboard, controllerArray));
            }

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

    }

    @Override
    public void dispose() {

    }
}
