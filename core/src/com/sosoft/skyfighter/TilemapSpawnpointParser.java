package com.sosoft.skyfighter;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;



public class TilemapSpawnpointParser {
    public static void ParseSpawnpoints(Array<Vector2> spawnPoints, TiledMap tiledMap)
    {
        MapObjects mapObjects = tiledMap.getLayers().get("spawnpoint-layer").getObjects();
        for(MapObject mapObject: mapObjects)
            if (mapObject instanceof RectangleMapObject)
                spawnPoints.add(new Vector2(((RectangleMapObject)mapObject).getRectangle().x,((RectangleMapObject)mapObject).getRectangle().y));
    }

}
