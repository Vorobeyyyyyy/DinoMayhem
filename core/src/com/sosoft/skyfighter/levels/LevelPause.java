package com.sosoft.skyfighter.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sosoft.skyfighter.skyFighter;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class LevelPause {
    private final skyFighter app;
    public SpriteBatch batch;
    private Stage stage;

    Texture tempTxt1;
    Texture tempTxt2;
    Texture tempTxt3;
    Texture tempTxt4;
    Texture tempTxt5;
    Texture tempTxt6;

    private Image mainArt1Pause;
    private Image mainPanelPause;
    private Image nameGame;
    private Image mainMenu;
    private Image pauseMenu;

    private Image continueImage;
    private Image settingsImage;
    private Image exitImageMenu;

    private Music menuMusic;

    private TextButton playButton;
    private TextButton exitButton;

    public LevelPause(final skyFighter mainWindow) {

        app = mainWindow;
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        tempTxt1 = new Texture("Menu\\BlackRec.png");
        mainArt1Pause = new Image(tempTxt1);
        mainArt1Pause.setPosition(0, 0);
        mainArt1Pause.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mainArt1Pause.addAction(alpha(0.75f));

        tempTxt2 = new Texture("Menu\\MainPanelPause.png");
        mainPanelPause = new Image(tempTxt2);
        mainPanelPause.setPosition(Gdx.graphics.getWidth() / 2 - 300, 100);
        mainPanelPause.setSize(600, Gdx.graphics.getHeight() - 200);
        mainPanelPause.addAction(alpha(0.5f));

        tempTxt3 = new Texture("Menu\\PauseMenu.png");
        pauseMenu = new Image(tempTxt3);
        pauseMenu.setSize(600, 300);
        pauseMenu.setPosition(Gdx.graphics.getWidth() / 2 - pauseMenu.getWidth() / 2, Gdx.graphics.getHeight() - pauseMenu.getHeight() - 75);

        tempTxt4 = new Texture("Menu\\ContinueImage.png");
        continueImage = new Image(tempTxt4);
        continueImage.setSize(400, 150);
        continueImage.setPosition(Gdx.graphics.getWidth() / 2 - continueImage.getWidth() / 2, Gdx.graphics.getHeight() - 2 * continueImage.getHeight() - 100);

        tempTxt5 = new Texture("Menu\\SettingsImage.png");
        settingsImage = new Image(tempTxt5);
        settingsImage.setSize(400, 150);
        settingsImage.setPosition(Gdx.graphics.getWidth() / 2 - settingsImage.getWidth() / 2, Gdx.graphics.getHeight() - 3 * settingsImage.getHeight() - 100);


        tempTxt6 = new Texture("Menu\\ExitToTheMenuImage.png");
        exitImageMenu = new Image(tempTxt6);
        exitImageMenu.setSize(400, 150);
        exitImageMenu.setPosition(Gdx.graphics.getWidth() / 2 - exitImageMenu.getWidth() / 2, Gdx.graphics.getHeight() - 4 * exitImageMenu.getHeight() - 100);

        exitImageMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(new Menu(app));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                exitImageMenu.setColor(Color.GOLD);
                exitImageMenu.addAction(parallel(moveBy(50, 0, 0.2f)));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                exitImageMenu.addAction(parallel(moveBy(-50, 0, 0.2f)));
            }
        });


        stage.addActor(mainArt1Pause);
        stage.addActor(mainPanelPause);
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
        tempTxt2.dispose();
        tempTxt3.dispose();
        tempTxt4.dispose();
        tempTxt5.dispose();
        tempTxt6.dispose();
    }
}
