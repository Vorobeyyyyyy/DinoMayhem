package com.sosoft.skyfighter.levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.sosoft.skyfighter.heroes.Hero;

public class LevelInterfaceHealthBar {
    private int height = 10;
    private Texture textureCurHealth;
    private Texture textureMaxHealth;
    private Sprite spriteCurHealth;
    private Sprite spriteMaxHealth;
    private Hero hero;

    LevelInterfaceHealthBar(Hero hero) {
        this.hero = hero;
        textureCurHealth = new Texture("Heroes/CurHealth.png");
        textureMaxHealth = new Texture("Heroes/MaxHealth.png");
        spriteCurHealth = new Sprite(textureCurHealth);
        spriteMaxHealth = new Sprite(textureMaxHealth);
        spriteCurHealth.setPosition(hero.centerPos.x - spriteMaxHealth.getWidth() / 2, hero.pos.y + hero.size.y + height);
        spriteMaxHealth.setPosition(hero.centerPos.x - spriteMaxHealth.getWidth() / 2, hero.pos.y + hero.size.y + height);
    }

    public void update() {
        spriteCurHealth.setPosition(hero.centerPos.x - spriteMaxHealth.getWidth() / 2, hero.pos.y + hero.size.y + height);
        if (hero.state.health >= 0)
            spriteCurHealth.setSize((float) hero.state.health / hero.state.maxHealth * spriteMaxHealth.getWidth(), spriteMaxHealth.getHeight());
        spriteMaxHealth.setPosition(hero.centerPos.x - spriteMaxHealth.getWidth() / 2, hero.pos.y + hero.size.y + height);
    }

    public void draw(Batch batch) {
        spriteCurHealth.draw(batch);
        spriteMaxHealth.draw(batch);
    }

    public void dispose() {
        textureMaxHealth.dispose();
        textureCurHealth.dispose();
    }
}
