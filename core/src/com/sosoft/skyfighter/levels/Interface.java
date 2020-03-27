package com.sosoft.skyfighter.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Interface {
    //private static BitmapFont bFont = new BitmapFont();
    private static SpriteBatch spriteBatchHandle;
    private static OrthographicCamera orthographicCameraCam;

    public class DamageText {
        private BitmapFont bFont;
        private java.lang.CharSequence text;
        private float x;
        private float y;
        private float transparency;
        private float gravity = 0.1f;
        private Vector2 momentum = new Vector2((float)(Math.random() - 0.5d) * 4, 3);

        public DamageText(java.lang.CharSequence msg, float x, float y) {
            this.bFont = new BitmapFont(Gdx.files.internal("Interface/damage.fnt"));
            this.text = msg;
            this.x = x;
            this.y = y;

            //EntityManager.addToUpdate(this);
        }

        public void update() {
            x += momentum.x;
            y += momentum.y;

            momentum.y -= gravity;

            transparency += 0.015f;
            bFont.setColor(1, 1, 1, 1 - transparency);

            if(transparency >= 1) {
                //EntityManager.setToDestroy(this);
            }
        }

        public void draw(Batch batch) {
            bFont.draw(batch, text, x, y);
        }

    }

}