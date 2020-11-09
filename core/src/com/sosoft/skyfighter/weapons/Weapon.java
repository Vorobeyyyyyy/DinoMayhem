package com.sosoft.skyfighter.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.sosoft.skyfighter.AnimatedSprite;
import com.sosoft.skyfighter.Animation;
import com.sosoft.skyfighter.heroes.Hero;

public class Weapon {
    // Characteristics
    public String name;
    int maxAmmo;
    int shotsPerSec;
    float accuracy;
    float reloadTime;

    // Stats
    BulletDef bulletDef;
    float cooldown;
    int currentAmmo;
    Hero hero;
    public Animation animation;
    Vector2 posOffset;
    public float direction;

    //Sounds
    Sound fireSound;
    Sound reloadSound;
    Sound takeSound;

    //Other
    boolean reloading;

    Weapon (Hero h)
    {
        animation = new Animation();
        this.hero = hero;
    }

    public Weapon(Hero hero, String name) {
        animation = new Animation();
        this.hero = hero;

        JsonValue description = new JsonReader().parse(Gdx.files.internal("Weapons/" + name + "/desc.json"));
        init(description);
        reloading = false;
    }


    public void update(float delta) {
        if (cooldown > 0)
            cooldown -= delta;
        else
            cooldown = 0f;
        if (currentAmmo <= 0 && !reloading)
            reload();
        animation.update(delta);
        animation.pos.x -= animation.currentAnimation.size.x / 2;
        animation.pos.y -= animation.currentAnimation.size.y / 2;
    }

    public void fire() {
        if (cooldown <= 0) {
            cooldown = 1f / (float) shotsPerSec;
            currentAmmo -= 1;
            animation.playAmination("fire");
            fireSound.play(0.02f);
            hero.levelController.level.bullets.add(new Bullet(this, bulletDef));
        }
    }

    public void draw(Batch batch) {
        animation.draw(batch);
    }

    void initBullet() {
        bulletDef = new BulletDef();

    }
    public void init(){
        initBullet();
    };

    public void init(JsonValue description) {
        initBullet(description);
        animation.addAnimation("idle", new AnimatedSprite(description.getString("idleAnimation")));
        animation.addAnimation("fire", new AnimatedSprite(description.getString("fireAnimation")));
        animation.setAnimation("idle");

        fireSound = Gdx.audio.newSound(Gdx.files.internal(description.getString("fireSound")));
        takeSound = Gdx.audio.newSound(Gdx.files.internal(description.getString("takeSound")));

        name = description.getString("name");
        maxAmmo = description.getInt("maxAmmo");
        shotsPerSec = description.getInt("shotsPerSec");
        reloadTime = description.getFloat("reloadTime");
        posOffset = new Vector2(description.getFloat("xOffset"), description.getFloat("yOffset"));

        currentAmmo = maxAmmo;
    }

    public void initBullet(JsonValue description) {
        bulletDef = new BulletDef();
        bulletDef.damage = description.getInt("damage");
        bulletDef.speed = description.getFloat("bulletSpeed");
        bulletDef.maxDistance = description.getFloat("maxDistance");
        bulletDef.maxTimeAlive = description.getFloat("maxTimeAlive");
        bulletDef.canBounce = description.getBoolean("canBounce");
        bulletDef.accuracy = description.getFloat("accuracy");
        bulletDef.spriteName = description.getString("bulletSprite");
        bulletDef.scale = description.getFloat("bulletSpriteScale");
        bulletDef.density = description.getFloat("density");
    }

    public void reload() {
        cooldown = reloadTime;
        currentAmmo = maxAmmo;
    }

    public void take()
    {
        takeSound.play(0.02f);
    }

    public void hide() {

    }

    public void dispose() {
        fireSound.dispose();
        takeSound.dispose();
        animation.dispose();
    }


}
