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
        float zoomAim, screenWidth = Gdx.graphics.getWidth(), screenHeight = Gdx.graphics.getHeight();

        Vector2 maxPos = new Vector2(Float.MIN_VALUE, Float.MIN_VALUE),
                minPos = new Vector2(Float.MAX_VALUE, Float.MAX_VALUE),
                posAim = new Vector2(0, 0);
        for (Hero hero : heroes)
            if (!hero.state.dead) {
                if (hero.centerPos.x < minPos.x)
                    minPos.x = hero.centerPos.x;
                if (hero.centerPos.x > maxPos.x)
                    maxPos.x = hero.centerPos.x;
                if (hero.centerPos.y < minPos.y)
                    minPos.y = hero.centerPos.y;
                if (hero.centerPos.y > maxPos.y)
                    maxPos.y = hero.centerPos.y;
            }
        if (heroes.size != 0) {
            //posAim.x /= heroCount;
            //posAim.y /= heroCount;
            posAim.x = (maxPos.x + minPos.x) / 2;
            posAim.y = (maxPos.y + minPos.y) / 2;
        } else {
            posAim.x = mapWidth / 2f;
            posAim.y = mapHeight / 2f;
            maxPos.x = mapWidth;
            maxPos.y = mapHeight;
            minPos.x = 0;
            minPos.y = 0;
        }


        viewportHeight = screenHeight;
        viewportWidth = screenWidth;

        if ((maxPos.x - minPos.x) / screenWidth  > (maxPos.y - minPos.y) / screenHeight)
            zoomAim = (maxPos.x - minPos.x) / viewportWidth;
        else
            zoomAim = (maxPos.y - minPos.y) / viewportHeight;

        if (zoomAim < MINCAMERAZOOM)
            zoomAim = MINCAMERAZOOM;

        zoom -= (zoom - (zoomAim + 0.2f)) / CAMERASLOW;
        position.x -= (position.x - posAim.x) / CAMERASLOW;
        position.y -= (position.y - posAim.y) / CAMERASLOW;



        super.update();
    }

}
