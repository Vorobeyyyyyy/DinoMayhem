package com.sosoft.skyfighter;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

public class Animation {
    HashMap<String, AnimatedSprite> animations;
    public AnimatedSprite currentAnimation;
    AnimatedSprite previousAnimation;
    boolean looped = true;
    public float direction = 0;
    public Vector2 pos = new Vector2();
    public boolean flipX = false, flipY = false, reverse = false;

    public Animation() {
        animations = new HashMap<String, AnimatedSprite>();
    }

    public void addAnimation(String name, AnimatedSprite animation) {
        animations.put(name, animation);
    }

    public void setAnimation(String name) {
        looped = true;
        currentAnimation = animations.get(name);
    }

    public void playAmination(String name) {
        looped = false;
        previousAnimation = currentAnimation;
        currentAnimation = animations.get(name);
        currentAnimation.currentTime = 0f;
    }

    public void update(float delta) {
        float tempCurrentTime = currentAnimation.currentTime;
        currentAnimation.update(delta);
        if (!looped && tempCurrentTime > currentAnimation.currentTime) {
            currentAnimation = previousAnimation;
            looped = true;
        }
    }

    public void draw(Batch batch) {
        currentAnimation.draw(batch, pos, flipX, flipY, reverse, direction);
    }
}
