package com.sosoft.skyfighter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class AnimatedSprite {
    float duration;
    float currentTime;
    int frameCount;
    int currentFrame;
    Array<Texture> textureArray;
    Array<Sprite> spriteArray;
    boolean isReflected;
    float direction;

    public AnimatedSprite(String baseName, int frameCount, float duration, float scale) {
        textureArray = new Array<Texture>();
        spriteArray = new Array<Sprite>();
        for (int i = 0; i < frameCount; i++)
            textureArray.add(new Texture(baseName + String.valueOf(i) + ".png"));
        for (Texture texture : textureArray)
            spriteArray.add(new Sprite(texture));
        for (Sprite sprite : spriteArray) {
            sprite.setOriginCenter();
            sprite.setSize(sprite.getWidth() * scale, sprite.getHeight() * scale);
        }
        this.frameCount = frameCount;
        this.duration = duration;
        currentTime = 0;
        isReflected = false;
        direction = 0;
    }

    public void update(float delta) {
        currentTime += delta;
        if (currentTime > duration)
            currentTime = 0;
        currentFrame = (int) (currentTime / (duration / frameCount));
    }

    public void draw(Batch batch, Vector2 pos) {
        Sprite currentSprite = spriteArray.get(currentFrame);
        currentSprite.setRotation(direction);
        currentSprite.setFlip(false, isReflected);
        currentSprite.setPosition(pos.x, pos.y);
        currentSprite.draw(batch);
    }

}
