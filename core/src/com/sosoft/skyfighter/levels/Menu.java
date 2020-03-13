package com.sosoft.skyfighter.levels;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.sosoft.skyfighter.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
import javafx.scene.text.TextBoundsType;

public class Menu implements Screen  {
    Stage stage;
    TextButton btn_Start;

    Button bt;

    TextButtonStyle btn_StartStyle;
    BitmapFont font;
    Skin skin;
    TextureAtlas ntb_StartAtlas;
    skyFighter mainWindow;

    public Menu (skyFighter mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        font.setColor(new Color(Color.BLUE));
        skin = new Skin();
        btn_StartStyle = new TextButtonStyle();
        btn_StartStyle.font = font;
//        btn_StartStyle.up = skin.getDrawable("1");
//        btn_StartStyle.down = skin.getDrawable("2");
//        btn_StartStyle.checked = skin.getDrawable("Zoom");
        bt = new Button();

        btn_Start = new TextButton("Start", btn_StartStyle);
        btn_Start.setX(300);
        btn_Start.setY(300);
        btn_Start.setSize(300, 400);
        btn_Start.setColor(Color.BLUE);
        btn_Start.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainWindow.setScreen(new Level1());
            }
        });
        stage.addActor(btn_Start);
    }

    @Override
    public void render(float delta) {
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

    }
}
