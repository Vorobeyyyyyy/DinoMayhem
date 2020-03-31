package com.sosoft.skyfighter.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import sun.jvm.hotspot.HelloWorld;

import java.util.Objects;

public class LevelInterface {
    //private static BitmapFont bFont = new BitmapFont();
    private static SpriteBatch spriteBatchHandle;
    private static OrthographicCamera orthographicCameraCam;
    private static Array<LevelInterfaceDamageText> arrayDamageText = new Array<LevelInterfaceDamageText>();

    public void update() {
        for(LevelInterfaceDamageText text : arrayDamageText)
            text.update();
    };

    public void draw(Batch batch) {
        for(LevelInterfaceDamageText text : arrayDamageText)
            text.draw(batch);
    }
//    private static Array<Object> updateObjects = new Array<Object>();
//    private static Array<Object> destroyObjects = new Array<Object>();

//    public static void addToUpdate(Object object) {
//        objects.add(object);
//        updateObjects.add(object);
//    };
//
//    public static void setToDestroy(Object object) {
//        objects.removeValue(object, true);
//        updateObjects.removeValue(object, true);
//        destroyObjects.add(object);
//    }
//
//    public static void update(World world) {
//        for(Object object : destroyObjects) {
//        }
//
//    }
//
//    public static void draw(Batch batch) {
//        for(Object object : objects) {
//            object.draw(batch);
//        }
//
//    }
}

