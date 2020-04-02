package com.sosoft.skyfighter;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

public class Animation {
    HashMap<String, AnimatedSprite> animations;
    AnimatedSprite currentAnimation;
    public boolean flipX = false, flipY = false, reverse = false;

    public Animation() {
        animations = new HashMap<String, AnimatedSprite>();
    }

    public void addAnimation(String name, AnimatedSprite animation) {
        animations.put(name, animation);
    }

    public void setAnimation(String name) {
        currentAnimation = animations.get(name);
    }

    public void update(float delta) {
        currentAnimation.update(delta);
    }

    public void draw(Batch batch, Vector2 pos) {
        currentAnimation.draw(batch, pos, flipX, flipY, reverse);
    }
}
