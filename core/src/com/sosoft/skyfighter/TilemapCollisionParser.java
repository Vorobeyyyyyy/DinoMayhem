package com.sosoft.skyfighter;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Arrays;

public class TilemapCollisionParser {

    public static void parseCollisionLayer(World world, MapObjects mapObjects) {
        for (MapObject mapObject : mapObjects)
            if (mapObject instanceof PolygonMapObject)
                parsePolygonMapObject(world, (PolygonMapObject) mapObject);
    }

    private static void parsePolygonMapObject(World world, PolygonMapObject polygonMapObject) {
        float[] vertices = polygonMapObject.getPolygon().getTransformedVertices();
        vertices = Arrays.copyOf(vertices,vertices.length+2);
        vertices[vertices.length-2] = vertices[0];
        vertices[vertices.length-1] = vertices[1];

        ChainShape chainShape = new ChainShape();
        chainShape.createChain(vertices);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bodyDef);
        body.createFixture(chainShape, 1f);
    }
}
