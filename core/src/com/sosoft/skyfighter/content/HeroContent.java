package com.sosoft.skyfighter.content;

import com.badlogic.gdx.utils.JsonValue;

public class HeroContent {
    public String name;
    public JsonValue runAnimation;

    public HeroContent(String name, JsonValue runAnimation)
    {
        this.name = name;
        this.runAnimation = runAnimation;
    }

}
