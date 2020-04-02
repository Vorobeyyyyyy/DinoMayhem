package com.sosoft.skyfighter.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sosoft.skyfighter.skyFighter;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class Menu implements Screen {
    private final skyFighter app;

    private Stage stage;

    Texture tempTxt1;
    Texture tempTxt2;
    Texture tempTxt3;
    Texture tempTxt4;
    Texture tempTxt5;
    Texture tempTxt6;
    Texture tempTxt7;
    Texture tempTxt8;

    private Image mainArt1;
    private Image mainPanel;
    private Image nameGame;
    private Image mainMenu;

    private Image settingsImage;
    private Image playImage;
    private Image playOnlineImage;
    private Image exitImage;

    private Music menuMusic;


    public Menu(final skyFighter mainWindow) {
        app = mainWindow;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        tempTxt1 = new Texture("Menu\\MainArt5.jpg");
        mainArt1 = new Image(tempTxt1);
        mainArt1.setPosition(0, 0);
        mainArt1.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        tempTxt2 = new Texture("Menu\\BlackRec.png");
        mainPanel = new Image(tempTxt2);
        mainPanel.setPosition(-Gdx.graphics.getWidth()/3.8f, 0);
        mainPanel.setSize(Gdx.graphics.getWidth()/3.8f, Gdx.graphics.getHeight());
        mainPanel.addAction(sequence(alpha(0.7f), parallel(moveBy(Gdx.graphics.getWidth()/3.8f, 0, 0.4f))));

        tempTxt3 = new Texture("Menu\\NameGame.png");
        nameGame = new Image(tempTxt3);
        nameGame.setSize(Gdx.graphics.getWidth()/4.8f, nameGame.getHeight()/3.6f);
        nameGame.setOrigin(nameGame.getWidth() * 0.7f, nameGame.getHeight());
        nameGame.addAction(sequence(alpha(0), scaleTo(.1f, .1f),
                parallel(fadeIn(1f, Interpolation.pow2),
                        scaleTo(2f, 2f, 1.5f, Interpolation.swing),
                        moveTo(stage.getWidth() * 0.65f, stage.getHeight() * 0.8f))));

        tempTxt4 = new Texture("Menu\\MainMenu.png");
        mainMenu = new Image(tempTxt4);
        mainMenu.setSize(Gdx.graphics.getWidth()/3.8f, Gdx.graphics.getHeight()/3.9f);
        mainMenu.setPosition(-Gdx.graphics.getWidth()/3.2f, 0.75f * Gdx.graphics.getHeight());
        mainMenu.addAction(moveBy(Gdx.graphics.getWidth()/3.2f, 0, 0.4f));

        tempTxt5 = new Texture("Menu\\PlayImage.png");
        playImage = new Image(tempTxt5);
        playImage.setSize(Gdx.graphics.getWidth()/3.8f, Gdx.graphics.getHeight()/7.2f);
        playImage.setPosition(-Gdx.graphics.getWidth()/3.2f, 0.75f * Gdx.graphics.getHeight() );
        playImage.addAction(moveBy(Gdx.graphics.getWidth()/3.2f, 0, 0.4f));
        playImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuMusic.pause();
                app.setScreen(new Level(app, "Tilemaps/Map1.tmx", true, Controllers.getControllers()));
                //app.setScreen(new SettingsScreen());
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                playImage.setColor(Color.GOLD);
                playImage.addAction(sizeBy(10f, 10f, 0.3f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                playImage.setColor(Color.WHITE);
                playImage.addAction(sizeBy(-10f, -10f, 0.3f));
            }
        });

        tempTxt6 = new Texture("Menu\\PlayOnlineImage.png");
        playOnlineImage = new Image(tempTxt6);
        playOnlineImage.setSize(Gdx.graphics.getWidth()/3.8f, Gdx.graphics.getHeight()/7.2f);
        playOnlineImage.setPosition(-Gdx.graphics.getWidth()/3.2f, 0.75f * Gdx.graphics.getHeight() -  playImage.getHeight());
        playOnlineImage.addAction(moveBy(Gdx.graphics.getWidth()/3.2f, 0, 0.4f));
        playOnlineImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                playOnlineImage.setColor(Color.GOLD);
                playOnlineImage.addAction(sizeBy(10f, 10f, 0.3f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                playOnlineImage.setColor(Color.WHITE);
                playOnlineImage.addAction(sizeBy(-10f, -10f, 0.3f));
            }
        });

        tempTxt7 = new Texture("Menu\\SettingsImage.png");
        settingsImage = new Image(tempTxt7);
        settingsImage.setSize(Gdx.graphics.getWidth()/3.8f, Gdx.graphics.getHeight()/7.2f);
        settingsImage.setPosition(-Gdx.graphics.getWidth()/3.2f, 0.75f * Gdx.graphics.getHeight() - 2 * playImage.getHeight());
        settingsImage.addAction(moveBy(Gdx.graphics.getWidth()/3.2f, 0, 0.4f));
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

        tempTxt8 = new Texture("Menu\\ExitImage.png");
        exitImage = new Image(tempTxt8);
        exitImage.setSize(Gdx.graphics.getWidth()/3.8f, Gdx.graphics.getHeight()/7.2f);
        exitImage.setPosition(-Gdx.graphics.getWidth()/3.2f, 0);
        exitImage.addAction(moveBy(Gdx.graphics.getWidth()/3.2f, 0, 0.4f));
        exitImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
                System.exit(0);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                exitImage.setColor(Color.GOLD);
                exitImage.addAction(sizeBy(10f, 10f, 0.3f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                exitImage.setColor(Color.WHITE);
                exitImage.addAction(sizeBy(-10f, -10f, 0.3f));
            }
        });

        stage.addActor(mainArt1);
        stage.addActor(mainPanel);
        stage.addActor(nameGame);
        stage.addActor(mainMenu);
        stage.addActor(playImage);
        stage.addActor(playOnlineImage);
        stage.addActor(settingsImage);
        stage.addActor(exitImage);
    }

    @Override
    public void show() {
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/Hyper.mp3"));
        menuMusic.setVolume(0f);
        menuMusic.setLooping(true);
       // menuMusic.play();
    }

    public void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        stage.draw();
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
        stage.dispose();
        menuMusic.dispose();
        tempTxt1.dispose();
        tempTxt2.dispose();
        tempTxt3.dispose();
        tempTxt4.dispose();
        tempTxt5.dispose();
        tempTxt6.dispose();
        tempTxt7.dispose();
        tempTxt8.dispose();
    }
}