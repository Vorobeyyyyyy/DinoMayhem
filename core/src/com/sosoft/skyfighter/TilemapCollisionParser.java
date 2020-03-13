package com.sosoft.skyfighter;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.sosoft.skyfighter.levels.Constants.PPM;

import java.util.Arrays;


public class TilemapCollisionParser {

    public static void parseCollisionLayer(World world, MapObjects mapObjects) {
        for (MapObject mapObject : mapObjects)
            if (mapObject instanceof PolygonMapObject)
                parsePolygonMapObject(world, (PolygonMapObject) mapObject);
            else if (mapObject instanceof EllipseMapObject)
                parseEllipseMapObject(world, (EllipseMapObject) mapObject);
            else if (mapObject instanceof PolylineMapObject)
                parsePolylineMapObject(world, (PolylineMapObject) mapObject);


    }

    private static void parsePolygonMapObject(World world, PolygonMapObject polygonMapObject) {
        float[] vertices = polygonMapObject.getPolygon().getTransformedVertices();
        vertices = Arrays.copyOf(vertices, vertices.length + 2);
        vertices[vertices.length - 2] = vertices[0];
        vertices[vertices.length - 1] = vertices[1];
        for (int i = 0; i < vertices.length; i++)
            vertices[i] /= PPM;

        ChainShape chainShape = new ChainShape();
        chainShape.createChain(vertices);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bodyDef);
        body.createFixture(chainShape, 1f);
    }

    private static void parseEllipseMapObject(World world, EllipseMapObject ellipseMapObject) {
        //ellipseMapObject.getEllipse().
        CircleShape circleShape = new CircleShape();

    }

    private static void parsePolylineMapObject(World world, PolylineMapObject polylineMapObject) {
        float[] vertices = polylineMapObject.getPolyline().getTransformedVertices();
        for (int i = 0; i < vertices.length; i++)
            vertices[i] /= PPM;
        ChainShape chainShape = new ChainShape();
        chainShape.createChain(vertices);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bodyDef);
        body.createFixture(chainShape, 1f);
    }
}
