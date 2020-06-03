package com.sosoft.skyfighter.levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class LevelBackground {
    Texture topTexture;
    Texture bottomTexture;
    Array<Texture> textures;
    Array<TextureRegion> layers;
    Array<Integer> speeds;
    Array<Float> positions;
    LevelDrawer levelDrawer;
    int copyCount;
    float scale;
    float width;

    public LevelBackground(LevelDrawer levelDrawer, String path, Array<Integer> speed) {
        textures = new Array<Texture>();
        positions = new Array<Float>();
        layers = new Array<TextureRegion>();
        for (int i = 0; i < speed.size; i++)
            textures.add(new Texture(path + String.valueOf(i) + ".png"));

        this.speeds = speed;
        this.levelDrawer = levelDrawer;

        scale = (float) levelDrawer.camera.mapHeight / textures.first().getHeight();
        width = textures.first().getWidth() * scale;
        copyCount = (int)Math.ceil(levelDrawer.camera.mapWidth / (textures.first().getWidth() * scale)) * 2;

        for (int i = 0; i < speed.size; i++)
            positions.add(-width * copyCount / 4);

        for (Texture texture : textures) {
            texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
            layers.add(new TextureRegion(texture,0,0, texture.getWidth() * copyCount,texture.getHeight()));
        }

        topTexture = new Texture(path + "top.png");
        bottomTexture = new Texture(path + "bot.png");
    }

    public void update(float delta) {
        for (int i = 0; i < speeds.size; i++) {
            positions.set(i, positions.get(i) + speeds.get(i) * delta);
            if (positions.get(i) >= -width * copyCount / 4 && speeds.get(i) > 0)
                positions.set(i, -width * copyCount / 2);
            if (positions.get(i) <= -width * copyCount / 2 && speeds.get(i) < 0)
                positions.set(i, -width * copyCount / 4);
        }
    }

    public void draw(Batch batch) {
        for (int i = 0; i < speeds.size; i++)
            batch.draw(layers.get(i), positions.get(i), 0, layers.get(i).getRegionWidth() * scale, layers.get(i).getRegionHeight() * scale);
        batch.draw(topTexture, -2 *levelDrawer.camera.mapWidth, levelDrawer.camera.mapHeight, 5 * levelDrawer.camera.mapWidth, levelDrawer.camera.mapHeight);
        batch.draw(bottomTexture, -2 * levelDrawer.camera.mapWidth, -levelDrawer.camera.mapHeight, 5 * levelDrawer.camera.mapWidth, levelDrawer.camera.mapHeight);
    }

    public void dispose() {
        for (Texture texture : textures)
            texture.dispose();
        topTexture.dispose();
        bottomTexture.dispose();
    }
}
