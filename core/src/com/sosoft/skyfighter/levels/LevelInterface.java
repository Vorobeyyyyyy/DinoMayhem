package com.sosoft.skyfighter.levels;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.sosoft.skyfighter.heroes.Hero;

public class LevelInterface {
    public Level level;
    public Array<LevelInterfaceHealthBar> healthBars;
    public Array<LevelInterfaceDamageText> arrayDamageText;
    public LevelInterfaceEventText levelInterfaceEventText;

    LevelInterface(Level level) {
        this.level = level;
        arrayDamageText = new Array<LevelInterfaceDamageText>();
        healthBars = new Array<LevelInterfaceHealthBar>();
        for (Hero player : level.players) {
            healthBars.add(new LevelInterfaceHealthBar(player));
        }
        levelInterfaceEventText = new LevelInterfaceEventText();
    }
    public void update() {
        for (LevelInterfaceDamageText text : arrayDamageText)
            text.update();
        for (LevelInterfaceHealthBar healthBar : healthBars)
            healthBar.update();
        levelInterfaceEventText.update();
    }

    public void draw(Batch batch) {
        for (LevelInterfaceDamageText text : arrayDamageText)
            text.draw(batch);
        for (LevelInterfaceHealthBar healthBar : healthBars)
            healthBar.draw(batch);
        levelInterfaceEventText.draw();
    }
}