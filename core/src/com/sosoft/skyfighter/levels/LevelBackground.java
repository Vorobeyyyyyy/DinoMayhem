package com.sosoft.skyfighter.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class LevelBackground {
    int layerCount;
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

    JsonValue desc;

    public LevelBackground(LevelDrawer levelDrawer, String path) {
        textures = new Array<Texture>();
        positions = new Array<Float>();
        layers = new Array<TextureRegion>();
        speeds = new Array<Integer>();

        desc = new JsonReader().parse(Gdx.files.internal(path + "/desc.json"));

        layerCount = desc.getInt("layerCount");
        for (String layerName : desc.get("layers").asStringArray())
            textures.add(new Texture(path + '/' + layerName));
        for (int speed : desc.get("speeds").asIntArray())
            speeds.add(speed);

        this.levelDrawer = levelDrawer;

        scale = (float) levelDrawer.camera.mapHeight / textures.first().getHeight();
        width = textures.first().getWidth() * scale;
        copyCount = (int)Math.ceil(levelDrawer.camera.mapWidth / (textures.first().getWidth() * scale)) * 2;

        for (int i = 0; i < layerCount; i++)
            positions.add(-width * copyCount / 4);

        for (Texture texture : textures) {
            texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
            layers.add(new TextureRegion(texture,0,0, texture.getWidth() * copyCount,texture.getHeight()));
        }

        topTexture = new Texture(path + "/top.png");
        bottomTexture = new Texture(path + "/bot.png");
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
