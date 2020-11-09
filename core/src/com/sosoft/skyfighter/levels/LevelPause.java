package com.sosoft.skyfighter.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sosoft.skyfighter.SkyFighter;
import com.sosoft.skyfighter.menu.Menu;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sizeBy;

public class LevelPause {
    private final SkyFighter app;
    public SpriteBatch batch;
    private Stage stage;

    Texture tempTxt1;
    Texture tempTxt3;
    Texture tempTxt4;
    Texture tempTxt5;
    Texture tempTxt6;

    private Image mainArt1Pause;
    private Image pauseMenu;

    private Image continueImage;
    private Image settingsImage;
    private Image exitImageMenu;


    public LevelPause(final SkyFighter mainWindow, final Level level) {
        app = mainWindow;
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        tempTxt1 = new Texture("Menu\\BlackRec.png");
        mainArt1Pause = new Image(tempTxt1);
        mainArt1Pause.setPosition(0, 0);
        mainArt1Pause.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mainArt1Pause.addAction(alpha(0.75f));

        tempTxt3 = new Texture("Menu\\PauseMenu.png");
        pauseMenu = new Image(tempTxt3);
        pauseMenu.setSize(Gdx.graphics.getWidth()/3.2f, Gdx.graphics.getHeight()/3.6f);
        pauseMenu.setPosition(Gdx.graphics.getWidth() / 2 - pauseMenu.getWidth() / 2, Gdx.graphics.getHeight() - pauseMenu.getHeight() - 75);

        tempTxt4 = new Texture("Menu\\ContinueImage.png");
        continueImage = new Image(tempTxt4);
        continueImage.setSize(Gdx.graphics.getWidth()/4.8f, Gdx.graphics.getHeight()/7.2f);
        continueImage.setPosition(Gdx.graphics.getWidth() / 2 - continueImage.getWidth() / 2, Gdx.graphics.getHeight() - 2 * continueImage.getHeight() - 100);
        continueImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                level.isPaused = false;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                continueImage.setColor(Color.GOLD);
                continueImage.addAction(sizeBy(10f, 10f, 0.3f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                continueImage.setColor(Color.WHITE);
                continueImage.addAction(sizeBy(-10f, -10f, 0.3f));
            }
        });

        tempTxt5 = new Texture("Menu\\SettingsImage.png");
        settingsImage = new Image(tempTxt5);
        settingsImage.setSize(Gdx.graphics.getWidth()/4.8f, Gdx.graphics.getHeight()/7.2f);
        settingsImage.setPosition(Gdx.graphics.getWidth() / 2 - settingsImage.getWidth() / 2, Gdx.graphics.getHeight() - 3 * settingsImage.getHeight() - 100);
        settingsImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                settingsImage.setColor(Color.GOLD);
                settingsImage.addAction(sizeBy(10f, 10f, 0.3f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                settingsImage.setColor(Color.WHITE);
                settingsImage.addAction(sizeBy(-10f, -10f, 0.3f));
            }
        });


        tempTxt6 = new Texture("Menu\\ExitToTheMenuImage.png");
        exitImageMenu = new Image(tempTxt6);
        exitImageMenu.setSize(Gdx.graphics.getWidth()/4.8f, Gdx.graphics.getHeight()/7.2f);
        exitImageMenu.setPosition(Gdx.graphics.getWidth() / 2 - exitImageMenu.getWidth() / 2, Gdx.graphics.getHeight() - 4 * exitImageMenu.getHeight() - 100);
        exitImageMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(new Menu(app));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                exitImageMenu.setColor(Color.GOLD);
                exitImageMenu.addAction(sizeBy(10f, 10f, 0.3f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                exitImageMenu.setColor(Color.WHITE);
                exitImageMenu.addAction(sizeBy(-10f, -10f, 0.3f));
            }
        });

        stage.addActor(mainArt1Pause);
        stage.addActor(pauseMenu);
        stage.addActor(continueImage);
        stage.addActor(settingsImage);
        stage.addActor(exitImageMenu);
    }

    public void update(float delta) {
        Gdx.input.setInputProcessor(stage);
        stage.act(delta);
    }

    public void Draw(float delta) {
        update(delta);
        stage.draw();
    }

    public void dispose()
    {
        batch.dispose();
        stage.dispose();
        tempTxt1.dispose();
        tempTxt3.dispose();
        tempTxt4.dispose();
        tempTxt5.dispose();
        tempTxt6.dispose();
    }
}
