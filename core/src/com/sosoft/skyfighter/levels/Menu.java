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
    Texture tempTxt3;
    Texture tempTxt5;
    Texture tempTxt7;
    Texture tempTxt8;

    private Image mainArt1;
    private Image nameGame;

    private Image settingsImage;
    private Image playImage;
    private Image exitImage;

    private Music menuMusic;


    public Menu(final skyFighter mainWindow) {
        app = mainWindow;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        tempTxt1 = new Texture("Menu\\Desert1.jpg");
        mainArt1 = new Image(tempTxt1);
        mainArt1.setPosition(0, 0);
        mainArt1.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        tempTxt3 = new Texture("Menu\\DinoMayhem.png");
        nameGame = new Image(tempTxt3);
        nameGame.setSize(Gdx.graphics.getWidth()/2.8f, Gdx.graphics.getHeight()/5.2f);
        nameGame.setPosition(Gdx.graphics.getWidth()/2 - nameGame.getWidth()/2, Gdx.graphics.getHeight() - nameGame.getHeight());

        tempTxt5 = new Texture("Menu\\PlayImage.png");
        playImage = new Image(tempTxt5);
        playImage.setSize(Gdx.graphics.getWidth()/3.8f, Gdx.graphics.getHeight()/7.2f);
        playImage.setPosition(Gdx.graphics.getWidth()/2 - playImage.getWidth()/2, Gdx.graphics.getHeight() - nameGame.getHeight() - playImage.getHeight());
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

        tempTxt7 = new Texture("Menu\\SettingsImage.png");
        settingsImage = new Image(tempTxt7);
        settingsImage.setSize(Gdx.graphics.getWidth()/3.8f, Gdx.graphics.getHeight()/7.2f);
        settingsImage.setPosition(Gdx.graphics.getWidth()/2 - settingsImage.getWidth()/2, Gdx.graphics.getHeight() - nameGame.getHeight() - playImage.getHeight() - settingsImage.getHeight());
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
        exitImage.setPosition(Gdx.graphics.getWidth()/2 - exitImage.getWidth()/2, Gdx.graphics.getHeight() - nameGame.getHeight() - playImage.getHeight() - settingsImage.getHeight() - exitImage.getHeight());
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
        stage.addActor(nameGame);
        stage.addActor(playImage);
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
        tempTxt3.dispose();
        tempTxt5.dispose();
        tempTxt7.dispose();
        tempTxt8.dispose();
    }
}