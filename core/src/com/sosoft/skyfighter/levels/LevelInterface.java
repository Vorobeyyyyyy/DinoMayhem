package com.sosoft.skyfighter.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.sosoft.skyfighter.heroes.Hero;
import sun.jvm.hotspot.HelloWorld;

import java.util.Objects;

public class LevelInterface {
    public Level level;
    public LevelInterfaceTeams levelInterfaceTeams;
    public Array<LevelInterfaceHealthBar> healthBars;
    public Array<LevelInterfaceDamageText> arrayDamageText;

    LevelInterface(Level level) {
        this.level = level;
        arrayDamageText = new Array<LevelInterfaceDamageText>();
        healthBars = new Array<LevelInterfaceHealthBar>();
        levelInterfaceTeams = new LevelInterfaceTeams(5);
        for (Hero player : level.players) {
            healthBars.add(new LevelInterfaceHealthBar(player));
        }
    }

    public void update() {
        for (LevelInterfaceDamageText text : arrayDamageText)
            text.update();
        for (LevelInterfaceHealthBar healthBar : healthBars)
            healthBar.update();
    }

    public void draw(Batch batch) {
        for (LevelInterfaceDamageText text : arrayDamageText)
            text.draw(batch);
        for (LevelInterfaceHealthBar healthBar : healthBars)
            healthBar.draw(batch);
        levelInterfaceTeams.draw();
    }
}

