package com.sosoft.skyfighter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import static com.badlogic.gdx.Gdx.files;

public class ContentManager {


    public static Array<JsonValue> getAllMaps() {
        Array<JsonValue> mapDescriptions = new Array<JsonValue>();
        for (FileHandle fileHandle : Gdx.files.internal("Maps").list()) {
            if (fileHandle.extension().equals("json"))
                mapDescriptions.add(new JsonReader().parse(files.internal(fileHandle.path())));
        }
        return mapDescriptions;
    }

    public static Array<JsonValue> getAllHeroes(){
        Array<JsonValue> heroes = new Array<JsonValue>();
        for (FileHandle fileHandle : Gdx.files.internal("Heroes").list()) {
            if (fileHandle.isDirectory()) {
                for (FileHandle subFileHandle : files.internal(fileHandle.path()).list()) {
                    System.out.println(subFileHandle.name());
                    if (subFileHandle.isDirectory() && subFileHandle.name().equals("walk"))
                    {
                        heroes.add(new JsonReader().parse(files.internal(subFileHandle.child("desc.json").path())));
                    }
                }
            }
        }
        return heroes;
    }

}
