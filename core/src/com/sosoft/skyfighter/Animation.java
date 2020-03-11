package com.sosoft.skyfighter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.Texture;

public class Animation {
    private Array<Texture> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int currentFrame;

    public Animation(float cycleTime, int count, String ... args) {
        frameCount = count;
        frames = new Array<Texture>();
        for(int i = 0; i < args.length; i++)
            frames.add(new Texture(args[i]));
        this.maxFrameTime = cycleTime/count;
        currentFrame = 0;
        currentFrameTime = 0;
    }
    public Animation(float cycleTime, int count, Array<Texture> frames) {
        frameCount = count;
        this.frames = frames;
        this.maxFrameTime = cycleTime/count;
        currentFrame = 0;
        currentFrameTime = 0;
    }
    public void Update(float deltaTime) {
        currentFrameTime += deltaTime;
        if(currentFrameTime > maxFrameTime) {
            currentFrame++;
            currentFrameTime = 0f;
        }
        if(currentFrame >= frameCount) {
            currentFrame = 0;
        }
    }
    public Texture getFrame() {
        return frames.get(currentFrame);
    }
}
