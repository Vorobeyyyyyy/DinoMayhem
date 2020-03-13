package com.sosoft.skyfighter.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.sosoft.skyfighter.heroes.Hero;

import java.util.ArrayList;

public class LevelCamera extends OrthographicCamera {

    ArrayList<Hero> heroes = new ArrayList<Hero>();
    float cameraSlow = 3f;

    LevelCamera(ArrayList<Hero> heroes_) {
        heroes = heroes_;
    }

    @Override
    public void update() {
        float x = 0, y = 0;
        Vector2 maxPos = new Vector2(0, 0);
        Vector2 minPos = new Vector2(0, 0);
        for (Hero hero : heroes) {
            if (hero.pos.x < minPos.x)
                minPos.x = hero.pos.x;
            if (hero.pos.x > maxPos.x)
                maxPos.x = hero.pos.x;
            if (hero.pos.y < minPos.y)
                minPos.y = hero.pos.y;
            if (hero.pos.y > maxPos.y)
                maxPos.y = hero.pos.y;
            x += hero.pos.x;
            y += hero.pos.y;
        }
        x /= heroes.size();
        y /= heroes.size();

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        viewportHeight = screenHeight;
        viewportWidth = screenWidth;
        float zoomAim;

        if ((maxPos.x - minPos.x) / screenWidth / screenHeight > maxPos.y - minPos.y)
            zoomAim = (maxPos.x - minPos.x) / viewportWidth;
        else
            zoomAim = (maxPos.y - minPos.y) / viewportHeight;


        if (zoomAim < 1f)
            zoomAim = 1f;
        zoom -= (zoom - (zoomAim + 0.2f)) / cameraSlow;

        position.set(x, y, 0);


        super.update();
    }

}
