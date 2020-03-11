package com.sosoft.skyfighter;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MySpriteBatch extends SpriteBatch {
    public void draw(Player a) {
        a.sprite.setRotation(55f);
        super.draw(a.sprite, a.body.getPosition().x -256, a.body.getPosition().y - 256);
    }

}
