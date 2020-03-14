package com.sosoft.skyfighter.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sosoft.skyfighter.skyFighter;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

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

    private Music menuMusic;

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
        mainMenu.setSize(400, 280);
        mainMenu.setPosition(-450, Gdx.graphics.getHeight() - mainMenu.getHeight() / 1.2f);

        mainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuMusic.pause();
                app.setScreen(new Level(app, "Tilemaps/Map1.tmx", true, Controllers.getControllers()));
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                mainMenu.setColor(Color.GOLD);
                mainMenu.addAction(parallel(moveBy(50, 0, 0.2f)));
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                mainMenu.setColor(Color.WHITE);
                mainMenu.addAction(parallel(moveBy(-50, 0, 0.2f)));
            }
        });

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


        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/Hyper - Spoiler.mp3"));
        menuMusic.setVolume(0.0f);
        menuMusic.setLooping(true);
        menuMusic.play();


        mainPanel.addAction(alpha(0.7f));
        mainPanel.addAction(parallel(moveBy(500, 0, 0.4f)));

        nameGame.addAction(sequence(alpha(0), scaleTo(.1f, .1f),
                parallel(fadeIn(1f, Interpolation.pow2),
                        scaleTo(2f, 2f, 1.5f, Interpolation.swing), moveTo(stage.getWidth() * 0.65f, stage.getHeight() * 0.8f))));

        mainMenu.addAction(sequence(alpha(0), fadeIn(0.3f),
                parallel(moveBy(500, 0, 0.2f))));
    }

    public void update(float delta) {
        stage.act(delta);
    }

    private void initButtons() {
        font = new BitmapFont();
        font.setColor(Color.BLUE);
        skin = new Skin();
        btn_StartStyle = new TextButtonStyle();
        btn_StartStyle.font = font;
        playButton = new TextButton("Play", btn_StartStyle);
        playButton.setPosition(0, Gdx.graphics.getHeight() - 300);
        playButton.setSize(500, 200);
        playButton.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
//        playButton.addListener(new FocusListener() {
//            @Override
//            public boolean handle(Event event) {
//                playButton.addAction(parallel(moveBy(100, 0, 0.2f)));
//                return super.handle(event);
//            }
//        });
//        playButton.addListener(new ClickListener() {
//            @Override
//            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
//                playButton.setColor(Color.GOLD);
//                playButton.addAction(parallel(moveBy(50, 0, 0.2f)));
//            }
//            @Override
//            public void exit ( InputEvent event, float x, float y, int pointer, Actor toActor) {
//                playButton.addAction(parallel(moveBy(-50, 0, 0.2f)));
//            }
//        });
//        playButton.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                menuMusic.pause();
//                app.setScreen(new Level(app,"Tilemaps/Map1.tmx",true, Controllers.getControllers()));
//            }
//        });

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