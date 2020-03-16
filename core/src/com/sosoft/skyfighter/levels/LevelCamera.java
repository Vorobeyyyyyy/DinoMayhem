package com.sosoft.skyfighter.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.sosoft.skyfighter.heroes.Hero;

import static com.sosoft.skyfighter.levels.Constants.CAMERASLOW;
import static com.sosoft.skyfighter.levels.Constants.MINCAMERAZOOM;

public class LevelCamera extends OrthographicCamera {

    Array<Hero> heroes = new Array<Hero>();
    public int mapWidth;
    public int mapHeight;

    LevelCamera(Array<Hero> heroes, TiledMap tiledMap) {
        this.heroes = heroes;
        mapWidth = tiledMap.getProperties().get("width", Integer.class) * tiledMap.getProperties().get("tilewidth", Integer.class);
        mapHeight = tiledMap.getProperties().get("height", Integer.class) * tiledMap.getProperties().get("tileheight", Integer.class);
    }

    @Override
    public void update() {
        int heroCount = 0;
        float zoomAim, screenWidth = Gdx.graphics.getWidth(), screenHeight = Gdx.graphics.getHeight();

        Vector2 maxPos = new Vector2(heroes.get(0).pos.x, heroes.get(0).pos.y),
                minPos = new Vector2(heroes.get(0).pos.x, heroes.get(0).pos.y),
                posAim = new Vector2(0, 0);
        for (Hero hero : heroes)
            if (!hero.state.dead) {
                if (hero.pos.x < minPos.x)
                    minPos.x = hero.pos.x;
                if (hero.pos.x > maxPos.x)
                    maxPos.x = hero.pos.x;
                if (hero.pos.y < minPos.y)
                    minPos.y = hero.pos.y;
                if (hero.pos.y > maxPos.y)
                    maxPos.y = hero.pos.y;
                posAim.x += hero.pos.x;
                posAim.y += hero.pos.y;
                heroCount++;
            }
        if (heroCount != 0) {
            posAim.x /= heroCount;
            posAim.y /= heroCount;
        } else {
            posAim.x = mapWidth / 2f;
            posAim.y = mapHeight / 2f;
            maxPos.x = mapHeight;
            maxPos.y = mapWidth;
            minPos.x = 0;
            minPos.y = 0;
        }


        viewportHeight = screenHeight;
        viewportWidth = screenWidth;

        if ((maxPos.x - minPos.x) / screenWidth / screenHeight > maxPos.y - minPos.y)
            zoomAim = (maxPos.x - minPos.x) / viewportWidth;
        else
            zoomAim = (maxPos.y - minPos.y) / viewportHeight;

        if (zoomAim < MINCAMERAZOOM)
            zoomAim = MINCAMERAZOOM;

        zoom -= (zoom - (zoomAim + 0.2f)) / CAMERASLOW;
        position.x -= (position.x - posAim.x) / CAMERASLOW;
        position.y -= (position.y - posAim.y) / CAMERASLOW;

        //position.set(posAim.x, posAim.y, 0);


        super.update();
    }

}
