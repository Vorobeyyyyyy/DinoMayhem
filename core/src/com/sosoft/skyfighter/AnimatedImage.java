package com.sosoft.skyfighter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class AnimatedImage extends Image {
    Array<SpriteDrawable> frames;
    Array<Texture> textures;
    int frameCount;
    float duration;
    JsonValue description;

    int currentFrame;
    float currentTime;
    float frameTime;

    public AnimatedImage(JsonValue description)
    {
        frames = new Array<SpriteDrawable>();
        textures = new Array<Texture>();
        currentFrame = 0;


        frameCount = description.getInt("frameCount");
        duration = description.getFloat("duration");
        frameTime = duration / frameCount;

        for (String texturePath : description.get("frames").asStringArray()) {
            Texture texture = new Texture(texturePath);
            textures.add(texture);
            frames.add(new SpriteDrawable(new Sprite(texture)));
        }

        super.setDrawable(frames.get(0));
    }

    public AnimatedImage(String jsonPath) {
        this(new JsonReader().parse(Gdx.files.internal(jsonPath)));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        currentTime += delta;
        if (currentTime >= frameTime) {
            currentTime -= frameTime;
            if (++currentFrame >= frameCount)
                currentFrame = 0;
            super.setDrawable(frames.get(currentFrame));
        }
    }

    void dispose()
    {

    }
}
