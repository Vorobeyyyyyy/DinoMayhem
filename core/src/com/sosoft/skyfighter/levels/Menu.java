package com.sosoft.skyfighter.levels;

import java.awt.*;
import java.util.Random;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sosoft.skyfighter.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.Screen;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.sosoft.skyfighter.GameInput;
import com.sosoft.skyfighter.TilemapCollisionParser;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextBoundsType;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class Menu implements Screen {
    private final skyFighter app;
    public SpriteBatch batch;
    private Stage stage;

    private ShapeRenderer shapeRenderer;
    private float progress;

    private Image mainArt1;
    private Image mainPanel;
    private Image nameGame;
    private Image mainMenu;

    private TextButton playButton;
    private TextButton exitButton;

    TextButton btn_Start;

    Button bt;

    TextButtonStyle btn_StartStyle;
    BitmapFont font;
    Skin skin;
    TextureAtlas ntb_StartAtlas;


    public Menu(final skyFighter mainWindow) {
        app = mainWindow;
        progress = 0f;
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        //Random rnd = new Random(System.currentTimeMillis());
        //Texture tempTxt1 = new Texture("Menu\\MainArt" + String.valueOf(1 + rnd.nextInt(3)) + ".jpg");
        Texture tempTxt1 = new Texture("Menu\\MainArt5.jpg");
        mainArt1 = new Image(tempTxt1);
        mainArt1.setPosition(0, 0);
        mainArt1.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Texture tempTxt2 = new Texture("Menu\\BlackRec.png");
        mainPanel = new Image(tempTxt2);
        mainPanel.setPosition(-500, 0);
        mainPanel.setSize(500, Gdx.graphics.getHeight());

        Texture tempTxt3 = new Texture("Menu\\NameGame.png");
        nameGame = new Image(tempTxt3);
        nameGame.setSize(400, 300);
        nameGame.setOrigin(nameGame.getWidth() * 0.7f, nameGame.getHeight());

        Texture tempTxt4 = new Texture("Menu\\MainMenu.png");
        mainMenu = new Image(tempTxt4);
        mainMenu.setSize(500, 350);
        mainMenu.setPosition(-500, Gdx.graphics.getHeight() - mainMenu.getHeight() / 1.2f);

        stage.addActor(mainArt1);
        stage.addActor(mainPanel);
        stage.addActor(nameGame);
        stage.addActor(mainMenu);
    }

    @Override
    public void show() {
        initButtons();
//        font = new BitmapFont();
//        font.setColor(new Color(Color.BLUE));
//        skin = new Skin();
//        btn_StartStyle = new TextButtonStyle();
//        btn_StartStyle.font = font;
//        btn_StartStyle.up = skin.getDrawable("1");
//        btn_StartStyle.down = skin.getDrawable("2");
//        btn_StartStyle.checked = skin.getDrawable("Zoom");

        mainPanel.addAction(alpha(0.7f));
        mainPanel.addAction(parallel(moveBy(500, 0, 0.4f)));

        nameGame.addAction(sequence(alpha(0), scaleTo(.1f, .1f),
                parallel(fadeIn(2f, Interpolation.pow2),
                        scaleTo(2f, 2f, 1.5f, Interpolation.pow5), moveTo(stage.getWidth() * 0.65f, stage.getHeight() * 0.8f))));

        mainMenu.addAction(sequence(alpha(0), fadeIn(0.3f),
                parallel(moveBy(500, 0, 0.2f))));


    }

    public void update(float delta) {
        stage.act(delta);
    }

    private void initButtons() {
        font = new BitmapFont();
        font.setColor(new Color(Color.BLUE));
        skin = new Skin();
        btn_StartStyle = new TextButtonStyle();
        btn_StartStyle.font = font;
        playButton = new TextButton("Play", btn_StartStyle);
        playButton.setPosition(0, Gdx.graphics.getHeight() - 300);
        playButton.setSize(500, 200);
        playButton.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                app.setScreen(new Level("Tilemaps/Map1.tmx",true, Controllers.getControllers()));
            }
        });

        exitButton = new TextButton("Exit", btn_StartStyle);
        exitButton.setPosition(0, 0);
        exitButton.setSize(500, 200);
        exitButton.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        stage.addActor(playButton);
        stage.addActor(exitButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        stage.draw();

        batch.begin();
        batch.end();
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

        stage.dispose();
    }
}