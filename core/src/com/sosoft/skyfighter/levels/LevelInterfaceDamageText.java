package com.sosoft.skyfighter.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

public class LevelInterfaceDamageText {
    private BitmapFont bFont;
    private java.lang.CharSequence text;
    private float transparency;
    private float gravity = 0.1f;
    private Vector2 momentum = new Vector2((float) (Math.random() - 0.5d) * 3, 3);

    public boolean toDestroy = false;
    public float x;
    public float y;

    public LevelInterfaceDamageText(java.lang.CharSequence msg, Vector2 pos) {
        this.bFont = new BitmapFont();
        this.text = msg;
        this.x = pos.x;
        this.y = pos.y;
        bFont.getData().setScale(1.5f);
    }

    public void update() {
        x += momentum.x;
        y += momentum.y;
        momentum.y -= gravity;
        transparency += 0.01f;
        bFont.setColor(1, 1, 1, 1 - transparency);
        if (transparency >= 1) {
            toDestroy = true;
        }
    }

    public void draw(Batch batch) {
        bFont.draw(batch, text, x, y);
    }

    public void dispose() {
        bFont.dispose();
    }
}
