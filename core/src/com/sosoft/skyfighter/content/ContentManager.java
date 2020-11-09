package com.sosoft.skyfighter.content;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.sosoft.skyfighter.heroes.heroes.Dino;
import com.sosoft.skyfighter.heroes.heroes.Imposter;

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

    public static Array<HeroContent> getAllHeroes(){
        Array<HeroContent> heroes = new Array<HeroContent>();
        heroes.add(new HeroContent("Dino",new JsonReader().parse(files.internal(Dino.WALK_PATH))));
        heroes.add(new HeroContent("Imposter",new JsonReader().parse(files.internal(Imposter.WALK_PATH))));
        return heroes;
    }

}
