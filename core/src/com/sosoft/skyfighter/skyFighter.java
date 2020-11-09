package com.sosoft.skyfighter;

import com.badlogic.gdx.Game;
import com.sosoft.skyfighter.menu.Menu;

public class SkyFighter extends Game {


    @Override
    public void create() {
        setScreen(new Menu(this));
    }
}
