package com.sosoft.skyfighter.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.sosoft.skyfighter.ContentManager;
import com.sosoft.skyfighter.SkyFighter;

import static com.badlogic.gdx.Gdx.files;
import static com.badlogic.gdx.Gdx.gl;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleBy;

public class LevelSelectMenu implements Screen {

    private SkyFighter skyFighter;
    private Stage stage;
    private Skin skin;
    private Array<JsonValue> mapDescriptions;
    private Array<Texture> textures;
    boolean showed;

    public LevelSelectMenu(final SkyFighter game) {
        showed = false;
        skyFighter = game;
        stage = new Stage();
        //stage.setDebugAll(true);
        skin = new Skin(files.internal("Interface/craftacular/skin/craftacular-ui.json"));
        mapDescriptions = ContentManager.getAllMaps();
        textures = new Array<Texture>();
        Gdx.input.setInputProcessor(stage);

        //GUI STARTS HERE

        final float previewSizeX = Gdx.graphics.getWidth() / 3f - Gdx.graphics.getWidth() / 20f;
        final float previewSizeY = previewSizeX / 16f * 9f;

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        stage.addActor(mainTable);

        mainTable.add(new Label("Choose level", skin, "title")).top().row();
        Table levelContainer = new Table();

        ScrollPane levelScrollPane = new ScrollPane(levelContainer, skin);
        mainTable.add(levelScrollPane).fill().expand().row();
        stage.setScrollFocus(levelContainer);

        Table buttonGroup = new Table();
        TextButton backButton = new TextButton("Back",skin, "default");
        mainTable.add(buttonGroup);
        buttonGroup.add(backButton).pad(5).padLeft(Gdx.graphics.getWidth() - backButton.getWidth() - 5);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                skyFighter.setScreen(new Menu(skyFighter));
            }
        });

        int lineCounter = 0;
        for (final JsonValue mapDesc : mapDescriptions) {
            Texture preview = new Texture(mapDesc.getString("previewPath"));
            textures.add(preview);
            final Image tempImage = new Image(preview);

            levelContainer.add(tempImage).size(previewSizeX,previewSizeY).pad(previewSizeX / 20f);
            tempImage.addListener(new ClickListener(){
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    super.enter(event, x, y, pointer, fromActor);
                    tempImage.addAction(scaleBy(0.1f, 0.1f, 0.3f));
                    tempImage.addAction(moveBy(-previewSizeX / 20f, -previewSizeY / 20f,0.3f));
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    super.exit(event, x, y, pointer, toActor);
                    tempImage.addAction(scaleBy(-0.1f, -0.1f, 0.3f));
                    tempImage.addAction(moveBy(previewSizeX / 20f, previewSizeY / 20f,0.3f));
                }

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    //skyFighter.setScreen(new Level(skyFighter, mapDesc, true, Controllers.getControllers()));
                    skyFighter.setScreen(new ControllersSelectMenu(skyFighter, mapDesc));
                }
            });
            if (++lineCounter == 3) {
                levelContainer.row();
                lineCounter = 0;
            }
        }

    }

    @Override
    public void show() {
        System.out.println("SHOW");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gl.glClearColor(skin.getColor("gray").r,skin.getColor("gray").g,skin.getColor("gray").b,skin.getColor("gray").a);
        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        if (showed)
            skyFighter.setScreen(new LevelSelectMenu(skyFighter));
        showed = true;
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
        for (Texture texture : textures)
            texture.dispose();
    }
}
